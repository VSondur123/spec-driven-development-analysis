import java.awt.*;
import java.awt.event.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import javax.swing.*;
import javax.imageio.ImageIO;

/** Evaluator-owned harness. Uses OS Robot input against the unmodified frozen JAR. */
public class Cr01GuiEvaluator {
  static Robot robot;
  static JFrame window;
  static Path out;
  static final Map<String,Component> controls=new HashMap<>();
  static final java.util.List<String> records=new ArrayList<>();
  static <T>T edt(Callable<T> c)throws Exception {FutureTask<T> f=new FutureTask<>(c);SwingUtilities.invokeAndWait(f);return f.get();}
  static void scan(Component c){if(c.getName()!=null)controls.put(c.getName(),c);if(c instanceof Container p)for(Component x:p.getComponents())scan(x);}
  static String text(String name)throws Exception{return edt(()->{Component c=controls.get(name);return c instanceof JTextField f?f.getText():((JLabel)c).getText();});}
  static void click(String name)throws Exception {Point p=edt(()->{Component c=controls.get(name);Point q=c.getLocationOnScreen();return new Point(q.x+c.getWidth()/2,q.y+c.getHeight()/2);});robot.mouseMove(p.x,p.y);robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);robot.waitForIdle();robot.delay(150);}
  static void fill(String name,String s)throws Exception{click(name);robot.keyPress(KeyEvent.VK_META);robot.keyPress(KeyEvent.VK_A);robot.keyRelease(KeyEvent.VK_A);robot.keyRelease(KeyEvent.VK_META);robot.keyPress(KeyEvent.VK_BACK_SPACE);robot.keyRelease(KeyEvent.VK_BACK_SPACE);for(char c:s.toCharArray()){int k=KeyEvent.getExtendedKeyCodeForChar(c);robot.keyPress(k);robot.keyRelease(k);}robot.waitForIdle();robot.delay(100);}
  static void reset()throws Exception {click("clearButton");}
  static void operands(String a,String b,String op)throws Exception{fill("firstOperandField",a);fill("secondOperandField",b);if(op!=null)click(op+"Button");}
  static String state()throws Exception{return "first="+text("firstOperandField")+"; second="+text("secondOperandField")+"; result="+text("resultLabel")+"; error="+text("errorLabel");}
  static String shot(String id)throws Exception{String rel="screenshots/"+id+".png";Rectangle r=edt(()->window.getBounds());ImageIO.write(robot.createScreenCapture(r),"png",out.resolve(rel).toFile());return rel;}
  static void record(String id,String expected,boolean ok)throws Exception{String line=String.join("\t",OffsetDateTime.now().toString(),id,ok?"Pass":"Fail",expected,state(),shot(id));records.add(line);Files.write(out.resolve("gui-observations.tsv"),records);System.out.println(line);}
  static void result(String id,String a,String b,String op,String expected)throws Exception{reset();operands(a,b,op);click("calculateButton");record(id,expected,text("resultLabel").equals(expected)&&text("errorLabel").isEmpty());}
  static void error(String id,String a,String b,String op,String expected)throws Exception{reset();operands(a,b,op);click("calculateButton");record(id,expected,text("errorLabel").equals(expected)&&text("resultLabel").isEmpty()&&edt(()->window.isShowing()));}
  public static void main(String[]args)throws Exception{
    out=Path.of(args[1]);Files.createDirectories(out.resolve("screenshots"));
    calculator.ui.CalculatorApp.main(new String[0]);Thread.sleep(1000);
    edt(()->{for(Frame f:Frame.getFrames())if(f instanceof JFrame j&&j.isShowing()){window=j;scan(j);}window.toFront();window.requestFocus();return null;});
    Desktop.getDesktop().requestForeground(true);
    edt(()->{window.toFront();window.requestFocus();return null;});
    robot=new Robot();robot.setAutoDelay(35);robot.waitForIdle();robot.delay(1000);
    if(args[0].equals("probe")){fill("firstOperandField","7");System.out.println("Visible="+edt(()->window.isShowing())+"; bounds="+edt(()->window.getBounds())+"; controls="+controls.keySet());shot("probe");edt(()->{window.dispose();return null;});return;}
    records.add("time\tid\toutcome\texpected\tactual\tevidence");
    try {
      String[] unary={"square","squareRoot","reciprocal"};
      boolean visible=true;
      for(String op:unary){visible &= edt(()->controls.get(op+"Button").isShowing());System.out.println(op+" label="+edt(()->((JToggleButton)controls.get(op+"Button")).getText()));}
      record("CR-AC-01","Square, square-root and reciprocal controls visible",visible);
      result("CR-AC-02","-3","","square","9");
      result("CR-AC-03","1.2","","square","1.44");
      result("CR-AC-04","0","","square","0");
      result("CR-AC-05","9","","squareRoot","3");
      result("CR-AC-06","2","","squareRoot","1.4142135624");
      result("CR-AC-07","0","","squareRoot","0");
      error("CR-AC-08","-1","","squareRoot","Cannot take square root of a negative number");
      result("CR-AC-09","4","","reciprocal","0.25");
      result("CR-AC-10","3","","reciprocal","0.3333333333");
      result("CR-AC-11","-4","","reciprocal","-0.25");
      error("CR-AC-12","0","","reciprocal","Cannot divide by zero");
      error("CR-AC-13-1","0.0","","reciprocal","Cannot divide by zero");
      error("CR-AC-13-2","-0.0","","reciprocal","Cannot divide by zero");
      for(int i=0;i<3;i++)error("CR-AC-14-"+(i+1),"","",unary[i],"Enter both numbers");
      for(int i=0;i<3;i++)error("CR-AC-15-"+(i+1),"abc","",unary[i],"Invalid number");
      String[] inputs={"-3","9","4"},expected={"9","3","0.25"};
      for(int i=0;i<3;i++)result("CR-AC-16-"+(i+1),inputs[i],"",unary[i],expected[i]);
      for(int i=0;i<3;i++)result("CR-AC-17-"+(i+1),inputs[i],"123",unary[i],expected[i]);
      reset();operands("-1","","squareRoot");click("calculateButton");boolean initial=!text("errorLabel").isEmpty();fill("firstOperandField","9");click("calculateButton");record("CR-AC-18","Recover negative sqrt error to sqrt(9)=3",initial&&text("resultLabel").equals("3")&&text("errorLabel").isEmpty());
      reset();operands("0","","reciprocal");click("calculateButton");initial=!text("errorLabel").isEmpty();fill("firstOperandField","4");click("calculateButton");record("CR-AC-19","Recover reciprocal zero error to 1/4=0.25",initial&&text("resultLabel").equals("0.25")&&text("errorLabel").isEmpty());
      for(int i=0;i<3;i++){
        reset();operands(inputs[i],"",unary[i]);click("calculateButton");reset();
        boolean clear=text("firstOperandField").isEmpty()&&text("secondOperandField").isEmpty()&&text("resultLabel").isEmpty()&&text("errorLabel").isEmpty();
        for(String op:new String[]{"add","subtract","multiply","divide","square","squareRoot","reciprocal"})clear&=edt(()->!((JToggleButton)controls.get(op+"Button")).isSelected());
        record("CR-AC-20-"+(i+1),"Clear resets all inputs, seven operations and output",clear);
      }
      // Supplementary checks for agreed LOG-17 and CR-FR-03, kept outside parent denominators.
      for(int i=0;i<3;i++)result("SUP-SECOND-"+(i+1),inputs[i],"abc",unary[i],expected[i]);
      for(int i=0;i<3;i++){
        reset();operands(inputs[i],"",unary[i]);click("firstOperandField");robot.keyPress(KeyEvent.VK_ENTER);robot.keyRelease(KeyEvent.VK_ENTER);robot.waitForIdle();robot.delay(200);
        record("SUP-ENTER-"+(i+1),expected[i],text("resultLabel").equals(expected[i])&&text("errorLabel").isEmpty());
      }
    }finally{edt(()->{window.dispose();return null;});}
  }
}

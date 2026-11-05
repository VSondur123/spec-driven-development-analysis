import java.awt.*;
import java.awt.event.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import javax.swing.*;
import javax.imageio.ImageIO;

/** Evaluator-owned harness. Uses OS Robot input against the unmodified frozen JAR. */
public class BaselineGuiEvaluator {
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
      if(args[0].equals("offline")){record("BASE-AC-01","GUI opens while networking disabled",edt(()->window.isShowing()));return;}
      reset();fill("firstOperandField","7");record("BASE-AC-02","7 displayed",text("firstOperandField").equals("7"));
      reset();fill("firstOperandField","1.5");record("BASE-AC-03","1.5 displayed",text("firstOperandField").equals("1.5"));
      result("BASE-AC-04","7","5","add","12");
      result("BASE-AC-05","7","5","subtract","2");
      result("BASE-AC-06","7","5","multiply","35");
      result("BASE-AC-07","7","2","divide","3.5");
      result("BASE-AC-08","-7","2","add","-5");
      result("BASE-AC-09","1.2","2.3","add","3.5");
      reset();operands("7","5","add");click("calculateButton");reset();
      boolean empty=text("firstOperandField").isEmpty()&&text("secondOperandField").isEmpty()&&text("resultLabel").isEmpty()&&text("errorLabel").isEmpty();
      for(String op:new String[]{"add","subtract","multiply","divide"})empty&=edt(()->!((JToggleButton)controls.get(op+"Button")).isSelected());
      record("BASE-AC-10","Inputs, operation, result and error reset",empty);
      // Supplement: also verify Clear removes an error.
      operands("abc","2","add");click("calculateButton");reset();record("BASE-AC-10-error","Error cleared",text("errorLabel").isEmpty());
      String[][] missing={{"","5"},{"7",""},{"",""},{"   ","5"}};
      for(int i=0;i<missing.length;i++)error("BASE-AC-11-"+(i+1),missing[i][0],missing[i][1],"add","Enter both numbers");
      String[] bad={"abc","1.2.3","1,5","1e3","--1",".",".5","5.","-"};
      for(int i=0;i<bad.length;i++)error("BASE-AC-12-"+(i+1),bad[i],"2","add","Invalid number");
      error("BASE-AC-13-1","7","0","divide","Cannot divide by zero");
      error("BASE-AC-13-2","7","0.0","divide","Cannot divide by zero");
      reset();operands("abc","2","add");click("calculateButton");boolean hadError=!text("errorLabel").isEmpty();fill("firstOperandField","1.5");click("calculateButton");record("BASE-AC-14","Recover abc+2 error to 1.5+2=3.5",hadError&&text("resultLabel").equals("3.5")&&text("errorLabel").isEmpty());
      reset();String[][] seq={{"7","5","add","12"},{"7","2","divide","3.5"},{"-7","2","add","-5"}};
      for(int i=0;i<seq.length;i++){String[]s=seq[i];operands(s[0],s[1],s[2]);click("calculateButton");record("BASE-AC-16-"+(i+1),s[3],text("resultLabel").equals(s[3])&&text("errorLabel").isEmpty());}
      error("BASE-AC-17","7","5",null,"Select an operation");
      result("BASE-AC-18-1","0","5","divide","0");result("BASE-AC-18-2","1","3","divide","0.3333333333");result("BASE-AC-18-3","1","2048","divide","0.0004882813");result("BASE-AC-18-4","-1","2048","divide","-0.0004882813");
      reset();operands("7","5","add");click("calculateButton");String expected=text("resultLabel");reset();operands("7","5","add");click("firstOperandField");robot.keyPress(KeyEvent.VK_ENTER);robot.keyRelease(KeyEvent.VK_ENTER);robot.waitForIdle();robot.delay(200);record("BASE-AC-19","Enter in First number gives same 12 as Calculate",expected.equals("12")&&text("resultLabel").equals(expected)&&text("errorLabel").isEmpty());
    }finally{edt(()->{window.dispose();return null;});}
  }
}

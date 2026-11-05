"""Brief offline launch; always restore Wi-Fi. Run from the study root."""
from pathlib import Path
import subprocess
from datetime import datetime
root=Path.cwd()
out=root/'research/empirical-methodology/evaluations/2026-11-05-spec-kit-baseline'
with (out/'offline-network-state.txt').open('w') as log:
    def run(args, **kw):
        log.write('\n'+datetime.now().astimezone().isoformat()+' $ '+' '.join(args)+'\n');log.flush()
        p=subprocess.run(args,stdout=subprocess.PIPE,stderr=subprocess.STDOUT,text=True,**kw)
        log.write(p.stdout+f'Exit: {p.returncode}\n');log.flush();return p
    before=run(['networksetup','-getairportpower','en0'])
    if 'On' not in before.stdout: raise RuntimeError('Unexpected initial network state; no network change attempted')
    try:
        run(['networksetup','-setairportpower','en0','off']).check_returncode()
        state=run(['networksetup','-getairportpower','en0'])
        ipv4=run(['route','-n','get','default'])
        ipv6=run(['route','-n','get','-inet6','default'])
        run(['ifconfig','-a'])
        if 'Off' not in state.stdout or ipv4.returncode==0 or ipv6.returncode==0:
            raise RuntimeError('Offline state not established; no offline Pass can be claimed')
        cp=str(root/'tmp/spec-kit-evaluation')+':'+str(root/'tmp/spec-kit-evaluation/baseline/github-spec-kit-calculator/target/baseline-calculator-1.0.0.jar')
        run(['/Library/Java/JavaVirtualMachines/amazon-corretto-22.jdk/Contents/Home/bin/java','-cp',cp,'BaselineGuiEvaluator','offline',str(out/'offline')],timeout=25).check_returncode()
    finally:
        run(['networksetup','-setairportpower','en0','on']).check_returncode()
        run(['networksetup','-getairportpower','en0'])

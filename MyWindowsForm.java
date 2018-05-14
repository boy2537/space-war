
import java.util.Timer;
import java.util.TimerTask;

public class MyWindowsForm {
    private int count = 0;
    public MyWindowsForm(){
       
        Timer myTimer;
        myTimer = new Timer();

        myTimer.schedule(new TimerTask() {
            public void run() {
                 count++;
            }
        }, 0, 1000);
        
    }
    public int getCount(){
        return count;
    }
}
package test;

//imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; //using action listener b/c I believe this is how we can stop play
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;

public class timerTest {
    int seconds, minutes;
    private JLabel label;
    public timerTest(int m, int s) {
        label = new JLabel("Game label");
        minutes = m;
        seconds = s;
        label.setSize(400, 400);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setHorizontalAlignment(JLabel.CENTER);
        labelDisplay(minutes, seconds);
    }

    public void countdownTest() {
        int delay = 1000;
        Timer t = new Timer(delay, null);
        t.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                seconds--;
                labelDisplay(minutes, seconds);
                if(seconds < 11 && minutes == 0){
                    label.setForeground(Color.RED);
                }
                if (seconds == -1) {
                    seconds = 59;
                    minutes--;
                    labelDisplay(minutes, seconds);
                }
                if (minutes == 0 && seconds == 0) {
                    t.stop();
                    return;
                }
            }
        });
        t.start();
    }

    public String labelDisplay(int min, int sec){
        String s = "00;00";
        if(min < 10){
            if(sec < 10){
                label.setText("0" + min + ":0" + sec);
            }else{
                label.setText("0" + min + ":" + sec);
            }
        }else if(sec < 10){
            label.setText(min + ":0" + sec);
        }else{
            label.setText(min + ":" + sec);
        }
        return s;
    }

    public void createFrame(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this.label);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}
package test;

//imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; //using action listener b/c I believe this is how we can stop play
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.Font;

public class timerTest {
    // Create a window to display the timer count down -> can be moved later
    // Instance variables
    int seconds, minutes;
    private JLabel label;

    // Constructor: pass in the minutes and seconds needed for the timer
    public timerTest(int m, int s) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label = new JLabel("Game label"); // possible to change the font

        // For adding an icon which can be behind the count down
        // ImageIcon icon = new ImageIcon("timer.png");
        // Image img = icon.getImage();
        // resize the image to be smaller
        // Image newImg = img.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
        // icon = new ImageIcon(newImg);
        // label.setIcon(new ImageIcon("timer.png")); DONT UN-COMMENT
        // label.setIcon(icon);
        // label.setText("Counting down"); //Can try to put the label on top of the icon
        // image if we want

        minutes = m;
        seconds = s;

        label.setSize(400, 400); // possibly use set bounds to be more specific about size
        // Changing fonts & text size
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setHorizontalAlignment(JLabel.CENTER);
        labelDisplay(minutes, seconds);

        frame.add(label);
        frame.setVisible(true);
    }

    //Begins the timer count down
    public void countdownTest() {
        int delay = 1000;
        Timer t = new Timer(delay, null);
        t.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                seconds--;
                labelDisplay(minutes, seconds);
                // Decreasing the minute when seconds fall below 0, still want to display 0
                if (seconds == -1) {
                    seconds = 30;
                    minutes--;
                    labelDisplay(minutes, seconds);
                }
                // Stop the timer, stop the play action
                if (minutes == 0 && seconds == 0) {
                    t.stop();
                    return;
                }
            }
        });
        t.start();
    }

    //formats the timer display to show as "##:##"
    public void labelDisplay(int min, int sec)
    {
        if(min < 10)
        {
            if(sec < 10){
                label.setText("0" + min + ":0" + sec);}
            else{
                label.setText("0" + min + ":" + sec);}
        }
        else if(sec < 10)
        {
            label.setText(min + ":0" + sec);
        }
        else
        {
            label.setText(min + ":" + sec);
        }
    }

    //Creates a new frame for count down before game begins
    public void createFrame()
    {

    }

}
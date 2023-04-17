package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
//import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class timerTest {
    int seconds, minutes;
    private JLabel label;
    Image img = Toolkit.getDefaultToolkit().getImage("src/main/java/test/hourglass.jpg");

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

    // @Override
    // public void paintComponent(Graphics g){
    //     super.paintComponent(g);
    //     g.drawImage(img, 0, 0, null);
    // }

    public void createFrame(){
        JFrame frame = new JFrame();
        // ImageIcon icon = new ImageIcon("src/main/java/test/hourglass.jpg");
        // JLabel l = new JLabel(icon);
        // frame.add(l);
        // Image img = Toolkit.getDefaultToolkit().getImage("src/main/java/test/hourglass.jpg");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this.label);
        frame.setSize(600, 600);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setVisible(true);
    }
}
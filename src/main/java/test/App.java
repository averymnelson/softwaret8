package test;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class App {
    public static void ShowLogo() {
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("src/main/java/test/logo.jpg");
        JLabel label = new JLabel(icon);
        frame.add(label);
        frame.setSize(1000, 636);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        new Timer(3_000, (e) -> {
            frame.setVisible(false);
            frame.dispose();
        }).start();
    }

    /**
     * Runs Laser Tag System.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        ShowLogo();
        playerEntry entryScreen = new playerEntry();
        entryScreen.createGUI();
        //entryScreen.viewDATA();
    }
}
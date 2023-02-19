import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LazerTag{
  public static void ShowPicture(){
    var frame = new JFrame();
    var icon = new ImageIcon("logo.jpg");
    var label = new JLabel(icon);
    frame.add(label);
    frame.setSize(1000, 636);
    frame.setFocusable(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    new Timer(3_000, (e) -> { frame.setVisible(false); frame.dispose(); }).start();
  }
  public static void main(String[] args) {
    playerEntry entryScreen = new playerEntry();
    entryScreen.createGUI();
    ShowPicture();
  }
}
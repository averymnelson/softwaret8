import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

class HelloWorldApp {
  public static void main(String[] args) {
    System.out.println("Hello World!"); // Display the string.
    System.out.println("adding changes 2/6/23 2:49 pm");
  }
}
   
class ShowPicture {
 public static void main(String args[]) {
  var frame = new JFrame();
  var icon = new ImageIcon("logo.jpg");
  var label = new JLabel(icon);
  frame.add(label);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.pack();
  frame.setVisible(true);
 }
}

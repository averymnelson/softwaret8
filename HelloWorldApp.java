import javax.swing.JFrame;
//import java.util.Timer;

class HelloWorldApp {
  public static void main(String[] args) {
    System.out.println("Hello World!"); // Display the string.
    System.out.println("adding changes 2/6/23 2:49 pm");
  }
}
   
class ShowPicture extends JFrame{
 public static void main(String args[]) {
  //Timer timer = new Timer();
  var frame = new JFrame();
  View view = new View();
  frame.setSize(1000, 636);
  frame.setFocusable(true);
  frame.getContentPane().add(view);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setVisible(true);
 }
}

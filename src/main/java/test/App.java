package test;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.sql.*;

/**
 * Hello world!
 */
public final class App {
    public static void ShowLogo(){
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("src/main/java/test/logo.jpg");
        JLabel label = new JLabel(icon);
        frame.add(label);
        frame.setSize(1000, 636);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        new Timer(3_000, (e) -> { frame.setVisible(false); frame.dispose(); }).start();
    }
    public static void connectDB(){
        String url="jdbc:postgresql://ec2-3-224-125-117.compute-1.amazonaws.com:5432/dfj0j6glv3vvep?sslmode=require&user=mjajmnowzmxraa&password=64e8a963ce726f7735861c6967d2ff3757b46a6c647f90ad0eee23eb5b4bd999";
        try (Connection conn = DriverManager.getConnection(url)){
            if (conn != null) {
                System.out.println("Connected to the database!");
                String sql = "INSERT INTO player (id, first_name, last_name, codename) VALUES (10, 'testfName', 'testlName', 'Test')";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();
                pstmt.close();
                conn.close();
            }
            else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Runs Laser Tag System.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        playerEntry entryScreen = new playerEntry();
        entryScreen.createGUI();
        ShowLogo();
        connectDB();
    }
}

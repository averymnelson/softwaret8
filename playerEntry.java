import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
// import javax.swing.JTextArea;
// import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.*; //to cover every import for now, can delete later
// claire, gracie

//Class playerEntry : Creates tables and allows user to enter text
public class playerEntry extends JFrame implements ActionListener {

    //Instance variables/constants
    // protected JTextField textField;
    // protected JTextArea textArea;
    //private final static String newline = "\n";
    public static int ID;
    public static String fName, lName, codeName; 
    
    //Constructor
    public playerEntry(){
      
    }

    //Create a table for player entry
    public void createGUI () {
        //New Frame and panel objects 
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Entry Terminal");

        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Edit Current Game", TitledBorder.CENTER, TitledBorder.TOP));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Team 1"));
        
        //Table for player entry 
        String players[][] = new String[20][4];   
        String [] columnName = {"ID", "Codename","ID", "Codename" };
        JTable table = new JTable(players,columnName);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);
        frame.setSize(1000, 636);
        
        //can switch to using textField if the table begins to cause trouble later
        // textArea.setEditable(true);
        // textField = new JTextField(10);
        // textField.addActionListener(this);
        // textArea = new JTextArea(4, 10);
        //frame.getContentPane().add(new JButton("Click")); //frame.add(new JButton("Click"));//adds to the whole screen
        
        //Able to add an icon to a button! will do later... - Claire
        //Glitch where the player screen pops up for a second?
        JButton bStart = new JButton("Start");
        panel.add(bStart);
        frame.setVisible(true);
        
        //Formatting the table
        //table.setBounds(30,40,200,300);
        // table.setShowGrid(true);
        table.setGridColor(Color.gray);
        table.setBackground(Color.pink);
        table.setRowSelectionAllowed(false);
        table.setSelectionBackground(Color.pink);
        table.setSelectionForeground(Color.pink);
    }
    
    //Override ActionListener methods
    // @Override
    //Can add actions for the buttons on screen later
    public void actionPerformed(ActionEvent e) {
        // String text = textField.getText();
        // textArea.append(text + newline);
        // //Sets the position of the text insertion
        // textArea.setCaretPosition(textArea.getDocument().getLength());
        // // --Place first record into table
    // INSERT INTO player (id, first_name, last_name, codename)
    // VALUES (1, 'Jim', 'Strother', 'Opus');
    }
}

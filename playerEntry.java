import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import javax.swing.GroupLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
import javax.swing.JTable;

//Class playerEntry : Creates tables and allows user to enter text
public class playerEntry extends JFrame implements ActionListener{
    //Instance variables/constants
    //protected JTextField textField;
    //protected JTextArea textArea;
    private final static String newline = "\n";
    public static int ID;
    public static String fName, lName, codeName; 
    
    //Constructor
    public playerEntry(){
        //New GridLayout object: one component per rectangle (rows, columns)
        GridLayout grid = new GridLayout(1, 0);
        //Column labels
        String [] columnName = {"ID", "Codename"};
        // textField = new JTextField(10);
        // textField.addActionListener(this);
        // textArea = new JTextArea(4, 10);
        //Make the text box editable for player entry/changes to player names
        //textArea.setEditable(true);
        //JScrollPane scrollPane = new JScrollPane(textArea);
        // JFrame frame = new JFrame("EntryScreenDemo");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(600, 600);
        // frame.setVisible(true);
        JTable table = new JTable(10, 2);
        //table.setShowGrid(true);
        //Add Components to this panel.
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        //Add the scroll pane to this panel.
        add(scrollPane);
    }
    //Create a table for player entry
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("EntryScreenDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        //Add contents to the window.
        //frame.add(new playerEntry());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    //Override ActionListener methods
    @Override
    public void actionPerformed(ActionEvent e) {
        // String text = textField.getText();
        // textArea.append(text + newline);
        // //Sets the position of the text insertion
        // textArea.setCaretPosition(textArea.getDocument().getLength());
        // // --Place first record into table
    // INSERT INTO player (id, first_name, last_name, codename)
    // VALUES (1, 'Jim', 'Strother', 'Opus');
    }

    // private static void run() {
    //     createAndShowGUI();
    // }

    //Main
    // public static void main(String[] args){
    //     System.out.println("Hello");
    //     playerEntry entryScreen = new playerEntry();
    //     //createAndShowGUI();
    //     //run();
    // }
}

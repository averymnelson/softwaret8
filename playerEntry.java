//import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import javax.swing.GroupLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.security.auth.kerberos.KerberosCredMessage;
//import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.JScrollPane;
//import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;

// claire, gracie

//Class playerEntry : Creates tables and allows user to enter text
// public class playerEntry extends JFrame implements ActionListener{
    public class playerEntry extends JFrame implements ActionListener {

    //Instance variables/constants
    protected JTextField textField;
    protected JTextArea textArea;
    private final static String newline = "\n";
    public static int ID;
    public static String fName, lName, codeName; 
    
    //Constructor
    public playerEntry(){
        //New GridLayout object: one component per rectangle (rows, columns)
        //GridLayout grid = new GridLayout(1, 0);
        //Column labels

        // textField = new JTextField(10);
        // textField.addActionListener(this);
        // textArea = new JTextArea(4, 10);
        //Make the text box editable for player entry/changes to player names
        // textArea.setEditable(true);
        // JTable table = new JTable(10, 2);
        // table.setBounds(30,40,200,300);
        // table.setShowGrid(true);
        //Add Components to this panel.
        
        // JFrame frame = new JFrame("EntryScreenDemo");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(600, 600);
        // frame.setVisible(true);
        // JScrollPane scrollPane = new JScrollPane(table);
        // //Add the scroll pane to this panel.
        // add(scrollPane);

        // textField = new JTextField(10);
        // textField.addActionListener(this);
        // textArea = new JTextArea(4, 10);
        // textArea.setEditable(true);
    }
    //Create a table for player entry
    public static void createGUI () {

        //JTable table = new JTable(10, 2);

        JFrame frame = new JFrame("EntryScreenDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        //panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Team 1"));
        // frame.setSize(600, 600);
        // frame.setVisible(true);
        //JScrollPane scrollPane = new JScrollPane(table);
        //Add the scroll pane to this panel.
        // add(scrollPane);

        String players[][] = new String[20][4];   
        String [] columnName = {"ID", "Codename","ID", "Codename" };
        JTable table = new JTable(players,columnName);
        
        // textArea.setEditable(true);
        // textField = new JTextField(10);
        // textField.addActionListener(this);
        // textArea = new JTextArea(4, 10);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setVisible(true);
        //table.setBounds(30,40,200,300);
        // table.setShowGrid(true);
        //add(scrollPane);
        table.setGridColor(Color.pink);
    }

    // }
    
    //Override ActionListener methods
    // @Override
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

    // Main
    public static void main(String[] args){
        System.out.println("Hello");
        playerEntry entryScreen = new playerEntry();
        createGUI();
        //createAndShowGUI();
        //run();
    }
}

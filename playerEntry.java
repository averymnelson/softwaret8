import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;

// claire, gracie

//Class playerEntry : Creates tables and allows user to enter text
public class playerEntry extends JFrame implements ActionListener {

    //Instance variables/constants
    public static int ID;
    public static String fName, lName, codeName; 
    
    //Constructor
    public playerEntry(){}

    //Create a table for player entry
    public void createGUI () {

        //JTable table = new JTable(10, 2);
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Entry Terminal");

        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Edit Current Game", TitledBorder.CENTER, TitledBorder.TOP));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String players[][] = new String[20][4];   
        String [] columnName = {"ID", "Codename","ID", "Codename" };
        JTable table = new JTable(players,columnName);
        JScrollPane scrollPane = new JScrollPane(table);
        JButton bStart = new JButton("Start Game");

        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);
        frame.setSize(1000, 636);
        panel.add(bStart);
        frame.setVisible(true);
        
        table.setGridColor(Color.gray);
        table.setBackground(Color.pink);
        table.setRowSelectionAllowed(false);
        table.setSelectionBackground(Color.pink);
        table.setSelectionForeground(Color.pink);
    }
    
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
}

package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

//Class playerEntry : Creates tables and allows user to enter text
public class playerEntry extends JFrame implements ActionListener {

    //Instance variables/constants
    public static int ID;
    public static String fName, lName, codeName; 
    public String team1Players[][] = new String[15][2];
    public String team2Players[][] = new String[15][2];
    
    //Constructor
    public playerEntry(){}

    //Create a table for player entry
    public void createGUI () {

        //JTable table = new JTable(10, 2);
        JPanel panel = new JPanel();
        JPanel mainPanel, subPanel1, subPanel2;
        JFrame frame = new JFrame("Entry Terminal");

        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Edit Current Game", TitledBorder.CENTER, TitledBorder.TOP));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
    	mainPanel.setBorder(BorderFactory.createTitledBorder("Edit Current Game"));
    	mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
    	
    	//Constructing JPanel 1 and 2 with GridLayout of 1 row and 1 column
    	subPanel1 = new JPanel();
    	subPanel1.setBorder(BorderFactory.createTitledBorder("Team 1"));
    	subPanel1.setLayout(new GridLayout(1,1));
    	subPanel2 = new JPanel();
    	subPanel2.setBorder(BorderFactory.createTitledBorder("Team 2"));
    	subPanel2.setLayout(new GridLayout(1,1));

        String [] columnName = {"ID", "Codename"};
        JTable table1 = new JTable(team1Players,columnName);
        JTable table2 = new JTable(team2Players,columnName);
        JScrollPane scrollPane1 = new JScrollPane(table1);
        JScrollPane scrollPane2 = new JScrollPane(table2);

        JButton bStart = new JButton("Start Game");
        bStart.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;      
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;

        //Adding JPanel 1 and 2 to main JPanel
        subPanel1.add(scrollPane1);
        subPanel2.add(scrollPane2);
    	mainPanel.add(subPanel1);
    	mainPanel.add(subPanel2);

        frame.add(mainPanel);
        frame.setSize(1000, 636);
        mainPanel.add(bStart, c);
        addKeyBind(mainPanel, "F5");
        frame.setVisible(true);
        
        table1.setRowHeight(26);
        table1.setGridColor(Color.gray);
        table1.setBackground(Color.pink);
        table1.setRowSelectionAllowed(false);

        table2.setRowHeight(26);
        table2.setGridColor(Color.gray);
        table2.setBackground(Color.pink);
        table2.setRowSelectionAllowed(false);
    }

    private void addKeyBind(JComponent contentPane, String key) {
        InputMap inputMap = contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = contentPane.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(key), "Start Game");
        actionMap.put("Start Game", startGame);
    }

    Action startGame = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
          System.out.println("Game Started");
          playActionDisplay display = new playActionDisplay();
          display.createGUI();
        }
      };
    
    //Override ActionListener methods
    // @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Game Started");
        playActionDisplay display = new playActionDisplay();
        display.createGUI();
        //System.out.println(team1Players[0][0] + " " + team1Players[0][1]);
        // String text = textField.getText();
        // textArea.append(text + newline);
        // //Sets the position of the text insertion
        // textArea.setCaretPosition(textArea.getDocument().getLength());
        // // --Place first record into table
    // INSERT INTO player (id, first_name, last_name, codename)
    // VALUES (1, 'Jim', 'Strother', 'Opus');
    }
}

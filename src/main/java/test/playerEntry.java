package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.Timer;
import java.sql.*;

//Class playerEntry : Creates tables and allows user to enter text
public class playerEntry extends JFrame implements ActionListener {

    // Instance variables/constants
    public static int ID;
    public static String fName, lName, codeName;
    public String team1Players[][] = new String[15][2];
    public String team2Players[][] = new String[15][2];

    // Constructor
    public playerEntry() {
    }

    // Create a table for player entry
    public void createGUI() {

        // JTable table = new JTable(10, 2);
        JPanel panel = new JPanel();
        JPanel mainPanel, subPanel1, subPanel2;
        JFrame frame = new JFrame("Entry Terminal");

        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Edit Current Game",
                TitledBorder.CENTER, TitledBorder.TOP));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createTitledBorder("Edit Current Game"));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Constructing JPanel 1 and 2 with GridLayout of 1 row and 1 column
        subPanel1 = new JPanel();
        subPanel1.setBorder(BorderFactory.createTitledBorder("Team 1"));
        subPanel1.setLayout(new GridLayout(1, 1));
        subPanel2 = new JPanel();
        subPanel2.setBorder(BorderFactory.createTitledBorder("Team 2"));
        subPanel2.setLayout(new GridLayout(1, 1));

        String[] columnName = { "ID", "Codename" };
        JTable table1 = new JTable(team1Players, columnName);
        JTable table2 = new JTable(team2Players, columnName);
        JScrollPane scrollPane1 = new JScrollPane(table1);
        JScrollPane scrollPane2 = new JScrollPane(table2);

        JButton bStart = new JButton("Start Game (f5)");
        bStart.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;

        // Adding JPanel 1 and 2 to main JPanel
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
        public void actionPerformed(ActionEvent ae) {
            System.out.println("30 Seconds to game start");
            timerTest test = new timerTest();
            test.countdownTest();
            connectDB();
            new Timer(31_000, (e) -> {
                playActionDisplay display = new playActionDisplay();
                display.createGUI();
            }).start();
        }
    };

    // Override ActionListener methods
    // @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("30 Seconds to game start");
        timerTest test = new timerTest();
        test.countdownTest();
        connectDB();
        new Timer(31_000, (e) -> {
            playActionDisplay display = new playActionDisplay();
            display.createGUI();
        }).start();
        // System.out.println(team1Players[0][0] + " " + team1Players[0][1]);
        // String text = textField.getText();
        // textArea.append(text + newline);
        // //Sets the position of the text insertion
        // textArea.setCaretPosition(textArea.getDocument().getLength());
        // // --Place first record into table
        // INSERT INTO player (id, first_name, last_name, codename)
        // VALUES (1, 'Jim', 'Strother', 'Opus');
    }
    
    public void connectDB(){
        String url="jdbc:postgresql://ec2-3-224-125-117.compute-1.amazonaws.com:5432/dfj0j6glv3vvep?sslmode=require&user=mjajmnowzmxraa&password=64e8a963ce726f7735861c6967d2ff3757b46a6c647f90ad0eee23eb5b4bd999";
        try (Connection conn = DriverManager.getConnection(url)){
            if (conn != null) {
                System.out.println("Connected to the database!");
                for(int i = 0; i < 15; i++){
                    if(team1Players[i][0] != null && team1Players[i][1] != null){
                        String sql = "INSERT INTO player (id, first_name, last_name, codename) VALUES (" + team1Players[i][0] + ", 'testfName', 'testlName', '" + team1Players[i][1] + "')";
                        //String sql = "DELETE FROM player";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.executeUpdate();
                        pstmt.close();
                    }
                    if(team2Players[i][0] != null && team2Players[i][1] != null){
                        String sql = "INSERT INTO player (id, first_name, last_name, codename) VALUES (" + team2Players[i][0] + ", 'testfName', 'testlName', '" + team2Players[i][1] + "')";
                        //String sql = "DELETE FROM player";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.executeUpdate();
                        pstmt.close();
                    }
                }
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
}

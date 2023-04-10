package test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;

public class playActionDisplay extends JPanel {
    public String[][] team1Players = new String[16][2];
    public String[][] team2Players = new String[16][2];
    public static JPanel subPanel3;
    public static DefaultTableModel model;

    int minutes = 6;
    int seconds = 0;
    private JLabel l;

    public playActionDisplay() {
    }

    public String[][] teamPlayers(int team) {
        // this function takes the playeer entry array code name values
        // it adds a default score of 0 to each player
        // adds a default total team score of 0 to each team
        for (int i = 0; i < 15; i++) {
            if (playerEntry.team1Players[i][0] != null && playerEntry.team1Players[i][1] != null) {
                team1Players[i][0] = playerEntry.team1Players[i][1];
                team1Players[i][1] = "0";
                team2Players[i][0] = playerEntry.team2Players[i][1];
                team2Players[i][1] = "0";
            }
        }

        team1Players[15][0] = "Total Score";
        team1Players[15][1] = "0";
        team2Players[15][0] = "Total Score";
        team2Players[15][1] = "0";

        if (team == 1)
            return team1Players;
        if (team == 2)
            return team2Players;
        else
            return null;
    }

    public JPanel gameTime(){
        JPanel countDownPanel = new JPanel();
        countDownPanel.setBorder(BorderFactory.createTitledBorder("Time Remaining:"));
        countDownPanel.setLayout(new GridLayout(1,1));
        l = new JLabel("Count down");
        l.setSize(100, 20);
        l.setFont(new Font("Arial", Font.BOLD, 16));
        l.setHorizontalAlignment(JLabel.CENTER);
        labelDisplay(minutes, seconds);
        countdownTest();
        countDownPanel.add(l);
        return countDownPanel;
    }

    //Begins the timer count down
    public void countdownTest() {
        int delay = 1000;
        Timer t = new Timer(delay, null);
        t.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                seconds--;
                labelDisplay(minutes, seconds);
                //Change label to red when 10 seconds remaining
                if(seconds < 11 && minutes == 0){
                    l.setForeground(Color.RED);
                }
                // Decreasing the minute when seconds fall below 0, still want to display 0
                if (seconds == -1) {
                    seconds = 59;
                    minutes--;
                    labelDisplay(minutes, seconds);
                }
                // Stop the timer, stop the play action
                if (minutes == 0 && seconds == 0) {
                    t.stop();
                    return;
                }
            }
        });
        t.start();
    }

    //formats the timer display to show as "##:##"
    public String labelDisplay(int min, int sec)
    {
        String s = "00;00";
        if(min < 10)
        {
            if(sec < 10){
                l.setText("0" + min + ":0" + sec);
                s = "0" + min + ":0" + sec;}
            else{
                l.setText("0" + min + ":" + sec);
                s = "0" + min + ":" + sec;}
        }
        else if(sec < 10)
        {
            l.setText(min + ":0" + sec);
            s = min + ":0" + sec;
        }
        else
        {
            l.setText(min + ":" + sec);
            s = min + ":" + sec;
        }
        return s;
    }

    public void createGUI() {
        JPanel mainPanel, subPanel1, subPanel2, displayCountdown;
        JFrame frame = new JFrame("Play Action Display");
        String[] columnName = { "Code Name", "Score" };

        JTable table1 = new JTable(teamPlayers(1), columnName);
        table1.setShowGrid(false);
        table1.setIntercellSpacing(new Dimension(0, 0));
        table1.setBackground(new Color(216, 191, 216));
        table1.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane2 = new JScrollPane(table1);

        JTable table2 = new JTable(teamPlayers(2), columnName);
        table2.setShowGrid(false);
        table2.setIntercellSpacing(new Dimension(0, 0));
        table2.setBackground(new Color(255, 192, 203));
        table2.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane3 = new JScrollPane(table2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createTitledBorder("Playing Current Game"));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // Constructing JPanel 1 and 2 with GridLayout of 1 row and 1 column
        subPanel1 = new JPanel();
        subPanel1.setBorder(BorderFactory.createTitledBorder("Team 1"));
        subPanel1.setLayout(new GridLayout(1, 2));
        subPanel1.add(scrollPane2);

        subPanel2 = new JPanel();
        subPanel2.setBorder(BorderFactory.createTitledBorder("Team 2"));
        subPanel2.setLayout(new GridLayout(1, 2));
        subPanel2.add(scrollPane3);

        subPanel3 = new JPanel();
        subPanel3.setBorder(BorderFactory.createTitledBorder("Current Game Action"));
        subPanel3.setLayout(new GridLayout(1, 1));
        
        model = new DefaultTableModel(); 
        model.addColumn("Action");
        JTable gameLog = new JTable(model);
        //keeps scroll bar at the bottom
        gameLog.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                gameLog.scrollRectToVisible(gameLog.getCellRect(gameLog.getRowCount()-1, 0, true));
            }
        });

        gameLog.setShowGrid(false);
        gameLog.setDefaultEditor(Object.class, null);
        subPanel3.add(gameLog);

        JScrollPane scrollPane1 = new JScrollPane(gameLog);
        subPanel3.add(scrollPane1);

        // Team 1
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 310;
        c.ipadx = 400;
        c.weightx = 0.0;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(subPanel1, c);
        // Team 2
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 310;
        c.ipadx = 400;
        c.weightx = 0.0;
        c.gridx = 1;
        c.gridy = 0;
        mainPanel.add(subPanel2, c);
        // Game log
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 200;
        c.ipadx = 800;
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(subPanel3, c);
        // Countdown display
        JPanel countDownPanel = gameTime();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 50;
        c.ipadx = 800;
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        mainPanel.add(countDownPanel, c);

        // Adding JPanel 1 and 2 to main JPanel
        frame.add(mainPanel);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }

    public static void addRow(){
        model.addRow(new Object[]{App.traffic});
    }
}
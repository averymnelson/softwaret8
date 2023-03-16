package test;

import javax.swing.*;
import java.awt.*;

public class playActionDisplay extends JPanel {
    public String[][] team1Players = new String[16][2];
    public String[][] team2Players = new String[16][2];

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

    public void createGUI() {
        JPanel mainPanel, subPanel1, subPanel2, subPanel3, displayCountdown;
        JFrame frame = new JFrame("Play Action Display");
        JScrollPane scrollPane1 = new JScrollPane();
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

        // do we want this printing everyone as part of the test or just one to show it works?
        JTextField gameLogs = new JTextField(team1Players[0][0] + " hit " + team2Players[0][0]);
        gameLogs.setEditable(false);
        // gameLogs.setHorizontalAlignment(JTextField.NORTH_WEST);

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
        subPanel3.add(gameLogs);

        // NEW :::: Adding a count down timer to top of frame
        displayCountdown = new JPanel();
        displayCountdown.setBorder(BorderFactory.createTitledBorder("Time Remaining:"));
        displayCountdown.setLayout(new GridLayout(1, 1));
        Component countDownTimer;
        timerTest count = new timerTest(6,0);
        //JTextField gameCount = new JTextField( "Time" + (timerTest c = new timerTest(6,0)));
        //gameCount.setEditable(false);
        //displayCountdown.add(new timerTest(6, 0));
        displayCountdown.add(countDownTimer);
        ((timerTest) countDownTimer).countdownTest();

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
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 100;
        c.ipadx = 100;
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        mainPanel.add(displayCountdown, c);

        // Adding JPanel 1 and 2 to main JPanel
        subPanel3.add(scrollPane1);
        frame.add(mainPanel);
        frame.setSize(1000, 636);
        frame.setVisible(true);
    }
}
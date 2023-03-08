package test;

import javax.swing.*;
// import javax.swing.border.TitledBorder;
// import javax.swing.undo.StateEditable;
import java.awt.*;

public class playActionDisplay extends JPanel {
    public String[][] team1Players = new String[16][2];
    public String[][] team2Players = new String[16][2];

    public playActionDisplay() {
    }

    public String[][] teamPlayers(int team){
        for(int i = 0; i < 15; i++){
            if(playerEntry.team1Players[i][0] != null && playerEntry.team1Players[i][1] != null){
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

        if(team == 1)
            return team1Players;
        if(team == 2)
            return team2Players;
        else
            return null;
    }

    public void createGUI() {
        JPanel mainPanel, subPanel1, subPanel2, subPanel3;
        JFrame frame = new JFrame("Play Action Display");
        JScrollPane scrollPane1 = new JScrollPane();
        String[] columnName = {"Code Name", "Score"};

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
        //Figure out how to set table to uneditable
        //above is done

        // JTextField team1Scores = new JTextField("Score!");
        // team1Scores.setEditable(false);
        // team1Scores.setHorizontalAlignment(JTextField.RIGHT);

        //panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Edit Current Game", TitledBorder.CENTER, TitledBorder.TOP));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createTitledBorder("Playing Current Game"));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // Constructing JPanel 1 and 2 with GridLayout of 1 row and 1 column
        subPanel1 = new JPanel();
        subPanel1.setBorder(BorderFactory.createTitledBorder("Team 1"));
        subPanel1.setLayout(new GridLayout(1, 2));
        //subPanel1.add(team1Scores);
        subPanel1.add(scrollPane2);
        
        subPanel2 = new JPanel();
        subPanel2.setBorder(BorderFactory.createTitledBorder("Team 2"));
        subPanel2.setLayout(new GridLayout(1, 2));
        subPanel2.add(scrollPane3);

        subPanel3 = new JPanel();
        subPanel3.setBorder(BorderFactory.createTitledBorder("Current Game Action"));
        subPanel3.setLayout(new GridLayout(1, 1));
        // JScrollPane scrPane = new JScrollPane(subPanel3);
        // JScrollPane scrPane2 = new JScrollPane(subPanel3);
        // add(scrPane); // similar to getContentPane().add(scrPane);
        // add(scrPane2);
        // // Now, you can add whatever you want to the container


        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 255;
        c.ipadx = 400;
        c.weightx = 0.0;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(subPanel1, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 255;
        c.ipadx = 400;
        c.weightx = 0.0;
        c.gridx = 1;
        c.gridy = 0;
        mainPanel.add(subPanel2, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 235;
        c.ipadx = 800;
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(subPanel3, c);

        // Adding JPanel 1 and 2 to main JPanel
        subPanel3.add(scrollPane1);
        frame.add(mainPanel);
        frame.setSize(1000, 636);
        frame.setVisible(true);

        // mainPanel = new JPanel();
        // frame.add(mainPanel);
        // frame.setSize(1000, 300);
        // frame.setFocusable(true);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setVisible(true);

        // mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Playing Current Game",
        //     TitledBorder.CENTER, TitledBorder.TOP));
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // mainPanel.setBorder(BorderFactory.createTitledBorder("Edit Current Game"));

        // subPanel1 = new JPanel();
        // subPanel1.setBorder(BorderFactory.createTitledBorder("Team 1"));

        // subPanel2 = new JPanel();
        // subPanel2.setBorder(BorderFactory.createTitledBorder("Team 1 Log"));

        // subPanel3 = new JPanel();
        // subPanel3.setBorder(BorderFactory.createTitledBorder("Team 2"));

        // subPanel4 = new JPanel();
        // subPanel4.setBorder(BorderFactory.createTitledBorder("Team 2 Log"));

        // JScrollPane scrollPane1 = new JScrollPane();
        // JScrollPane scrollPane2 = new JScrollPane();

        // subPanel1.add(scrollPane1);
        // subPanel2.add(scrollPane2);
        // mainPanel.add(subPanel1);
        // mainPanel.add(subPanel2);
        // mainPanel.add(subPanel3);
        // //mainPanel.add(subPanel4);

        // frame.add(mainPanel);
        // frame.setSize(1000, 636);
        // frame.setVisible(true);
        // //repaint is currently doing nothing
        // subPanel1.repaint();

        // GroupLayout layout = new GroupLayout(mainPanel);
        // mainPanel.setLayout(layout);
        // layout.setAutoCreateGaps(true);
        // layout.setAutoCreateContainerGaps(true);
        // GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        // hGroup.addGroup(layout.createParallelGroup().addComponent(subPanel1).addComponent(subPanel2));
        // hGroup.addGroup(layout.createParallelGroup().addComponent(subPanel3));
        // layout.setHorizontalGroup(hGroup);
        // // GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        // // vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(subPanel1)
        // //     .addComponent(subPanel3));
        // // vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(subPanel2)
        // //     .addComponent(subPanel4));
        // //layout.setVerticalGroup(vGroup);
    }
    //adjust bottom two tables to scroll

    //adjust top two to pull from database list players
    //add points field should stay 0 though
    public void paintComponent(Graphics g){
        super.paintComponent(g); 
        g.drawString("string literal or a string variable", 0,10);
        g.setColor(new Color(227, 115, 131));
        g.fillRect(0,0,this.getWidth(), this.getHeight());

        //in game.java from mario (in the run function):
        //view.repaint();
        //in view.java from mario:
        // public void paintComponent(Graphics g){
        //     g.setColor(new Color(128,255,255));
        //     scrollPos=model.mario.x-100;
        //     g.fillRect(0,0,this.getWidth(), this.getHeight());
        //     g.setColor(Color.gray);
        //     g.drawLine(0, 596, 2000, 596);
        //     for(int i=0; i<model.sprites.size(); i++){
        //         model.sprites.get(i).draw(g, scrollPos);
        }

        public void scrollPanels(){
        // subpanels 3 and 4 will be the scrolling panels
        }
    }
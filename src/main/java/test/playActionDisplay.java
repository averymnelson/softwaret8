package test;

//import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Graphics;

public class playActionDisplay extends JPanel {

    public static int ID;
    public static String fName, lName, codeName;
    public String[][] team1Players = new String[15][2];
    public String[][] team2Players = new String[15][2];

    public playActionDisplay() {
    }

    public void createGUI() {
        JPanel mainPanel, subPanel1, subPanel2, subPanel3, subPanel4;
        JFrame frame = new JFrame();
        mainPanel = new JPanel();
        frame.add(mainPanel);
        frame.setSize(1000, 300);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Playing Current Game",
            TitledBorder.CENTER, TitledBorder.TOP));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.setBorder(BorderFactory.createTitledBorder("Edit Current Game"));

        subPanel1 = new JPanel();
        subPanel1.setBorder(BorderFactory.createTitledBorder("Team 1"));

        subPanel2 = new JPanel();
        subPanel2.setBorder(BorderFactory.createTitledBorder("Team 1 Log"));

        subPanel3 = new JPanel();
        subPanel3.setBorder(BorderFactory.createTitledBorder("Team 2"));

        subPanel4 = new JPanel();
        subPanel4.setBorder(BorderFactory.createTitledBorder("Team 2 Log"));

        JScrollPane scrollPane1 = new JScrollPane();
        JScrollPane scrollPane2 = new JScrollPane();

        subPanel1.add(scrollPane1);
        subPanel2.add(scrollPane2);
        mainPanel.add(subPanel1);
        mainPanel.add(subPanel2);
        mainPanel.add(subPanel3);
        mainPanel.add(subPanel4);

        frame.add(mainPanel);
        frame.setSize(1000, 636);
        frame.setVisible(true);
        //repaint is currently doing nothing
        subPanel1.repaint();

        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup().addComponent(subPanel1).addComponent(subPanel2));
        hGroup.addGroup(layout.createParallelGroup().addComponent(subPanel3).addComponent(subPanel4));
        layout.setHorizontalGroup(hGroup);
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(subPanel1)
            .addComponent(subPanel3));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(subPanel2)
            .addComponent(subPanel4));
        layout.setVerticalGroup(vGroup);
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
    }
package test;

import java.awt.*;
import javax.swing.*;

public class playActionDisplay {

    public static int ID;
    public static String fName, lName, codeName;
    public String team1Players[][] = new String[15][2];
    public String team2Players[][] = new String[15][2];

    public playActionDisplay() {
    }

    public void createGUI() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setSize(1000, 636);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JPanel mainPanel, subPanel1, subPanel2, subPanel3, subPanel4;
        // panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
        // "Playing Current Game",
        // TitledBorder.CENTER, TitledBorder.TOP));
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        subPanel3 = new JPanel();
        subPanel3.setBorder(BorderFactory.createTitledBorder("Team 3"));
        subPanel3.setLayout(new GridLayout(1, 1));

        subPanel4 = new JPanel();
        subPanel4.setBorder(BorderFactory.createTitledBorder("Team 4"));
        subPanel4.setLayout(new GridLayout(1, 1));

        String[] columnName = { "ID", "Codename" };
        JTable table1 = new JTable(team1Players, columnName);
        JTable table2 = new JTable(team2Players, columnName);
        JTable table3 = new JTable(team1Players, columnName);
        JTable table4 = new JTable(team2Players, columnName);
        JScrollPane scrollPane1 = new JScrollPane(table1);
        JScrollPane scrollPane2 = new JScrollPane(table2);

        // Adding JPanel 1 and 2 to main JPanel
        subPanel1.add(scrollPane1);
        subPanel2.add(scrollPane2);
        mainPanel.add(subPanel1);
        mainPanel.add(subPanel2);
        mainPanel.add(subPanel3);
        mainPanel.add(subPanel4);

        frame.add(mainPanel);
        frame.setSize(1000, 636);
        frame.setVisible(true);

        table1.setRowHeight(26);
        table1.setGridColor(Color.gray);
        table1.setBackground(Color.pink);
        table1.setRowSelectionAllowed(false);

        table2.setRowHeight(26);
        table2.setGridColor(Color.gray);
        table2.setBackground(Color.pink);
        table2.setRowSelectionAllowed(false);

        table3.setRowHeight(26);
        table3.setBackground(Color.green);
        table3.setRowSelectionAllowed(false);

        table4.setRowHeight(26);
        table4.setBackground(Color.green);
        table4.setRowSelectionAllowed(false);

        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(table1)
                        .addComponent(table2)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(table3)
                                .addComponent(table4)));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(table1)
                        .addComponent(table3)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(table2)
                                .addComponent(table4)));
    }
}

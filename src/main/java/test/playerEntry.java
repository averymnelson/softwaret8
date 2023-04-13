package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.Timer;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.sql.*;

//Class playerEntry : Creates tables and allows user to enter text
public class playerEntry extends JFrame implements ActionListener, KeyListener, TableModelListener {

    // Instance variables/constants
    public static int ID;
    public static String fName, lName, codeName;
    public static String team1Players[][] = new String[15][2];
    public static String team2Players[][] = new String[15][2];
    private TableModel model;
    private TableModel model2;
    String url = "jdbc:postgresql://ec2-3-219-213-121.compute-1.amazonaws.com:5432/defdh3biejj702?sslmode=require&user=sennggnbqaumyv&password=298b65e800749214bde557c4e55d199a827fb55d7a29b3e61eb79f67737e839d";
    JPanel panel = new JPanel();
    JPanel mainPanel, subPanel1, subPanel2;
    JFrame frame = new JFrame("Entry Terminal");

    // use these variables to control how long the timer runs
    int min = 0;
    int sec = 30;
    int delay = (min * 60 + sec + 1) * 1000; // don't adjust this

    // Constructor
    public playerEntry() {
    }

    // Create a table for player entry
    public void createGUI() {
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

        subPanel1.add(scrollPane1);
        subPanel2.add(scrollPane2);
        mainPanel.add(subPanel1);
        mainPanel.add(subPanel2);

        frame.add(mainPanel);
        frame.setSize(1000, 636);
        mainPanel.add(bStart, c);
        addKeyBind(mainPanel, "F5");
        addKeyBind(mainPanel, "Enter");
        frame.setVisible(true);

        table1.setRowHeight(26);
        table1.setGridColor(Color.gray);
        table1.setBackground(new Color(216, 191, 216));
        table1.setRowSelectionAllowed(false);
         model = table1.getModel();
         model.addTableModelListener(this);

        table2.setRowHeight(26);
        table2.setGridColor(Color.gray);
        table2.setBackground(new Color(255, 192, 203));
        table2.setRowSelectionAllowed(false);
         model2 = table2.getModel();
         model2.addTableModelListener(this);
    }

    private void addKeyBind(JComponent contentPane, String key) {
            InputMap inputMap = contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = contentPane.getActionMap();
            inputMap.put(KeyStroke.getKeyStroke(key), "Start Game");
            actionMap.put("Start Game", startGame);
    }

    // this is called when f5 is pressed
    Action startGame = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println((delay / 1000 - 1) + " Seconds to game start");
            timerTest test = new timerTest(min, sec);
            test.createFrame();
            test.countdownTest();

            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    playActionDisplay display = new playActionDisplay();
                    display.createGUI();
                    frame.setVisible(false);
                    frame.dispose();
                }
            };
            Timer timer = new Timer(delay, taskPerformer);
            timer.setRepeats(false);
            timer.start();
        }
    };

    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}

    // This method is called when the JBotton is clicked
    public void actionPerformed(ActionEvent ae) {
        insertDB();
        System.out.println((delay / 1000 - 1) + " Seconds to game start");
        timerTest test = new timerTest(min, sec);
        test.createFrame();
        test.countdownTest();

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                playActionDisplay display = new playActionDisplay();
                display.createGUI();
                frame.setVisible(false);
                frame.dispose();
            }
        };
        Timer timer = new Timer(delay, taskPerformer);
        timer.setRepeats(false);
        timer.start();
    }

    public void insertDB() {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connected to the database! Inserting Players.");
                for (int i = 0; i < 15; i++) {
                    if (team1Players[i][0] != null && team1Players[i][1] != null) {
                        String sql = "INSERT INTO player (id, first_name, last_name, codename) VALUES ("
                                + team1Players[i][0] + ", 'testfName', 'testlName', '" + team1Players[i][1] + "')";
                        // String sql = "DELETE FROM player";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.executeUpdate();
                        pstmt.close();
                    }
                    if (team2Players[i][0] != null && team2Players[i][1] != null) {
                        String sql = "INSERT INTO player (id, first_name, last_name, codename) VALUES ("
                                + team2Players[i][0] + ", 'testfName', 'testlName', '" + team2Players[i][1] + "')";
                        // String sql = "DELETE FROM player";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.executeUpdate();
                        pstmt.close();
                    }
                }
                conn.close();
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewTable() {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                String query = "select ID, CODENAME from PLAYER";
                try (Statement stmt = conn.createStatement()) {
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        String codename = rs.getString("CODENAME");
                        String id = rs.getString("ID");
                        for (int i = 0; i < 15; i++) {
                            if (team1Players[i][0] != null) {
                                if (id.equals(team1Players[i][0]))
                                    team1Players[i][1] = codename;
                            }
                        }
                        for (int i = 0; i < 15; i++) {
                            if (team2Players[i][0] != null) {
                                if (id.equals(team2Players[i][0]))
                                    team2Players[i][1] = codename;
                            }
                        }
                    }
                } catch (SQLException e) {
                    System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewDATA() {
        try (Connection con = DriverManager.getConnection(url)) {
            if (con != null) {
                String query = "select ID, CODENAME from PLAYER";
                try (Statement stmt = con.createStatement()) {
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        String Name = rs.getString("CODENAME");
                        int ID = rs.getInt("ID");
                        System.out.println(ID + "_" + Name);
                    }
                } catch (SQLException e) {
                    System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     @Override
     public void tableChanged(TableModelEvent e) {
     viewTable();
     }
}

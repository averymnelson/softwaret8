package test;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.awt.event.ActionEvent;
//creates play action display, music, handles scoring
public class playActionDisplay extends JPanel {
    public static String[][] team1Players = new String[16][2];
    public static String[][] team2Players = new String[16][2];
    public static String score = "0";
    public static String teamScore = "0";
    public static boolean gameOver = false;
    public static JTable table1;
    public static JTable table2;
    public static JPanel subPanel3;
    public static DefaultTableModel model;
    public static Clip clip;
    public static AudioInputStream audioInputStream;
    int minutes = 6;
    int seconds = 0;
    private JLabel l;

    public playActionDisplay() {
    }

    public String[][] teamPlayers(int team) {
        //sets team and individual scores to zero
        for (int i = 0; i < 15; i++) {
            if (playerEntry.team1Players[i][0] != null && playerEntry.team1Players[i][1] != null) {
                team1Players[i][0] = playerEntry.team1Players[i][1];
                team1Players[i][1] = score;
                team2Players[i][0] = playerEntry.team2Players[i][1];
                team2Players[i][1] = score;
            }
        }

        team1Players[15][0] = "Total Score";
        team1Players[15][1] = score;
        team2Players[15][0] = "Total Score";
        team2Players[15][1] = score;

        if (team == 1){
            return team1Players;
        }if (team == 2){
            return team2Players;
        }else{
            return null;
        }
    }

    public JPanel gameTime(){
        //adds time to panel
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

    public void countdownTest() {
        //starts countdown
        int delay = 1000;
        Timer t = new Timer(delay, null);
        t.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                seconds--;
                labelDisplay(minutes, seconds);
                //changes number color to red
                if(seconds < 11 && minutes == 0){
                    l.setForeground(Color.RED);
                }
                //carrying over when minute changes
                if (seconds == -1) {
                    seconds = 59;
                    minutes--;
                    labelDisplay(minutes, seconds);
                }
                //stops timer
                if (minutes == 0 && seconds == 0) {
                    gameOver = true;
                    t.stop();
                    try {
                        audioInputStream.close();
                        clip.close();
                        clip.stop();
                    } catch (Exception e) {
                    }
                    labelDisplay(minutes, seconds);
                    return;
                }
            }
        });
        t.start();
    }

    //formats the timer display
    public String labelDisplay(int min, int sec)
    {
        String s;
        if(min < 10){
            if(sec < 10){
                l.setText("0" + min + ":0" + sec);
                s = "0" + min + ":0" + sec;
            }else{
                l.setText("0" + min + ":" + sec);
                s = "0" + min + ":" + sec;
            }
        }else if(sec < 10){
            l.setText(min + ":0" + sec);
            s = min + ":0" + sec;
        }else{
            l.setText(min + ":" + sec);
            s = min + ":" + sec;
        }if(gameOver == true){
            l.setText("Game Over!");
            s = "Game Over!";
        }
        return s;
    }

    public void createGUI() {
        JPanel mainPanel, subPanel1, subPanel2;
        JFrame frame = new JFrame("Play Action Display");
        String[] columnName = { "Code Name", "Score" };

        table1 = createTable(columnName, 1);
        table1.setBackground(new Color(216, 191, 216));
        JScrollPane scrollPane2 = new JScrollPane(table1);

        table2 = createTable(columnName, 2);
        table2.setBackground(new Color(255, 192, 203));
        JScrollPane scrollPane3 = new JScrollPane(table2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createTitledBorder("Playing Current Game"));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
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
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.ipady = 255;
        grid.ipadx = 400;
        grid.weightx = 0.0;
        grid.gridx = 0;
        grid.gridy = 0;
        mainPanel.add(subPanel1, grid);
        // Team 2
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.ipady = 255;
        grid.ipadx = 400;
        grid.weightx = 0.0;
        grid.gridx = 1;
        grid.gridy = 0;
        mainPanel.add(subPanel2, grid);
        // Game log
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.ipady = 300;
        grid.ipadx = 800;
        grid.weightx = 0.0;
        grid.gridwidth = 2;
        grid.gridx = 0;
        grid.gridy = 1;
        mainPanel.add(subPanel3, grid);
        // Countdown display
        JPanel countDownPanel = gameTime();
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.ipady = 50;
        grid.ipadx = 800;
        grid.weightx = 0.0;
        grid.gridwidth = 2;
        grid.gridx = 0;
        grid.gridy = 2;
        mainPanel.add(countDownPanel, grid);
        // Adding JPanel 1 and 2 to main JPanel
        frame.add(mainPanel);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        Music();
    }

    public JTable createTable(String[] columnName, int teamNum){
        JTable table = new JTable(teamPlayers(teamNum), columnName);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setDefaultEditor(Object.class, null);
        return table;
    }

    public static void addRow(){
        if(gameOver == false){
            model.addRow(new Object[]{App.traffic});
        }
    }

    public static void addScore(int playerID){
        if(gameOver == false){
            for (int i = 0; i < 15; i++){
                //check that IDs are not null
                if (playerEntry.team1Players[i][0] != null && playerEntry.team2Players[i][0] != null) {
                    int player1 = Integer.parseInt(playerEntry.team1Players[i][0]);
                    int player2 = Integer.parseInt(playerEntry.team2Players[i][0]);

                    if(player1 == playerID){
                        updateScore(i, 1);
                        team1Players[i][1] = score;
                        table1.setValueAt(score, i, 1);
                        
                        updateTeamScore(1);
                        team1Players[15][1] = teamScore;
                        table1.setValueAt(teamScore, 15, 1);
                    }else if(player2 == playerID){
                        updateScore(i, 2);
                        team2Players[i][1] = score;
                        table2.setValueAt(score, i, 1);

                        updateTeamScore(2);
                        team2Players[15][1] = teamScore;
                        table2.setValueAt(teamScore, 15, 1);
                    }
                }
            }
        }
        int t1 = Integer.parseInt(team1Players[15][1]);
        //System.out.println(t1);
        int t2 = Integer.parseInt(team2Players[15][1]);
        //System.out.println(t2);
        if (t1>t2){
            //System.out.println(team1Players[15][0]);
            if(team1Players[15][0].length()<=11){
                table1.setValueAt(team1Players[15][0].concat("*"),15,0);
            }
            //System.out.println(team1Players[15][0]);
            table2.setValueAt(team2Players[15][0].substring(0, 11),15,0);
            //System.out.println(team2Players[15][0]);
        }if (t2>t1){
            //System.out.println(team2Players[15][0]);
            if(team2Players[15][0].length()<=11){
                table2.setValueAt(team2Players[15][0].concat("*"),15,0);
            }
            //System.out.println(team2Players[15][0]);
            table1.setValueAt(team1Players[15][0].substring(0, 11),15,0);
            //System.out.println(team1Players[15][0]);
        }
        highPlayer();
    }

    private static void highPlayer() {
        int arr[]=new int [15];
        for (int i=0; i<15;i++){
            if (team1Players[i][0]!=null){
                arr[i]=Integer.parseInt(team1Players[i][1]);
            }
        }
        int max=0;
        int indexmax=0;
        for (int i=0; i<15;i++){
            if(arr[i]>max){
                max=arr[i];
                indexmax=i;
            }
        }

        int arr2[]=new int [15];
        for (int i=0; i<15;i++){
            if (team2Players[i][0]!=null){
            arr[i]=Integer.parseInt(team2Players[i][1]);
            }
        }
        int max2=0;
        int indexmax2=0;
        for (int i=0; i<15;i++){
            if(arr2[i]>max){
                max2=arr2[i];
                indexmax2=i;
            }
        }
        for (int i=0; i<15; i++){
            if (team2Players[i][0]!=null){
            String[] arrOfStr = team2Players[i][0].split("\\*");
            table2.setValueAt(arrOfStr[0],i,0);
            }
        }
        for (int i=0; i<15; i++){
            if (team1Players[i][0]!=null){
            String[] arrOfStr = team1Players[i][0].split("\\*");
            table1.setValueAt(arrOfStr[0],i,0);
            }
        }
        if (max>max2){
            //System.out.println(team1Players[15][0]);
            
            int len=team1Players[indexmax][0].length();
            if(team1Players[indexmax][0].length()<=len){
                table1.setValueAt(team1Players[indexmax][0].concat("*"),indexmax,0);
            }
            
        }else{
            //System.out.println(team2Players[15][0]);
            int len=team2Players[indexmax2][0].length();
            if(team2Players[indexmax2][0].length()<=len){
                table2.setValueAt(team2Players[indexmax2][0].concat("*"),indexmax2,0);
            }
        }
    }

    public static void updateScore(int player, int team){
        int updatedScore = 0;
        if(team == 1)
            updatedScore = Integer.parseInt(team1Players[player][1]);
        else if(team == 2)
            updatedScore = Integer.parseInt(team2Players[player][1]);
        updatedScore += 10;
        score = "" + updatedScore;
    }

    public static void updateTeamScore(int team){
        int updatedteamScore = 0;
        for(int i = 0; i < 15; i++){
            if(team == 1 && playerEntry.team1Players[i][0] != null)
                updatedteamScore = updatedteamScore + Integer.parseInt(team1Players[i][1]);
            if(team == 2 && playerEntry.team2Players[i][0] != null)
                updatedteamScore = updatedteamScore + Integer.parseInt(team2Players[i][1]);
        }
        teamScore = "" + updatedteamScore;
    }

    public static void Music() {
		try{
		int sample = (int) (Math.random() * (8) + 1);
		File file = new File("src\\main\\java\\test\\audio\\Track0" + sample + ".wav");
		if (file.exists()){
			audioInputStream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
	    }else{
			System.out.println("Can't find audio file");
		}
        }catch (Exception e){
        }
    }
}
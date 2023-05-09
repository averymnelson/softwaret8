package test;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
//full setup
public final class App {
	public static String traffic;
	public static String trafficprt;
	public static String hitW;
	public static String hitL;

    public static void ShowLogo() {
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("src/main/java/test/logo.jpg");
        JLabel label = new JLabel(icon);
		frame.add(label);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        new Timer(3_000, (e) -> {
            frame.setVisible(false);
            frame.dispose();
        }).start();
    }

    /**
     * Runs Laser Tag System.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws IOException{
        ShowLogo();
        playerEntry entryScreen = new playerEntry();
        entryScreen.createGUI();

		DatagramSocket ds = new DatagramSocket(7501);
		byte[] receive = new byte[65535];
		DatagramPacket DpReceive = null;

		while (true){
			DpReceive = new DatagramPacket(receive, receive.length);
			ds.receive(DpReceive);
			traffic = data(receive).toString();
			if (traffic.equals("bye")){
				System.out.println("Client sent bye.....EXITING");
				break;
			}
			trafficprt = updatetraffic(traffic);
			System.out.println(trafficprt);
			if(playActionDisplay.model != null){
				playActionDisplay.addRow();
				String[] hits = traffic.split(" hit ");
				for (int i = 0; i< hits.length; i = i + 2){
					int playerID = Integer.parseInt(hits[i]); //contain player ID that got a hit
					playActionDisplay.addScore(playerID);
				}
			}
			receive = new byte[65535];
		}
		ds.close();
    }

	private static String updatetraffic(String trafficIN) {
		String updated;
		String[] play = trafficIN.split(" hit ");
		System.out.println(play[0]+" + "+play[1]);
		for (int i=0;i<15; i++){
			if(playerEntry.team1Players[i][0]!=null){
				//System.out.println(playerEntry.team1Players[i][0]);
				if (play[0].equals(playerEntry.team1Players[i][0])){
					hitW=playerEntry.team1Players[i][1];
					System.out.println("set winner to "+hitW);
				}
				if(play[1].equals(playerEntry.team1Players[i][0])){
					hitL=playerEntry.team1Players[i][1];
					System.out.println("set loser to "+hitL);
				}
			}
			if(playerEntry.team2Players[i][0]!=null){
				if (play[0].equals(playerEntry.team2Players[i][0])){
					hitW=playerEntry.team2Players[i][1];
					System.out.println("set winner to "+hitW);
				}
				if (play[1].equals(playerEntry.team2Players[i][0])){
					hitL=playerEntry.team2Players[i][1];
					System.out.println("set loser to "+hitL);
				}
			}
		}
		System.out.println(hitW + " & "+hitL);
		updated=hitW+" hit "+hitL;
		return updated;
	}

	public static StringBuilder data(byte[] a)
	{
		if (a == null){
			return null;
		}
		StringBuilder ret = new StringBuilder();
		int i = 0;
		while (a[i] != 0){
			ret.append((char) a[i]);
			i++;
		}
		return ret;
	}
}

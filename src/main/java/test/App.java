package test;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
//full setup
public final class App {
	public static String traffic;

    public static void ShowLogo() {
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("src/main/java/test/logo.jpg");
        JLabel label = new JLabel(icon);
        frame.add(label);
        frame.setSize(1000, 636);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			System.out.println(traffic);
			new Timer(30_000, (e) -> {
				if (!traffic.equals("bye")){
					playActionDisplay.addRow();
					String[] hits = traffic.split(" hit ");
					for (int i = 0; i< hits.length; i = i + 2){
						int playerID = Integer.parseInt(hits[i]); //contain player ID that got a hit
						playActionDisplay.addScore(playerID);
					}
				}
			}).start();
			receive = new byte[65535];
		}
		ds.close();
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
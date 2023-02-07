import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;

class View extends JPanel
{
	BufferedImage logo_image;
	View(){
		try{
			this.logo_image = ImageIO.read(new File("logo.jpg"));
		}
		catch(Exception e) {
    		e.printStackTrace(System.err);
    		System.exit(1);
		}
	}

	public void paintComponent(Graphics g){
		//g.setColor(new Color(128, 255, 255));
    	g.fillRect(0, 0, 1000, 636);
		g.drawImage(logo_image, 0, 0, null);
	}

	static BufferedImage loadImage(String filename){
	 	BufferedImage im = null;
        try{
			im = ImageIO.read(new File(filename));
		}
		catch(Exception e) {
    		e.printStackTrace(System.err);
    		System.exit(1);
		}
	 	return im;
    }
}

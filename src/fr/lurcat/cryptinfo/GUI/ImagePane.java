package fr.lurcat.cryptinfo.GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePane extends JPanel {
	
	private String image;

	public ImagePane(String image){
		this.image = image;
	}
	
	public void paintComponent(Graphics g){
		try{
			Image img = ImageIO.read(new File(image));
			g.drawImage(img, 0, 0, this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}

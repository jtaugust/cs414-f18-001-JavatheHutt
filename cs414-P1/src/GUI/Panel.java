package GUI;

import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Panel {
	public static void fillFrame(JFrame frame, int width, int height, String type, String path) {
		//paint the panel as the background jpg
		JPanel background = new JPanel() {
			BufferedImage image = Helpers.getImage(path);
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, width, height, this);
			}
		};
		//set the panel on the frame, this will cover the entire frame and create the background
		frame.setContentPane(background);
		//set the background layout
		background.setLayout(new BorderLayout());

		//determine which pane, generate it with template 
		if (type == "Login") {
			Templates.LoginTemplate.generateLogin(background);
		}else if (type == "Main"){
			//TODO
		}else {
			//error
		}
	}
	
	public static JPanel panel() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		return panel;
	}
}

package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class Label {
	public static JLabel getImageLabel(String location){
		BufferedImage image = Helpers.getImage(location);
		JLabel label = new JLabel(new ImageIcon(image));
		return label;
	}
	
	public static JLabel createLabel(String name) {
		JLabel label = new JLabel(name);

		return label;
	}
	
	
}

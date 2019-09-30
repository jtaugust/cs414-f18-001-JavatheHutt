package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class Label {
	
	//create a label with the desired image
	public static JLabel getImageLabel(String location){
		BufferedImage image = Helpers.getImage(location);
		JLabel label = new JLabel(new ImageIcon(image));
		return label;
	}
	
	//create a label for popup errors
	public static JLabel errorLabel(String error, Color color){
		JLabel label = new JLabel(error);
		label.setFont(new Font("Verdana",1, 18));
		label.setForeground(color);
		return label;
	}
}

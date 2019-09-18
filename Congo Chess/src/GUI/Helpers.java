package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JTextField;

public class Helpers {
	public static BufferedImage getImage(String location){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(location));
		}catch (IOException e) {
			//do nothing, should never reach here
		}
		return image;
	}
	
	public static JTextField textField(String text, Dimension size, Font font) {
		JTextField field = new JTextField(text);
		field.setPreferredSize(size);
		field.setMaximumSize(size);
		field.setMinimumSize(size);
		field.setAlignmentX(Component.CENTER_ALIGNMENT);
		field.setFont(font);
		return field;
	}
	
	public static Component Spacer(int width, int height) {
		return Box.createRigidArea(new Dimension(width, height));
	}
	
	public static Font newFont(int fontSize) {
		return new Font("SansSerif", Font.BOLD, fontSize);
	}
	
	public static Component Button(String name, Dimension size) {
		Button button = new Button(name);
		button.setMaximumSize(size);
		return button;
	}
}

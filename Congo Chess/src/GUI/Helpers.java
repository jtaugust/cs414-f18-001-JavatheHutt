package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Helpers {
	
	//gets the requested image from its location and returns it
	public static BufferedImage getImage(String location){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(location));
		}catch (IOException e) {
			//do nothing, should never reach here
		}
		return image;
	}
	
	//resizes an image with the provided size dimension
	public static BufferedImage resizeImg(BufferedImage img, Dimension size) {
		int width = size.width, height = size.height;
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = newImage.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    return newImage;
	}
	
	//creates a JTextField with the name, fills the field with text, sets the size, and sets the font of the text
	public static JTextField textField(String name, String text, Dimension size, Font font) {
		JTextField field = new JTextField(text);
		field.setPreferredSize(size);
		field.setMaximumSize(size);
		field.setMinimumSize(size);
		field.setAlignmentX(Component.CENTER_ALIGNMENT);
		field.setFont(font);
		field.setName(name);
		field.setText(text);

		return field;
	}
	
	//creates a blank box for spacing panel content
	public static Component spacer(int width, int height) {
		return Box.createRigidArea(new Dimension(width, height));
	}
	
	//creates a new bolded font of SansSerif, of proveded size
	public static Font newFont(int fontSize) {
		return new Font("SansSerif", Font.BOLD, fontSize);
	}
	
	//create a button with the provided name
	public static JButton button(String name) {
		JButton button = new JButton(name);
		return button;
	}
	
	//create a button with the provided name of the provided size
	public static JButton button(String name, Dimension size) {
		JButton button = button(name);
		button.setPreferredSize(size);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		return button;
	}
	
	//create a button with an image instead of text, with the provided size
	public static JButton button(BufferedImage img, Dimension imgSize) {
		JButton button = new JButton();
		img = resizeImg(img, imgSize);
		button.setIcon(new ImageIcon(img));
		return button;
	}
	
	//creates a textField with the given name, will have no text, with a width of 200, height of 50, and a font size of 22
	public static JTextField newTextField(String name){
		JTextField field = textField(name,"", new Dimension(200, 50), newFont(22));
		return field;
	}

	 //creates a textField with the name, fills in the field with the text given,width of 200, height of 50, and font size of 22
	public static JTextField newTextField(String name, String text){
		JTextField field = textField(name, text, new Dimension(200, 50), newFont(22));
		return field;
	}
	
	//creates a textField with the name, fills the field with text, sets the size, and defaults to a font size of 22
	public static JTextField newTextField(String name, String text, Dimension size){
		JTextField field = textField(name,text, size, newFont(22));
		return field;
	}
}

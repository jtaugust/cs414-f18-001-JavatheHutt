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
	
	public static BufferedImage getImage(String location){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(location));
		}catch (IOException e) {
			//do nothing, should never reach here
		}
		return image;
	}
	
	public static BufferedImage resizeImg(BufferedImage img, Dimension size) {
		int width = size.width, height = size.height;
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = newImage.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    return newImage;
	}
	
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
	
	public static Component spacer(int width, int height) {
		return Box.createRigidArea(new Dimension(width, height));
	}
	
	public static Font newFont(int fontSize) {
		return new Font("SansSerif", Font.BOLD, fontSize);
	}
	
	public static JButton button(String name) {
		JButton button = new JButton(name);
		return button;
	}
	
	public static JButton button(String name, Dimension size) {
		JButton button = button(name);
		button.setPreferredSize(size);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		return button;
	}
	
	public static JButton button(BufferedImage img, Dimension imgSize) {
		JButton button = new JButton();
		img = resizeImg(img, imgSize);
		button.setIcon(new ImageIcon(img));
		return button;
	}
	
	public static JTextField newTextField(String name, String text){ //creates a textField with ID as given name, fills in the field with the text given,width of 200, height of 50, and font size of 22
		JTextField field = textField(name, text, new Dimension(400, 50), newFont(22));
		//clear default fields
		field.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if(field.getText().equals(text)) {
					field.setText(""); 
				}
			}
			public void focusLost(FocusEvent e) {
				if(field.getText().isEmpty()) {
					field.setText(text);
				
				}
				
			}
		}); 
		return field;
	}
	
	public static JTextField newTextField(String name, String text, Dimension size){
		JTextField field = textField(name,text, size, newFont(22));
		return field;
	}
	
	public static JTextField newTextField(String name, String text, Dimension size, Font font){
		JTextField field = textField(name,text, size, font);
		return field;
	}

}

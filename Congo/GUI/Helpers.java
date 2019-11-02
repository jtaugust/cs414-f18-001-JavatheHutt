package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

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
		field.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,6,0,0,new Color(79,175,255)),new MatteBorder(8,8,8,8, Color.white)));
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
	
	public static JPasswordField newPasswordField(int size) {
		JPasswordField field = new JPasswordField("Password", size);
		field.setEchoChar((char)0);
		Dimension s = new Dimension(400, 50);
		field.setPreferredSize(s);
		field.setFont(newFont(22));
		field.setMaximumSize(s);
		field.setMinimumSize(s);
		field.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		field.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				char[] defaultPass = {'P','a','s','s','w','o','r','d'};
				if(Arrays.equals(field.getPassword(), defaultPass)) {
					field.setText("");
					field.setEchoChar('*');
				}
			}
			public void focusLost(FocusEvent e) {
				if(field.getPassword().length == 0) {
					field.setText("Password");
					field.setEchoChar((char)0);
				}

			}
		}); 
		return field;
	}
	
	public static JPasswordField newPasswordField(int size, String text) {
		JPasswordField field = new JPasswordField(text, size);
		field.setEchoChar((char)0);
		Dimension s = new Dimension(400, 50);
		field.setPreferredSize(s);
		field.setFont(newFont(22));
		field.setMaximumSize(s);
		field.setMinimumSize(s);
		field.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		field.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				char[] defaultPass = text.toCharArray();
				if(Arrays.equals(field.getPassword(), defaultPass)) {
					field.setText("");
					field.setEchoChar('*');
				}
			}
			public void focusLost(FocusEvent e) {
				if(field.getPassword().length == 0) {
					field.setText(text);
					field.setEchoChar((char)0);
				}

			}
		}); 
		return field;
	}

	//creates a textField with the name, fills the field with text, sets the size, and defaults to a font size of 22
	public static JTextField newTextField(String name, String text, Dimension size){
		JTextField field = textField(name,text, size, newFont(22));
		return field;
	}
}
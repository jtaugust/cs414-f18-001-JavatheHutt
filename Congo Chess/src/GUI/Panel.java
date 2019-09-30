package GUI;

import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.*;

import App.Application;
import Screens.LoginScreen;

public class Panel {
	
	private static JPanel workingPanel = new JPanel(), workingPanelBackground = new JPanel();
	
	//generates the desired template
	public static void createPanels(JFrame frame, int template) {
		switch (template) {
			case 1: Templates.InitialTemplate.generateInitial(frame); break; //initial
			case 2: Templates.MainTemplate.generateMain(frame, true); break; //main
			case 3: Templates.AccountTemplate.generateAccount(frame); break; //Account
			default: break; //error, should never reach here
		}
	}
	
	//returns a background panel of the provided picture
	public static JPanel setBackground(String path) { //will cover the panel using the frames width and height
		int width = Frame.getWidth(), height = Frame.getHeight();

		//paint the panel as the image provided
		JPanel background = new JPanel() {
			BufferedImage image = Helpers.getImage(path);
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, width, height, this);
			}
		};
		
		return background;
	}
	
	//returns a background panel of the provided picture with the provided size
	public static JPanel setBackground(String path, Dimension size) {
		int width = size.width, height = size.height;
		//paint the panel as the image provided
		JPanel background = new JPanel() {
			BufferedImage image = Helpers.getImage(path);
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, width, height, this);
			}
		};
		return background;
	}
	
	//returns a background panel with the provided panel overlayed onto the panel
	public static JPanel setBackground(String path, JPanel panel) {
		JPanel background = new JPanel();
		background = setBackground(path);
		background.add(panel);
		return background;
	}
	
	//returns a background panel of the provided size with the provided panel overlayed onto the panel
	//NOTE: this may cause issues if the provided panel is bigger than the provided size
	public static JPanel setBackground(String path, Dimension size, JPanel panel) {
		//TODO: Error checking to make sure the provided dimension size is at least the same size as the provided panel
		JPanel background = new JPanel();
		background = setBackground(path, size);
		background.add(panel);
		return background;
	}
	
	//returns the panel that screens should be added to
	public static JPanel getWorkingPanel() {
		return workingPanel;
	}
	
	//sets the panel that screens should be added to
	public static void setWorkingPanel(JPanel panel) { //set the working panel with provided panel, will not be see-through
		workingPanel = panel;
	}
	
	//sets the panel that screens should be added to with the provided opacity
	public static void setWorkingPanel(JPanel panel, Boolean opaque) { //set the working panel with provided panel and opacity
		panel.setOpaque(opaque);
		workingPanel = panel;
	}
	
	//wipes the working panel for the next screen
	public static void clearWorkingPanel() {
		JPanel temp = new JPanel();
		//get current workingPanel opacity
		Boolean opaque = workingPanel.isOpaque();
		temp.setOpaque(opaque);
		//remove workingPanel from background
		workingPanelBackground.remove(workingPanel);
		//add the new panel to the background
		workingPanelBackground.add(temp, BorderLayout.CENTER);
		//update workingPanel
		workingPanel = temp;
	}
	
	//sets the working panel's Opacity
	public static void setWorkingPanel(Boolean opaque){
		workingPanel.setOpaque(opaque);
	}
	
	//sets the panel that is everything above the frame but below the working panel
	public static void setWorkingPanelBackground(JPanel panel){
		workingPanelBackground = panel;
	}
	
	//returns the panel that is everything above the frame but below the working panel
	public static JPanel getWorkingPanelBackground(){
		return workingPanelBackground;
	}

}

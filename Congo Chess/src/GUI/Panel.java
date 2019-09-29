package GUI;

import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.*;

import App.Application;
import Screens.LoginScreen;

public class Panel {
	
	private static JPanel workingPanel = new JPanel(), workingPanelBackground = new JPanel();
	
	public static void createPanels(JFrame frame, int template) {
		//determine which pane, generate its template
		switch (template) {
			case 1: Templates.InitialTemplate.generateInitial(frame); break; //initial
			case 2: Templates.MainTemplate.generateMain(frame, true); break; //main
			case 3: Templates.AccountTemplate.generateAccount(frame); break; //Account
			default: break; //error, should never reach here
		}
	}
	
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
	
	public static JPanel setBackground(String path, Dimension size) { //will cover the panel using the provided width and height
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
	
	public static JPanel setBackground(String path, JPanel panel) { //adds existing panel on top of background using the frames width and height
		JPanel background = new JPanel();
		background = setBackground(path);
		background.add(panel);
		return background;
	}
	
	public static JPanel setBackground(String path, Dimension size, JPanel panel) { //adds existing panel on top of background using provided width and height
		JPanel background = new JPanel();
		background = setBackground(path, size);
		background.add(panel);
		return background;
	}
	
	public static JPanel getWorkingPanel() {
		return workingPanel;
	}
	
	public static void setWorkingPanel(JPanel panel) { //set the working panel with provided panel, will not be see-through
		panel.setOpaque(true);
		workingPanel = panel;
	}
	
	public static void setWorkingPanel(JPanel panel, Boolean opaque) { //set the working panel with provided panel and opacity
		panel.setOpaque(opaque);
		workingPanel = panel;
	}
	
	public static void setWorkingPanel(Boolean opaque){
		workingPanel.setOpaque(opaque);
	}
	
	public static void clearWorkingPanel(){
		//remove workingPanel from background
		Panel.getWorkingPanelBackground().remove(Panel.getWorkingPanel());
		setWorkingPanel(new JPanel(), false);
	}
	
	public static void setWorkingPanelBackground(JPanel panel){
		workingPanelBackground = panel;
	}
	
	public static JPanel getWorkingPanelBackground(){
		return workingPanelBackground;
	}

}

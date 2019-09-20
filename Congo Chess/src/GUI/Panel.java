package GUI;

import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.*;

import Screens.Login_Screen;

public class Panel {
	
	private static JPanel workingPanel = null;
	
	public static void createPanels(JFrame frame, int template) {
		//determine which pane, generate its template
		switch (template) {
			case 1: Templates.initialTemplate.generateInitial(frame); break; //initial
			case 2: Templates.MainTemplate.generateMain(frame); break; //main
			case 3: Templates.AccountTemplate.generateAccount(frame); break; //Account
			default: break; //error, should never reach here
		}
	}
	
	public static JPanel setBackground(String path) {
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
	
	public static JPanel getWorkingPanel() {
		return workingPanel;
	}
	
	public static void setWorkingPanel(JPanel panel, Boolean opaque) {
		workingPanel = panel;
		workingPanel.setOpaque(opaque);
	}
}

package GUI;

import java.awt.BorderLayout;
import java.awt.Label;

import javax.swing.JFrame;

import App.*;
import Templates.BackgroundTemplate;
import Screens.WorkingPanel;

public class Frame {
	JFrame frame;
	
	private int width, height;
	private String Username;
	//store the GUI classes to build the application
	private BackgroundTemplate Background;
	private WorkingPanel WorkingPanel;
	
	
	public Frame() {
		frame = new JFrame();
		Username = null;
		width = 600;
		height = 800;
		Background = new BackgroundTemplate();
		WorkingPanel = new WorkingPanel();
		finalize(frame);
	}

	public void changeScreen(String screen) {
		if (screen.equals("Login") || screen.equals("Registration")) {
			Background.setTemplate(1);
		}else {
			Background.setTemplate(2);
		}
		this.width = Background.getWidth();
		this.height = Background.getHeight();
	}
	
	/*
	 * Sets the frames common settings 
	 */
	public void finalize(JFrame frame) {
		//set frame size
		frame.setSize(width, height);
		//set the start position to center of the screen
		frame.setLocationRelativeTo(null);
		//set default close action (X will close the application)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set the title
		frame.setTitle("XGames online: Congo Chess");
		//disable resize
		frame.setResizable(false);
		//make frame visible
		frame.setVisible(true);
		//add the built panels to the frame
		frame.setContentPane(Background.getBackgroundPanel());
		//frame.setContentPane(Panel.getWorkingPanelBackground());
		update();
	}
	
	private void update() {
		frame.validate();
		frame.repaint();
	}
}

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
	
	//create all initial objects
	public Frame() {
		frame = new JFrame();
		Username = null;
		width = 600;
		height = 800;
		Background = new BackgroundTemplate();
		WorkingPanel = new WorkingPanel();
		Background.setTemplate(1);
		WorkingPanel.setFrame(this);
		finalize(frame);
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
	
		//set the working panel into the background panel
		Background.getBackgroundPanel().add(WorkingPanel.getWorkingPanel());
		
		//set the background panel into the frame
		frame.setContentPane(Background.getBackgroundPanel());
		
		update();
	}
	
	private void update() {
		frame.validate();
		frame.repaint();
	}
	
	public void setUser(String username){
		if (WorkingPanel.getCurrentScreen().equals("Login")){
			this.Username = username;
		}
	}
	
	
}

package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JFrame;

import App.*;
import Templates.BackgroundTemplate;
import Screens.Screen;
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
		frame.setName("Application Window");
		Username = null;
		Background = new BackgroundTemplate();
		WorkingPanel = new WorkingPanel();
		setFrames();
		changeTemplate("Login");
	}
	
	public void changeScreen(Screen screen) {
		//set the screens information
		WorkingPanel.setScreenInfo(screen);
		
		if (!screen.name.equals("New Game")){
			//clear the working panel
			WorkingPanel.workingPanel.removeAll();
			changeTemplate(screen.name);
		
			//set the workingPanel
			WorkingPanel.screen.setScreen();
	
			WorkingPanel.updateWorking();
		}
	}
	
	private void setFrames() {
		WorkingPanel.setFrame(this);
		Background.setFrame(this);
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
		
		//set the background panel into the frame
		frame.setContentPane(Background.getBackgroundPanel());
		
		update();
	}
	
	private void update() {
		frame.validate();
		frame.repaint();
	}
	
	public void setUser(String username){
		this.Username = username;
	}
	
	public void changeTemplate(String screen) {
		if (Background.needChange(screen)) {
			if (Background.getTemplate() == 1) {
				Background.setTemplate(2);
			}else {
				Background.setTemplate(1);
			}
		//add working panel to background
		Background.getBackgroundPanel().add(WorkingPanel.getWorkingPanel());	
		}
		setFrameDimensions();
		finalize(frame);
	}
	
	public void setFrameDimensions(){
		this.width = Background.getWidth();
		this.height = Background.getHeight();
	}
	
	public String getUser(){
		return this.Username;
	}
	
	public JFrame getThisFrame(){
		return this.frame;
	}
	
	public void clearDialog(){
		Window windows[] = Window.getWindows();
		for (int i = 0; i < windows.length; i++){
			if (windows[i].getName().equals("New Game")){
				windows[i].dispose();
			}
		}
	}
}

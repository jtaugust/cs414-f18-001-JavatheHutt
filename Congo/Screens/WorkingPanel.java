package Screens;

import javax.swing.JPanel;

import GUI.Frame;

public class WorkingPanel {
	JPanel workingPanel;
	
	String currentScreen;
	
	Screen screen;
	
	Frame frame;
	
	public WorkingPanel(){
		workingPanel = new JPanel();
		workingPanel.setName("blankWorking");
		changeScreen(new LoginScreen());
	}
	
	public void changeScreen(Screen screen) {
		//check if template needs to change
		
		//set the WorkingPanel objects screen to the new screen object
		this.screen = screen;
		
		//set the currentScreen to the new screens name
		this.currentScreen = this.screen.name;
		
		//give the screen this object
		this.screen.WorkingPanel = this;
		
		//give the new screen the workingPanel JPanel
		this.screen.workingPanel = this.workingPanel;
		
		//set the workingPanel
		this.screen.setPanel();
		
		System.out.println(this.workingPanel.getName());
	}
	
	public void requestSetUser(String user){
		if (this.currentScreen == "Login"){
			frame.setUser(user);
		}
	}
	
	public JPanel getWorkingPanel(){
		return this.workingPanel;
	}
	
	public String getCurrentScreen(){
		return this.currentScreen;
	}
	
	public void setFrame(Frame frame){
		this.frame = frame;
	}
	
}

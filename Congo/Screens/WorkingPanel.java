package Screens;

import javax.swing.JPanel;

public class WorkingPanel {
	JPanel workingPanel;
	
	String currentScreen;
	
	Screen screen;
	
	public WorkingPanel(){
		workingPanel = new JPanel();
		changeScreen(new LoginScreen());
	}
	
	public void changeScreen(Screen screen) {
		
	}
}

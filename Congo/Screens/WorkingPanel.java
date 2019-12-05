package Screens;

import javax.swing.JPanel;

import GUI.Frame;

public class WorkingPanel {
	public JPanel workingPanel;
	
	String currentScreen;
	
	public Screen screen;
	
	Frame frame;
	
	public WorkingPanel(){
		workingPanel = new JPanel();
		workingPanel.setName("blankWorking");
		setScreenInfo(new LoginScreen());
		this.screen.setScreen();
	}
	
	public void setScreenInfo(Screen screen) {
		
		
		//set the currentScreen to the new screens name
		this.currentScreen = screen.name;
		if (screen.name.equals("New Game")){
			screen.WorkingPanel = this;
			screen.workingPanel = this.workingPanel;
			screen.setScreen();
		}else{
			//set the WorkingPanel objects screen to the new screen object
			this.screen = screen;
			//give the new screen this object
			this.screen.WorkingPanel = this;
			//give the new screen the workingPanel JPanel
			this.screen.workingPanel = this.workingPanel;
		}
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
	
	public void updateWorking(){
		this.workingPanel.validate();
		this.workingPanel.repaint();
	}
	
	//pass off the screen object from the screens to the frame
	protected void changeScreen(Screen screen) {
		frame.changeScreen(screen);
	}
	
	protected void setUser(String user) {
		frame.setUser(user);
	}
	
	protected String getUser() {
		return frame.getUser();
	}
	
}

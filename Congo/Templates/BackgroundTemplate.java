package Templates;

import javax.swing.JPanel;

import GUI.Frame;
import Screens.Screen;

public class BackgroundTemplate {
	private JPanel backgroundPanel;
	private int template;
	private int width, height;
	protected Frame frame;
	BackgroundTemplate temp;
	
	public BackgroundTemplate() {
		backgroundPanel = new JPanel();
		width = 0;
		height = 0;
		template = 0;
	}
	
	public void setTemplate(int template) {
		if (this.template != template){ //if there is a need to change the template, do so
			backgroundPanel.removeAll();
			this.template = template; //set the template int
			if (template == 1) {
				temp = new InitialTemplate(this.frame);
				this.backgroundPanel = temp.generateTemplate();
				width = 600;
				height = 800;
			}else {
				temp = new MainTemplate(this.frame);
				this.backgroundPanel = temp.generateTemplate();
				width = 1000;
				height = 1000;
			}
		}
	}
	
	public int getTemplate() {
		return this.template;
	}
	
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
	
	public JPanel getBackgroundPanel() {
		return this.backgroundPanel;
	}
	
	public void setFrame(Frame frame){
		this.frame = frame;
	}
	
	public boolean needChange(String screen) {
		if ((screen.equals("Login") || screen.equals("Registration"))) {
			if (template == 1) {
				return false;
			}else {
				return true;
			}
		}else {
			if (template > 1) {
				return false;
			}
		}
		return true;
	}
	
	protected void changeScreen(Screen screen) {
		frame.changeScreen(screen);
	}
	
	protected void setUser(String user) {
		frame.setUser(user);
	}
	
	public JPanel generateTemplate() {
		return null;
	}
}

package Templates;

import javax.swing.JPanel;

public class BackgroundTemplate {
	private JPanel backgroundPanel;
	private int template;
	private int width, height;
	
	public BackgroundTemplate() {
		backgroundPanel = new JPanel();
		width = 0;
		height = 0;
		template = 0;
	}
	
	public void setTemplate(int template) {
		if (this.template != template){ //if there is a need to change the template, do so
			this.template = template; //set the template int
			if (template == 1) {
				this.backgroundPanel = InitialTemplate.generateInitial();
				width = 600;
				height = 800;
			}else {
				this.backgroundPanel = MainTemplate.generateMain();
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
}

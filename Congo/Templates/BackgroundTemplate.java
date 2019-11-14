package Templates;

import java.awt.Dimension;

import javax.swing.JPanel;

public class BackgroundTemplate {
	private JPanel backgroundPanel;
	private int template;
	private int width, height;
	
	public BackgroundTemplate() {
		setTemplate(1);
	}
	
	public void setTemplate(int template) {
		this.template = template;
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
}

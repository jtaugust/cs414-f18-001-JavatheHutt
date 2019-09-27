package GUI;

import java.awt.BorderLayout;
import java.awt.Label;

import javax.swing.JFrame;

import App.*;

public class Frame {
	private static int width = 0, height = 0, template = 0;
	//create a frame, takes width and height, possibly take a string for the panels, 
	public static JFrame createFrame(int template) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		//load background as well as the required template
		Panel.createPanels(frame, template);

		return frame;
	}

	public static void changeTemplate(String screen) {
			boolean change = false;
			switch(screen) {
			case "Login":
			case "Registration":
			case "Captcha": 
				if (template != 1) {
					Panel.createPanels(Application.window, 1);
				}
				break;
			default: break;
			//TODO other cases
			}
	}
	
	public static void finalize(JFrame frame) {
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
		
		frame.setContentPane(Panel.getWorkingPanelBackground());
	}

	public static void setDimensions(int w, int h) {
		width = w;
		height = h;
	}
	
	public static int getWidth(){
		return width;
	}
	
	public static int getHeight(){
		return height;
	}
	
	public static void setTemplate(int t) {
		template = t;
	}
	
	public static int getTemplate() {
		return template;
	}
	
}

package GUI;

import java.awt.BorderLayout;
import java.awt.Label;

import javax.swing.JFrame;

import App.*;

public class Frame {
	private static int width = 0, height = 0, template = 0;
	
	/*
	 * create the frame to build off of
	 * then create the required template panel
	 */
	public static JFrame createFrame(int template) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		//load background as well as the required template
		Panel.createPanels(frame, template);

		return frame;
	}

	/*
	 * if the template requested is not the current template
	 * build the requested template
	 */
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
	
			case "New Game":
			case "Existing Games":
			case "Rules":
			case "InitialMain":
			case "Notifications":
			case "Match History":
			case "UnRegister":
			case "Account":
				if (template != 2) {
					Panel.createPanels(Application.window, 2);
				}
				break;
				
			
			}
	}
	
	/*
	 * Sets the frames common settings 
	 */
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
		//add the built panels to the frame
		frame.setContentPane(Panel.getWorkingPanelBackground());
	}

	//sets the frames dimensions
	public static void setDimensions(int w, int h) {
		width = w;
		height = h;
	}
	
	//returns the frame width
	public static int getWidth(){
		return width;
	}
	
	//returns the frame height
	public static int getHeight(){
		return height;
	}
	
	//sets the template being used
	public static void setTemplate(int t) {
		template = t;
	}
	
	//returns the template being used
	public static int getTemplate() {
		return template;
	}
	
}

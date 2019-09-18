package GUI;

import java.awt.BorderLayout;
import java.awt.Label;

import javax.swing.JFrame;

public class Frame {

	//create a frame, takes width and height, possibly take a string for the panels, 
	public static JFrame createFrame(int width, int height, String type, String backgroundPath) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		//load background as well as the required template
		Panel.fillFrame(frame, width, height, type, backgroundPath);
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

		return frame;
	}
}

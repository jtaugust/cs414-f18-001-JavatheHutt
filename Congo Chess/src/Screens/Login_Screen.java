package Screens;

import javax.swing.JFrame;

public class Login_Screen {
	//given this screen is the start screen, its method is the bootup method.
	public static void Bootup() {
		//get frame to work with
		final int width = 600, height = 800;
		JFrame frame = GUI.Frame.createFrame(width, height, "Login", "./Images/Background.jpg");
	}
}

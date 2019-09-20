package App;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

import Screens.*;
import GUI.Frame;
import GUI.Panel;

import javax.imageio.*;

public class Application extends JFrame{
	public static JFrame window = null;
	public static void main(String[] args) {
		//create start up frame: initial width and height (600,800), initial template
		window = GUI.Frame.createFrame(3);
		
		//fill the working frame with the login details
		//changeScreen("Login");	
	}
	
	//all page changes use this method
	public static void changeScreen(String screen) {
		//change the template if required
		Frame.changeTemplate(screen);
		
		//update the working panel
		switch (screen) {
			case "Login": Login_Screen.Screen(); break;
		}
		
		//All changes to working panel must include this to update changes
		window.validate();
		window.repaint();
	}
}



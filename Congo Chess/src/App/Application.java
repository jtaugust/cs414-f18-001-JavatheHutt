package App;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

import Screens.*;
import Templates.*;
import GUI.Frame;
import GUI.Panel;

import javax.imageio.*;

public class Application extends JFrame{
	public static JFrame window = null;
	private static String username = null;
	
	public static void main(String[] args) {
		//create start up frame: initial width and height (600,800), initial template
		window = GUI.Frame.createFrame(1);
		
		//fill the working frame with the login details
	    changeScreen("Login");	
	}
	
	//all page changes use this method
	public static void changeScreen(String screen) {
		//clear the working panel
		Panel.clearWorkingPanel();
		
		//change the template if required
		Frame.changeTemplate(screen);
		
		//update the working panel
		switch (screen) {
			case "Login": LoginScreen.screen(); break;
			case "Registration": RegistrationScreen.screen(); break;
		}
		update();
	}
	
	public static void update(){
		//set the content 
		window.getContentPane().add(Panel.getWorkingPanel());
		window.validate();
		window.repaint();
	}
	
	public static void setUser(String user){
		username = user;
	}
	
	public static String getUser(){
		return username;
	}
}



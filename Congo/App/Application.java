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
import Server.serverUsersHelpers;

import javax.imageio.*;

public class Application extends JFrame{
	public static JFrame window = null;
	private static String username = null;
	private static String currentScreen = null;
	private static boolean err = false;
	
	public static void main(String[] args) {
		//create start up frame: initial width and height (600,800), initial template
		//window = GUI.Frame.createFrame(1);
		
		//fill the working frame with the login details
	    //changeScreen("Login");
	    serverUsersHelpers doa = new serverUsersHelpers();
	    
	    System.out.println("here");
	    
	    window = GUI.Frame.createFrame(1);
	    
	    changeScreen("Login");
	    
	}
	
	//all page changes use this method
	public static void changeScreen(String screen) {
		if (!screen.equals(currentScreen) || err) {
			err = false;

			//change the template if required
			Frame.changeTemplate(screen);
			
			//remove the working panel so the next screen has a fresh panel to work with
			Panel.clearWorkingPanel();
			
			//update the working panel with the requested screen
			switch (screen) {
				case "Login": LoginScreen.screen(); break;
				case "Registration": RegistrationScreen.screen(); break;
				case "Captcha": CaptchaScreen.screen(); break;
				
				case "InitialMain": InitialMainScreen.screen(); break;
				case "New Game": NewGameScreen.screen(); break;
				case "Existing Games": ExistingGamesScreen.screen(); break;
				case "Rules": RulesScreen.screen(); break;
				
				case "Account": AccountScreen.screen(); break;
				case "UnRegister": UnRegisterScreen.screen(); break;
				case "Match History": MatchHistoryScreen.screen(); break;	
			}
			update();
		}
	}
	
	//tells the frame to update itself with the new screen
	public static void update(){
		window.validate();
		window.repaint();
	}
	
	//when the user logs in, set the user
	public static void setUser(String user){
		username = user;
	}
	
	//returns the current user's username
	public static String getUser(){
		return username;
	}
	
	//returns which screen is currently being shown
	public static String getCurrentScreen() {
		return currentScreen;
	}
	
	//sets which screen is current being shown
	public static void setCurrentScreen(String str) {
		currentScreen = str;
	}
	
	public static void setErr() {
		err = true;
	}
}



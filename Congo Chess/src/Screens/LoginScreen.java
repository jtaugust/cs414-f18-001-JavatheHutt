package Screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import App.Application;
import GUI.Helpers;
import GUI.Label;
import GUI.Panel;
import Templates.InitialTemplate;

public class LoginScreen {
	private static boolean loginError = false;
	public static void screen(){
		
		//get the working panel
		JPanel panel = Panel.getWorkingPanel();
		
		//set the working panel's layout
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		//build the panel
		JTextField username = Helpers.newTextField("username", "username");
		panel.add(Helpers.spacer(100, 150));
		panel.add(username);
		
		//create space under username textfield
		panel.add(Helpers.spacer(200, 50));
	
		JTextField password = Helpers.newTextField("Password", "Password");
	    panel.add(password);
		
	    //create the panel below password
	    JPanel error = new JPanel();
	    error.setOpaque(false);
	    error.setMaximumSize(new Dimension(400,50));
	    if (loginError == true){ //if login failed, update panel to an error label
	    	error.setOpaque(false);
	    	error.add(Label.errorLabel("<html>Incorrect username or password.<html>", Color.red));
	    }
	    panel.add(error);
	    
		JButton login = Helpers.button("Login", new Dimension(100, 30));
		login.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String name = username.getText(), pass = password.getText();
						if (!Database.DB.isUser(name, pass)){ // user doesnt exist
							LoginScreen.setLoginError();
							Application.changeScreen("Login");
						}else{
							//update application variable "User" to username.getText()
							Application.setUser(name);
							//redirect to main page
							//TODO
						}
					}
				}
		);
		panel.add(login);
		
		panel.add(Helpers.spacer(200, 50));
		
		JButton register = Helpers.button("Dont have an account? Create one now!", new Dimension(250, 30));
		register.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Application.changeScreen("Registration");
					}
				}
		);
		panel.add(register);
		
		//if loginError was set, change it back
		if (loginError == true){
			loginError = false;
		}
	}

	public static void setLoginError(){
		loginError = true;
	}
	
	
}

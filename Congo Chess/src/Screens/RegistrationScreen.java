package Screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import App.Application;
import GUI.Helpers;
import GUI.Label;
import GUI.Panel;

public class RegistrationScreen {
	private static boolean loginError = false;
	public static void screen(){
		//Set current screen to Registration
		Application.setCurrentScreen("Registration");
		
		//get the working panel
		JPanel panel = Panel.getWorkingPanel();
		
		//set the working panel's layout
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		//build the panel
		JTextField email = Helpers.newTextField("email", "temp@fake.com");
		panel.add(Helpers.spacer(100, 50));
		panel.add(email);

		//create space under email textfield
		panel.add(Helpers.spacer(200, 50));
		
		JTextField username = Helpers.newTextField("username", "Admin1");
		panel.add(username);
		
		//create space under username textfield
		panel.add(Helpers.spacer(200, 50));
	
		JTextField password = Helpers.newTextField("Password", "tempPass");
	    panel.add(password);
	    
		//create space under password textfield
		panel.add(Helpers.spacer(200, 50));
	    
	    JTextField passwordConfirm = Helpers.newTextField("Confirm Password", "tempPass");
	    panel.add(passwordConfirm);
		
	    //create the panel below password
	    JPanel error = new JPanel();
	    error.setOpaque(false);
	    error.setMaximumSize(new Dimension(400,50));
	    if (loginError == true){ //if login failed, update panel to an error label
	    	error.setOpaque(false);
	    	error.add(Label.errorLabel("<html>Passwords do not match<html>", Color.red));
	    }
	    panel.add(error);
	    
	    
		JButton login = Helpers.button("Register", new Dimension(100, 30));
		
		login.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//TODO: create new user in database if name and email isn't already taken.
						Application.changeScreen("InitialMain");
						
					}
				}
		);
		panel.add(login);
		
		panel.add(Helpers.spacer(200, 50));
		
		JButton register = Helpers.button("Go back to login page.", new Dimension(250, 30));
		register.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Application.changeScreen("Login");
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

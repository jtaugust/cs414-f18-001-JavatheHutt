package Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import App.Application;
import GUI.Helpers;
import GUI.Label;
import GUI.Panel;
import Server.serverHelpers;

public class RegistrationScreen {
	private static int registrationError = 0;
	public static void screen(){
		//Set current screen to Registration
		Application.setCurrentScreen("Registration");


		//get the working panel
		JPanel workingPanel = Panel.getWorkingPanel();
		//set the working panel's layout
		workingPanel.setLayout(new BorderLayout());


		//create section for email, username, password, and password confirmation
		JPanel textFields = new JPanel();
		textFields.setLayout(new BoxLayout(textFields, BoxLayout.Y_AXIS));



		//create email field 
		JTextField emailfield = Helpers.newTextField("Email", "Email Address");
		emailfield.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,6,0,0,new Color(79,175,255)),new MatteBorder(8,8,8,8, Color.white)));

		//add space before username
		textFields.add(Helpers.spacer(100, 50));

		//add email field
		textFields.add(emailfield);

		//create space under email textfield
		textFields.add(Helpers.spacer(200, 50));

		//create username field 
		JTextField username = Helpers.newTextField("Username", "Username");
		username.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,6,0,0,new Color(79,175,255)),new MatteBorder(8,8,8,8, Color.white)));

		//add username field
		textFields.add(username);

		//create space under username field
		textFields.add(Helpers.spacer(200, 50));

		//create password fields
		JPasswordField passwordField = Helpers.newPasswordField(16);
		passwordField.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,6,0,0,new Color(79,175,255)),new MatteBorder(8,8,8,8, Color.white)));

		//add password field
		textFields.add(passwordField);

		//create space under password field (for password error)
		textFields.add(Helpers.spacer(200, 50));

		//create password confirmation field
		JPasswordField passwordConfirm = Helpers.newPasswordField(16, "Confirm Password");
		passwordConfirm.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,6,0,0,new Color(79,175,255)),new MatteBorder(8,8,8,8, Color.white)));

		//add password confirmation field
		textFields.add(passwordConfirm);

		//make sure background is transparent so the template background will show
		textFields.setOpaque(false);

		//create space under password confirmation field (for password error)
		textFields.add(Helpers.spacer(200, 10));

	    //create the panel below password
	    JPanel error = new JPanel();
	    error.setOpaque(false);
	    error.setMaximumSize(new Dimension(400,50));

	    if (registrationError != 0){ //if registration failed, update panel to an error label
	    	error.setOpaque(false);
	    	String errStr = "";
	    	switch (registrationError) {
	    		case 1: errStr = "<html>You must fill out the entire form.</html>"; break;
	    		case 2: errStr = "<html>Passwords do not match.</html>"; break;
	    		case 3: errStr = "<html>That username is already in use.</html>"; break;
	    		case 4: errStr = "<html>That email is already in use.</html>"; break;
	    		case 5: errStr = "<html>Password contains illegal characters.</html>"; break;
	    		default: break;
	    	}
	    	error.add(Label.errorLabel(errStr, Color.red));
	    	
	    	//reset the error
	    	registrationError = 0;
	    }
	    textFields.add(error);	    


	    //create section for buttons (below the fields section)
	    JPanel bottomButtons = new JPanel();
	    bottomButtons.setLayout(new BoxLayout(bottomButtons, BoxLayout.LINE_AXIS));
	    bottomButtons.setBackground(Color.black);
	    bottomButtons.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(6,0,0,0,new Color(79,175,255)),new MatteBorder(5,5,5,5, Color.black)));
	    bottomButtons.setPreferredSize(new Dimension(600,75));

	    //create and add "Register" button
	    JPanel register = new JPanel();
	    register.setBackground(new Color(79,175,255));
	  	register.setLayout(new GridBagLayout());
	  	register.add(new JLabel("Register"));
	  	register.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {
	  			register.setBackground(new Color(110,190,255));
	  		}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			String name = username.getText();
				String email = emailfield.getText();
				String password = new String(passwordField.getPassword());
				String passwordCon = new String(passwordConfirm.getPassword());
				if (isDefaultInput(username, emailfield, passwordField,passwordConfirm)) {
					setRegistrationError(1);
	  			}else if(!password.equals(passwordCon)){ // passwords don't match, show error
					setRegistrationError(2);
				} else {
					int err = serverHelpers.tryRegister(name, email, password);
					if (err == 0){ // 
						Application.setUser(name);
						Application.changeScreen("InitialMain");
					}else{ //error was received
						//update application variable "User" to username.getText()
						setRegistrationError(err);
					}

				}
	  		}
		});
	    register.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10,10,10,10,Color.black), new MatteBorder(2,2,2,2,new Color(79,175,255))));
	    bottomButtons.add(register);

		//create and add "Back to Login" button
	    JPanel login = new JPanel();
	    login.setBackground(new Color(90,90,90));
	    login.setLayout(new GridBagLayout());
	    login.add(new JLabel("Back to Login"));
	    login.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {
	  			login.setBackground(new Color(255,255,255));
	  		}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			Application.changeScreen("Login");
	  		}
		});
	    login.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10,10,10,10,Color.black), new MatteBorder(2,2,2,2,new Color(79,175,255))));
	    bottomButtons.add(login);

	    //add field and button panels to the working panel
	    workingPanel.add(textFields, BorderLayout.PAGE_START);
	    workingPanel.add(bottomButtons, BorderLayout.PAGE_END);
	}

	//set the registration error
	private static void setRegistrationError(int err){
		registrationError = err;
		Application.setErr();
		Application.changeScreen("Registration");
	}

	private static boolean isDefaultInput(JTextField username, JTextField emailfield, JTextField passwordField, JTextField passwordConfirm) {
		if (username.getText().contentEquals("Username") || emailfield.getText().contentEquals("Email Address")
				|| passwordField.getText().contentEquals("Password") || passwordConfirm.getText().contentEquals("Password")) { 
			return true;
		}
		return false;
	}
}
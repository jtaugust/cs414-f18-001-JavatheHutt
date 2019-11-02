package Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import App.Application;
import GUI.Helpers;
import GUI.Label;
import GUI.Panel;
import Server.serverHelpers;

public class LoginScreen {
	private static int loginError = 0;
	public static void screen(){
		Application.setCurrentScreen("Login");
		
		//get the working panel
		JPanel workingPanel = Panel.getWorkingPanel();
		//set the working panel's layout
		workingPanel.setLayout(new BorderLayout());

		//create section for username and password
		JPanel textFields = new JPanel();
		textFields.setLayout(new BoxLayout(textFields, BoxLayout.Y_AXIS));

		//create username field 
		JTextField username = Helpers.newTextField("Username", "Username");
		
		//create password field
		JPasswordField password = Helpers.newPasswordField(16);
		
		//on enter in username, move to password
		username.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                	password.requestFocus();
                }
            }
        });

		//add space before username
		textFields.add(Helpers.spacer(100, 150));

		//add username field
		textFields.add(username);

		//create space under username textfield
		textFields.add(Helpers.spacer(200, 50));
		
		
		
		//allow enter key to login while on password field
        password.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    String name = username.getText(), pass = new String(password.getPassword());
                    int err = serverHelpers.tryLogin(name, pass);
                    if (err == 0){ // authentic user
                        Application.setUser(name);
                        Application.changeScreen("InitialMain");
                    }else{
                        LoginScreen.setLoginError(err);
                        Application.setErr();
                        Application.changeScreen("Login");
                    }
                }
            }
        });

		//add password field
		textFields.add(password);

		//make sure background is transparent so the template background will show
		textFields.setOpaque(false);

		//create space under password textfield (for password error)
		textFields.add(Helpers.spacer(200, 50));


	    //create the panel below password
	    JPanel error = new JPanel();
	    error.setOpaque(false);
	    error.setMaximumSize(new Dimension(400,50));

	    //if login failed, add an error label below the password textfield
	    if (loginError != 0){
	    	error.setOpaque(false);
	    	String err = "";
	    	switch (loginError) {
	    		case 1: err = "<html>You must fill out the entire form.</html>"; break;
	    		case 2: err = "<html>Username or password is incorrect</html>"; break;
	    		case 3: err = "<html>Sql connection error occured.</html>"; break;
	    		case 4: err = "<html>Password contains illegal characters.</html>"; break;
	    		default: break; //unkown error occured
	    	}
	    	error.add(Label.errorLabel(err, Color.red));
	    	loginError = 0;
	    }
	    textFields.add(error);	    


	    //create section for buttons (below the fields section)
	    JPanel bottomButtons = new JPanel();
	    bottomButtons.setLayout(new BoxLayout(bottomButtons, BoxLayout.LINE_AXIS));
	    bottomButtons.setBackground(Color.black);
	    bottomButtons.setPreferredSize(new Dimension(600,75));

	    //create and add "Login" button
	    JPanel login = new JPanel();
	    login.setBackground(new Color(79,175,255));
	  	login.setLayout(new GridBagLayout());
	  	login.add(new JLabel("Login"));
	  	login.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {
	  			login.setBackground(new Color(110,190,255));
	  		}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			String name = username.getText(), pass = new String(password.getPassword());
	  			
	  			if (pass.equals("Password") || username.equals("Username")){
	  				setLoginError(1);
	  			}else{
		  			int err = serverHelpers.tryLogin(name, pass);
					if (err == 0){ // authentic user
						Application.setUser(name);
						Application.changeScreen("InitialMain");
					}else{
						LoginScreen.setLoginError(err);
						Application.setErr();
						Application.changeScreen("Login");
					}
	  			}
	  		}
		});
	    login.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10,10,10,10,Color.black), new MatteBorder(2,2,2,2,new Color(79,175,255))));
	    bottomButtons.add(login);



		//create and add "Register" button
	    JPanel register = new JPanel();
	    register.setBackground(new Color(90,90,90));
	    register.setLayout(new GridBagLayout());
	    register.add(new JLabel("Sign up here!"));
	    register.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {
	  			register.setBackground(new Color(255,255,255));
	  		}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			Application.changeScreen("Registration");
	  		}
		});
	    register.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10,10,10,10,Color.black), new MatteBorder(2,2,2,2,new Color(79,175,255))));
	    bottomButtons.add(register);

	    //add field and button panels to the working panel
	    workingPanel.add(textFields, BorderLayout.PAGE_START);
	    workingPanel.add(bottomButtons, BorderLayout.PAGE_END);
	}

	private static void setLoginError(int err){
		loginError = err;
	}


}
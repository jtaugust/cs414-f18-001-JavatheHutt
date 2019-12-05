package Screens;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class RegistrationScreen extends Screen{
	
	public static final Color darkGray = new Color(50,50,50);
	public static final Color mediumGray = new Color(70,70,70);
	public static final Color lightGray = new Color(90,90,90);
	public static final Color highlightGray = new Color(120,120,120);
	public static final Color blue = new Color(79,175,255);
	public static final Color highlightBlue = new Color(110,190,255);
	
	public RegistrationScreen() {
		this.error = 0;
		this.name = "Registration";
		setErrorCards();
	}
	
	@Override
	public void setScreen(){

		//set the working panel's layout
		workingPanel.setLayout(new BorderLayout());

		//create section for email, username, password, and password confirmation
		JPanel textFields = new JPanel();
		textFields.setLayout(new BoxLayout(textFields, BoxLayout.Y_AXIS));

		//create email field 
		JTextField emailfield = Helpers.newTextField("Email", "Email Address");
		
		//create username field 
		JTextField username = Helpers.newTextField("Username", "Username");
		
		//create password fields
		JPasswordField passwordField = Helpers.newPasswordField(16);
		
		//create password confirmation field
		JPasswordField passwordConfirm = Helpers.newPasswordField(16, "Confirm Password");
		
		
		//on enter in email, move to username
		emailfield.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                	username.requestFocus();
                }
            }
        });
		//on enter in username, move to password
		username.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                	passwordField.requestFocus();
                }
            }
        });
		//on enter in password, move to confirm password
		passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                	passwordConfirm.requestFocus();
                }
            }
        });
		
		//on enter in confirm password, submit
		passwordConfirm.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
            	if (e.getKeyCode()==KeyEvent.VK_ENTER){
		  			if (isDefaultInput(username, emailfield, passwordField,passwordConfirm)) {
						setError(1);
		  			}else{
						String name = username.getText();
						String email = emailfield.getText();
						String password = new String(passwordField.getPassword());
						String passwordCon = new String(passwordConfirm.getPassword());
						register(name, email, password, passwordCon);
		  			}
            	}
            }
        });

		
		//add space before username
		textFields.add(Helpers.spacer(100, 50));

		//add email field
		textFields.add(emailfield);

		//create space under email textfield
		textFields.add(Helpers.spacer(200, 50));

		//add username field
		textFields.add(username);

		//create space under username field
		textFields.add(Helpers.spacer(200, 50));

		//add password field
		textFields.add(passwordField);

		//create space under password field (for password error)
		textFields.add(Helpers.spacer(200, 50));

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

	    //add the error field beneath the passwords
	    textFields.add(errorCards);	    

	    //create section for buttons (below the fields section)
	    JPanel bottomButtons = new JPanel();
	    bottomButtons.setLayout(new BoxLayout(bottomButtons, BoxLayout.LINE_AXIS));
	    bottomButtons.setBackground(Color.black);
	    bottomButtons.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(6,0,0,0,blue),new MatteBorder(5,5,5,5, Color.black)));
	    bottomButtons.setPreferredSize(new Dimension(600,75));

	    //create and add "Register" button
	    JPanel register = new JPanel();
	    Color registerStartColor = blue;
	    register.setBackground(registerStartColor);
	  	register.setLayout(new GridBagLayout());
	  	register.add(new JLabel("Register"));
	  	register.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {
	  			register.setBackground(new Color(90,210,255));
	  		}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			if (isDefaultInput(username, emailfield, passwordField, passwordConfirm)){
	  				setError(1);
	  			}else{
		  			String name = username.getText();
					String email = emailfield.getText();
					String password = new String(passwordField.getPassword());
					String passwordCon = new String(passwordConfirm.getPassword());
					register(name, email, password, passwordCon);
	  			}
	  			register.setBackground(registerStartColor);
	  		}
		});
	    register.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10,10,10,10,Color.black), new MatteBorder(2,2,2,2,blue)));
	    bottomButtons.add(register);

		//create and add "Back to Login" button
	    JPanel login = new JPanel();
	    login.setBackground(lightGray);
	    login.setLayout(new GridBagLayout());
	    login.add(new JLabel("Back to Login"));
	    login.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {
	  			login.setBackground(Color.white);
	  		}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			WorkingPanel.changeScreen(new LoginScreen());
	  		}
		});
	    login.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10,10,10,10,Color.black), new MatteBorder(2,2,2,2,blue)));
	    bottomButtons.add(login);

	    //add field and button panels to the working panel
	    workingPanel.add(textFields, BorderLayout.PAGE_START);
	    workingPanel.add(bottomButtons, BorderLayout.PAGE_END);
	}
	
	private void register(String name, String email, String pass, String passCon){
		if(!pass.equals(passCon)){ // passwords don't match, show error
			setError(2);
		} else {
			int err = serverHelpers.tryRegister(name, email, pass);
			if (err == 0){ // 
				WorkingPanel.setUser(name);
				WorkingPanel.changeScreen(new InitialMainScreen());
			}else{ //error was received
				setError(err);
			}
		}
	}

	private boolean isDefaultInput(JTextField username, JTextField emailfield, JTextField passwordField, JTextField passwordConfirm) {
		if (username.getText().contentEquals("Username") || emailfield.getText().contentEquals("Email Address")
				|| passwordField.getText().contentEquals("Password") || passwordConfirm.getText().contentEquals("Password")) { 
			return true;
		}
		return false;
	}

	@Override
	void setErrorCards() {
		errorCards = Panel.errorCards(new Dimension(400,400));
		String errStr = "";
		int err = 0;
		boolean cont = true;
		while(cont) {
			
	    	switch (err) {
	    		case 0: break;
	    		case 1: errStr = "<html>You must fill out the entire form.</html>"; break;
	    		case 2: errStr = "<html>Passwords do not match.</html>"; break;
	    		case 3: errStr = "<html>That username is already in use.</html>"; break;
	    		case 4: errStr = "<html>That email is already in use.</html>"; break;
	    		case 5: errStr = "<html>Password contains illegal characters.</html>"; break;
	    		case 6: errStr = "<html>Username contains illegal characters.</html>"; break;
	    		case 7: errStr = "<html>Provided email is invalid.</html>"; break;
	    		default: cont = false; break;
	    	}
    	
	    	if (cont)
	    		this.errorCards.add(Label.errorLabel(errStr, Color.red), String.valueOf(err));
    		
    	err++;
		}
	}
}
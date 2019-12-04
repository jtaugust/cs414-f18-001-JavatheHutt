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

import javax.swing.*;
import javax.swing.border.MatteBorder;

import App.Application;
import GUI.Helpers;
import GUI.Label;
import GUI.Panel;
import Server.serverHelpers;

public class LoginScreen extends Screen{
	
	public static final Color lightGray = new Color(90,90,90);
	public static final Color highlightGray = new Color(120,120,120);
	public static final Color blue = new Color(79,175,255);
	public static final Color highlightBlue = new Color(110,190,255);
	
	public LoginScreen(){
		this.error = 0;
		this.name = "Login";
		//set up error cards
		
		setErrorCards();
	}
	
	@Override
	public void setScreen(){
		JTextField captureFocus = new JTextField();
		captureFocus.setOpaque(false);
		captureFocus.setBorder(null);
		
		workingPanel.setName("Login");
		
		//set the working panel's layout
		workingPanel.setLayout(new BorderLayout());
		
		//hide the panel so the background shows
		workingPanel.setOpaque(false);

		//create section for username and password
		JPanel textFields = new JPanel();
		
		//add the empty focus field because swing is weird
		textFields.add(captureFocus);
		
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
    	  			if (isDefaultInput(name, pass)){
    	  				setError(1);
    	  			}else{
    	  				login(name, pass);
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
	    JPanel error = new JPanel(new CardLayout());
	    error.setOpaque(false);
	    error.setMaximumSize(new Dimension(400,50));

	    //add the error field beneath password
	    textFields.add(errorCards);

	    //create section for buttons (below the fields section)
	    JPanel bottomButtons = new JPanel();
	    bottomButtons.setLayout(new BoxLayout(bottomButtons, BoxLayout.LINE_AXIS));
	    bottomButtons.setBackground(Color.black);
	    bottomButtons.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(6,0,0,0,blue),new MatteBorder(5,5,5,5, Color.black)));
	    bottomButtons.setPreferredSize(new Dimension(600,75));

	    //create and add "Login" button
	    JPanel login = new JPanel();
	    Color loginStartColor = blue;
	    login.setBackground(loginStartColor);
	  	login.setLayout(new GridBagLayout());
	  	login.add(new JLabel("Login"));
	  	login.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {
	  			login.setBackground(highlightBlue);
	  		}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			String name = username.getText(), pass = new String(password.getPassword());
	  			if (isDefaultInput(name, pass)){
	  				setError(1);
	  			}else{
	  				login(name, pass);
	  			}
	  			login.setBackground(loginStartColor);
	  		}
		});
	    login.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10,10,10,10,Color.black), new MatteBorder(2,2,2,2,blue)));
	    bottomButtons.add(login);
	    
		//create and add "Register" button
	    JPanel register = new JPanel();
	    Color registerStartColor = lightGray;
	    register.setBackground(registerStartColor);
	    register.setLayout(new GridBagLayout());
	    register.add(new JLabel("Sign up here!"));
	    register.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {
	  			register.setBackground(Color.white);
	  		}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			WorkingPanel.changeScreen(new RegistrationScreen());
	  			register.setBackground(registerStartColor);
	  		}
		});
	    register.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10,10,10,10,Color.black), new MatteBorder(2,2,2,2,blue)));
	    bottomButtons.add(register);
	    
	    //add field and button panels to the working panel
	    workingPanel.add(textFields, BorderLayout.PAGE_START);
	    workingPanel.add(bottomButtons, BorderLayout.PAGE_END);
	    
	}
	
	private static boolean isDefaultInput(String user, String pass){
		if (user.equals("Username") || pass.equals("Password") || user.equals("") || pass.equals("")){
			return true;
		}else{
			return false;
		}
	}
	
	private void login(String user, String pass){
        int err = serverHelpers.tryLogin(user, pass);
        if (err == 0){ // authentic user
        	WorkingPanel.setUser(user);
        	WorkingPanel.changeScreen(new InitialMainScreen());
        }else{
            setError(err);
        }
	}
	
	@Override
	public void setErrorCards(){
		errorCards = Panel.errorCards(new Dimension(400,400));
		boolean cont = true;
		int err = 0;
		String error = "";
		while(cont){
			switch(err){
				case 0: break;
				case 1: error = "<html>You must fill out the entire form.</html>"; break;
	    		case 2: error = "<html>Username or password is incorrect</html>"; break;
	    		case 3: error = "<html>Sql connection error occured.</html>"; break;
	    		case 4: error = "<html>Password contains illegal characters, <br> only letters and numbers are allowed.</html>"; break;
	    		case 5: error = "<html>Username contains illegal characters, <br> only letters and numbers are allowed.</html>"; break;
	    		default: cont = false; break;
			}
			if (cont)
				this.errorCards.add(Label.errorLabel(error, Color.red), String.valueOf(err));
			
			err++;
		}	
	}
}
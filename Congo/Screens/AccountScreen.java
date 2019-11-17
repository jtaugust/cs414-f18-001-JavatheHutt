package Screens;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import App.Application;
import GUI.Panel;
import Server.serverHelpers;

public class AccountScreen extends Screen{
	
	public AccountScreen() {
		error = 0;
		name = "Acccount";
		
	}
	
	public void setScreen() {
		workingPanel.setBackground(Color.black);
		workingPanel.setBackground(new Color(50,50,50));
		
		//create and add "Unregister" button
	    JPanel unregister = new JPanel();
	    unregister.setBackground(new Color(90,90,90));
	    unregister.setLayout(new GridBagLayout());
	    JLabel label = new JLabel("Unregister");
	    label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	    unregister.add(label);
	    unregister.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {
	  			unregister.setBackground(new Color(120,120,120));
	  		}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			unregister.setBackground(new Color(90,90,90));
	  			String Username = WorkingPanel.getUser();
	  			int err = serverHelpers.tryUnregister(Username);
	  			
	  			if (err == 0) {
	  				WorkingPanel.changeScreen(new LoginScreen());
	  			}
	  			else {
	  				setError(err);
	  			}
	  		}
		});
	    unregister.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10,10,10,10,Color.black), new MatteBorder(2,2,2,2,new Color(79,175,255))));
	    workingPanel.add(unregister);
	}
	
	@Override
	void setErrorCards() {
		// TODO Auto-generated method stub
		
	}
	
	
}
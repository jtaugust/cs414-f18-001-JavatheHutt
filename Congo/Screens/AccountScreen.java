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

public class AccountScreen {
	
	private static int unregisterError = 0;
	
	public static void screen() {
		System.out.println("here in account");
		Application.setCurrentScreen("Account");
		JPanel panel = Panel.getWorkingPanel();
		panel.setBackground(Color.black);
		panel.setBackground(new Color(50,50,50));
		
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
	  			String Username = Application.getUser();
	  			int err = serverHelpers.tryUnregister(Username);
	  			
	  			if (err == 0) {
	  				Application.changeScreen("Login");
	  			}
	  			else {
	  				AccountScreen.setUnregisterError(err);
	  				Application.setErr();
					Application.changeScreen("Account");
	  			}
	  		}
		});
	    unregister.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10,10,10,10,Color.black), new MatteBorder(2,2,2,2,new Color(79,175,255))));
	    panel.add(unregister);
	}
	
	private static void setUnregisterError(int err){
		unregisterError = err;
	}
	
	
}
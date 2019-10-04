package Templates;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import App.Application;
import GUI.Frame;
import GUI.Helpers;
import GUI.Panel;

public class MainTemplate {
	public static void generateMain(JFrame frame, Boolean finalize){
		
		//set the frame dimensions
		Frame.setDimensions(1000,1000);
		
		//create the background panel to hold all new panels
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setLayout(new BorderLayout());
		
		//create the taskbar
		JPanel taskbar = new JPanel();
		
		generateTaskbar(taskbar);
		
		//create the working panel
		JPanel workingPanel = new JPanel();
		
		//set the panel that a screen should load into
		Panel.setWorkingPanel(workingPanel, true);
		
		//add taskbar to background panel
		backgroundPanel.add(taskbar, BorderLayout.PAGE_START);
	
		//add the working panel to the background panel so it is actually visible and can be worked with
		backgroundPanel.add(workingPanel, BorderLayout.CENTER);
	
		//set the workingPanelBakground variable
		Panel.setWorkingPanelBackground(backgroundPanel);
		
		//if the requested template is the main template, continue
		if (finalize) {
			//set the template variable
			Frame.setTemplate(2);
			Frame.finalize(frame);
		}//else, the requested template is the account template and needs to do more work
	}
	
	
	//create the 5 buttons (account, new game, existing games, rules, logout)
	private static void generateTaskbar(JPanel taskbar){
		taskbar.setLayout(new GridLayout(1, 5));

		//account button
		JButton account = Helpers.button(Helpers.getImage("./Images/AccountDefaultImage.jpg"), new Dimension(100, 100));
		account.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Application.changeScreen("Account");
					}
				}
		);
		taskbar.add(account);
		
		//new game button
		JButton newGame = Helpers.button("New Game", new Dimension(250, 30));
		newGame.addActionListener(
					new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Application.changeScreen("New Game");
					}
				}
		);
		taskbar.add(newGame);
		
		//existing games button
		JButton existing = Helpers.button("Existing Games", new Dimension(250, 30));
		existing.addActionListener(
					new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Application.changeScreen("Existing Games");
					}
				}
		);
		taskbar.add(existing);
		
		//rules button
		JButton rules = Helpers.button("Rules", new Dimension(250, 30));
		rules.addActionListener(
					new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Application.changeScreen("Rules");
					}
				}
		);
		taskbar.add(rules);
		
		//logout button
		JButton logout = Helpers.button("Logout", new Dimension(250, 30));
		logout.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Application.changeScreen("Login");
					}
				}
		);
		taskbar.add(logout);
	}
}

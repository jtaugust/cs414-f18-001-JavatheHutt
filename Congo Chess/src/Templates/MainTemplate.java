package Templates;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import App.Application;
import GUI.Frame;
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
	
		Panel.setWorkingPanelBackground(backgroundPanel);
		
		//finalize the frame
		if (finalize) {
			//set the template variable
			Frame.setTemplate(2);
			Frame.finalize(frame);
		}
	}
	
	private static void generateTaskbar(JPanel taskbar){
		
		//set taskbar layout and background color (color between buttons)
		taskbar.setLayout(new BoxLayout(taskbar, BoxLayout.LINE_AXIS));
		taskbar.setBackground(Color.black);

		//create the 5 buttons (account, new game, existing games, rules, logout)
		JPanel buttonAccount = new JPanel();
		JPanel buttonNewGame = new JPanel();
		JPanel buttonExistingGames = new JPanel();
		JPanel buttonRules = new JPanel();
		JPanel buttonLogout = new JPanel();
		
		//Create and add "Account" button
		buttonAccount.setBackground(new Color(112,112,112));
		buttonAccount.setLayout(new BorderLayout());
		ImageIcon image = new ImageIcon(new ImageIcon("./Images/AccountDefaultImage.jpg").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		JLabel label = new JLabel(image);
		buttonAccount.add(label,BorderLayout.CENTER);
		buttonAccount.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 6, 0, new Color(0,0,0)), new MatteBorder(3, 3, 3, 3, new Color(90,90,90))));
		buttonAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
            	buttonAccount.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 6, 0, new Color(0,0,0)), new MatteBorder(3, 3, 3, 3, new Color(110,190,255))));
            }
            @Override
            public void mouseReleased(final MouseEvent e) {
            	Application.changeScreen("Account");
            	buttonAccount.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 6, 0, new Color(0,0,0)), new MatteBorder(3, 3, 3, 3, new Color(79,175,255))));
            	buttonNewGame.setBackground(new Color(90,90,90));
            	buttonExistingGames.setBackground(new Color(90,90,90));
            	buttonRules.setBackground(new Color(90,90,90));
            	buttonLogout.setBackground(new Color(90,90,90));
            }
        });
		taskbar.add(buttonAccount).setMaximumSize(new Dimension(75,75));
		
		//Add spacer between "Account" and "New Game" buttons
		taskbar.add(Box.createRigidArea(new Dimension(6, 0)));
		
		//Create and add "New Game" button
		buttonNewGame.setBackground(new Color(90,90,90));
		buttonNewGame.setLayout(new GridBagLayout());
		buttonNewGame.add(new JLabel("New Game"));
		buttonNewGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
            	buttonNewGame.setBackground(new Color(110,190,255));
            }
            @Override
            public void mouseReleased(final MouseEvent e) {
            	Application.changeScreen("New Game");
            	buttonAccount.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 6, 0, new Color(0,0,0)), new MatteBorder(3, 3, 3, 3, new Color(90,90,90))));
            	buttonNewGame.setBackground(new Color(79,175,255));
            	buttonExistingGames.setBackground(new Color(90,90,90));
            	buttonRules.setBackground(new Color(90,90,90));
            	buttonLogout.setBackground(new Color(90,90,90));
            }
        });
		taskbar.add(buttonNewGame);
		
		//Add spacer between "New Game" and "Existing Games" buttons
		taskbar.add(Box.createRigidArea(new Dimension(3, 0)));
		
		//Create and add "Existing Games" button
		buttonExistingGames.setBackground(new Color(90,90,90));
		buttonExistingGames.setLayout(new GridBagLayout());
		buttonExistingGames.add(new JLabel("Existing Games"));
		buttonExistingGames.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
            	buttonExistingGames.setBackground(new Color(110,190,255));
            }
            @Override
            public void mouseReleased(final MouseEvent e) {
            	Application.changeScreen("Existing Games");
            	buttonAccount.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 6, 0, new Color(0,0,0)), new MatteBorder(3, 3, 3, 3, new Color(90,90,90))));
            	buttonNewGame.setBackground(new Color(90,90,90));
            	buttonExistingGames.setBackground(new Color(79,175,255));
            	buttonRules.setBackground(new Color(90,90,90));
            	buttonLogout.setBackground(new Color(90,90,90));
            }
        });
		taskbar.add(buttonExistingGames);
		
		//Add spacer between "Existing Games" and "Rules" button.
		taskbar.add(Box.createRigidArea(new Dimension(3, 0)));
		
		//Create and add "Rules" button
		buttonRules.setBackground(new Color(90,90,90));
		buttonRules.setLayout(new GridBagLayout());
		buttonRules.add(new JLabel("Rules"));
		buttonRules.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
            	buttonRules.setBackground(new Color(110,190,255));
            }
            @Override
            public void mouseReleased(final MouseEvent e) {
            	Application.changeScreen("Rules");
            	buttonAccount.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 6, 0, new Color(0,0,0)), new MatteBorder(3, 3, 3, 3, new Color(90,90,90))));
            	buttonNewGame.setBackground(new Color(90,90,90));
            	buttonExistingGames.setBackground(new Color(90,90,90));
            	buttonRules.setBackground(new Color(79,175,255));
            	buttonLogout.setBackground(new Color(90,90,90));
            }
        });
		taskbar.add(buttonRules);
		
		//Add spacer between "Rules" and "Logout" buttons
		taskbar.add(Box.createRigidArea(new Dimension(3, 0)));
		
		//Create and add "Logout" button
		buttonLogout.setBackground(new Color(90,90,90));
		buttonLogout.setLayout(new GridBagLayout());
		buttonLogout.add(new JLabel("Logout"));
		buttonLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
            	buttonLogout.setBackground(new Color(110,190,255));
            }
            @Override
            public void mouseReleased(final MouseEvent e) {
            	Application.changeScreen("Login");
            	buttonAccount.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 6, 0, new Color(0,0,0)), new MatteBorder(3, 3, 3, 3, new Color(90,90,90))));
            	buttonNewGame.setBackground(new Color(90,90,90));
            	buttonExistingGames.setBackground(new Color(90,90,90));
            	buttonRules.setBackground(new Color(90,90,90));
            	buttonLogout.setBackground(new Color(79,175,255));
            }
        });
		taskbar.add(buttonLogout);
		
		//Add black padding all around taskbar
		taskbar.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 8, 0, new Color(79,175,255)), new MatteBorder(6, 6, 0, 6, new Color(10,10,10))));
		
	}
}

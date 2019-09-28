package Templates;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.Frame;
import GUI.Helpers;
import GUI.Panel;

public class MainTemplate {
	public static void generateMain(JFrame frame){
		
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
		
		//add taskbar to background panel
		backgroundPanel.add(taskbar, BorderLayout.PAGE_START);
	
		//backgroundPanel.add(workingPanel, BorderLayout.CENTER);
	
		Panel.setWorkingPanelBackground(backgroundPanel);
		
		
		//set the panel that a screen should load into
		Panel.setWorkingPanel(workingPanel, true);
		
		//frame.setContentPane(backgroundPanel);
		
		//set the template variable
		Frame.setTemplate(2);
		
		//finalize the frame
		Frame.finalize(frame);
	}
	
	private static void generateTaskbar(JPanel taskbar){
		//create the 5 buttons (account, new game, existing games, rules, logout)
		taskbar.setLayout(new GridLayout(1, 5));

		//add image button to taskbar
		taskbar.add(Helpers.button(Helpers.getImage("./Images/AccountDefaultImage.jpg"), new Dimension(100, 100)));
		taskbar.add(Helpers.button("New Game"));
		taskbar.add(Helpers.button("Existing Games"));
		taskbar.add(Helpers.button("Rules"));
		taskbar.add(Helpers.button("Logout"));
	}
}

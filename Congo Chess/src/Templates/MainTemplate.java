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
		JPanel background = new JPanel();
		background.setLayout(new BorderLayout());
		
		//create the taskbar
		JPanel taskbar = new JPanel();
		
		generateTaskbar(taskbar);
		
		//create the working panel
		JPanel workingPanel = new JPanel();

		//make working panel background invisible so it wont cover the Frame background
		workingPanel.setOpaque(false);
		
		//add taskbar to background panel
		background.add(taskbar, BorderLayout.PAGE_START);
		
		//add working panel to background panel
		background.add(workingPanel, BorderLayout.CENTER);
		
		//set the panel that a screen should load into
		Panel.setWorkingPanel(workingPanel, true);
		
		//set the entire background into the pane
		frame.setContentPane(background);
		
		//finalize the frame
		Frame.finalize(frame);
	}
	
	private static void generateTaskbar(JPanel taskbar){
		//create the 5 buttons (account, new game, existing games, rules, logout)
		taskbar.setLayout(new GridLayout(1, 5));

		//add image button to taskbar
		taskbar.add(Helpers.Button(Helpers.getImage("./Images/AccountDefaultImage.jpg"), new Dimension(100, 100)));
		taskbar.add(Helpers.Button("New Game"));
		taskbar.add(Helpers.Button("Existing Games"));
		taskbar.add(Helpers.Button("Rules"));
		taskbar.add(Helpers.Button("Logout"));
	}
}

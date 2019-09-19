package Templates;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.Frame;
import GUI.Panel;

public class MainTemplate {
	public static void generateMain(JFrame frame){
		//create the background panel to hold all new panels
		JPanel background = new JPanel();
		background.setLayout(new BorderLayout());
		
		//create the taskbar
		JPanel taskbar = new JPanel();
		taskbar.setBackground(Color.BLACK);
		
		//create the working panel
		JPanel workingPanel = new JPanel();

		//make working panel background invisible so it wont cover the Frame background
		workingPanel.setOpaque(false);
		
		//add taskbar to background panel
		background.add(taskbar, BorderLayout.PAGE_START);
		
		//add working panel to background panel
		background.add(workingPanel, BorderLayout.CENTER);
		
		//set the panel that a screen should load into
		Panel.setWorkingPanel(workingPanel);
		
		//set the frame dimensions
		Frame.setDimensions(1000,1000);
		
		//set the entire background into the pane
		frame.setContentPane(background);
	}
	
	private static void generateTaskbar(JFrame taskbar){
		//create the 5 buttons (account, new game, existing games, rules, logout)
	}
}

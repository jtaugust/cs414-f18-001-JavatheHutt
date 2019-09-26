package Templates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.Helpers;
import GUI.Panel;

public class AccountTemplate{
	public static void generateAccount(JFrame frame) {
		//this template is the exact same as the main, except it must also have the sidebar
		MainTemplate.generateMain(frame);
		
		//get the main template working panel 
		JPanel workingPanel = Panel.getWorkingPanel();
		
		//set the workingPanel layout
		workingPanel.setLayout(new BorderLayout());
		
		workingPanel.add(generateSidebar(), BorderLayout.LINE_START);
		
		//set the panel that a screen should load into
		Panel.setWorkingPanel(workingPanel, false);
	}
	
	private static JPanel generateSidebar(){
		//create the sidepanel
		JPanel sidebar = new JPanel();
		
		//set the sidePanel layout to a 3 row by 1 column grid
		sidebar.setLayout(new GridLayout(2, 1));
		
		//create and add the 2 buttons
		sidebar.add(Helpers.button("Match History"));
		sidebar.add(Helpers.button("UnRegister"));
		
		return sidebar;
	}
}

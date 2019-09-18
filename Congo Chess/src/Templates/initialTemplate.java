package Templates;

import java.awt.*;

import javax.swing.*;

import GUI.Frame;
import GUI.Panel;
import Screens.Login_Screen;

public class initialTemplate {
	public static void generateInitial(JFrame frame) {
		//create background panel
		JPanel backgroundPanel = Panel.background("./Images/Background.jpg");
		backgroundPanel.setLayout(new BorderLayout());
		
		//add the logo to top of background panel
		backgroundPanel.add(GUI.Label.getImageLabel("./Images/Logo.png"), BorderLayout.PAGE_START);
		
		//create panel for working area
		JPanel workingPanel = GUI.Panel.panel();
		
		//add panel to background panel
		backgroundPanel.add(workingPanel, BorderLayout.CENTER);
		
		//set the panel that a screen should load into
		Panel.setWorkingPanel(workingPanel);
		
		//set the frame dimensions
		Frame.setDimensions(600, 800);
		
		frame.setContentPane(backgroundPanel);
		
	}
}
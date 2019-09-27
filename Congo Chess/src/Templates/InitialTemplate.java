package Templates;

import java.awt.*;

import javax.swing.*;

import GUI.Frame;
import GUI.Panel;
import Screens.LoginScreen;

public class InitialTemplate {
	public static void generateInitial(JFrame frame) {
		//set the frame dimensions
		Frame.setDimensions(600, 800);
		
		//create background panel
		JPanel backgroundPanel = Panel.setBackground("./Images/Background.jpg");
		backgroundPanel.setLayout(new BorderLayout());
		
		//add the logo to top of background panel
		backgroundPanel.add(GUI.Label.getImageLabel("./Images/Logo.png"), BorderLayout.PAGE_START);
		
		//set the working panel background
		Panel.setWorkingPanelBackground(backgroundPanel);
		
		//make working panel background invisible so it wont cover the Frame background
		Panel.setWorkingPanel(false);
		
		//set the template variable
		Frame.setTemplate(1);
		
		//finalize the frame
		Frame.finalize(frame);
	}
}
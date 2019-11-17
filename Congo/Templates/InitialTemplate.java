package Templates;

import java.awt.*;

import javax.swing.*;

import GUI.Frame;
import GUI.Panel;
import Screens.LoginScreen;

public class InitialTemplate extends BackgroundTemplate{
	
	public InitialTemplate(Frame frame) {
		this.frame = frame;
	}
	
	@Override
	public JPanel generateTemplate() {
		
		//create background panel
		JPanel backgroundPanel = Panel.setBackground("./Images/BackgroundDark.jpg", 600, 800);
		backgroundPanel.setLayout(new BorderLayout());
		
		//Create inner panel to hold both logos
		JPanel logo = new JPanel();
		logo.setLayout(new BorderLayout());

		//Create top logo
		JPanel xgames = new JPanel();
		xgames.setBorder(BorderFactory.createMatteBorder(10, 0, 10, 0, Color.black));
		xgames.add(GUI.Label.getImageLabel("./Images/Xgames.png"));
		xgames.setBackground(Color.black);

		//Create bottom logo
		JPanel congo = new JPanel();
		congo.setBorder(BorderFactory.createMatteBorder(0, 0, 6, 0, new Color(79,175,255)));
		congo.add(GUI.Label.getImageLabel("./Images/congo.png"));
		congo.setBackground(new Color(255,255,255));

		//add logos to parent panel
		logo.add(xgames,BorderLayout.PAGE_START);
		logo.add(congo,BorderLayout.PAGE_END);

		//add logo to background panel
		backgroundPanel.add(logo, BorderLayout.PAGE_START);
		
		return backgroundPanel;
	}
}
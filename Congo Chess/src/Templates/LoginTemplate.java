package Templates;

import java.awt.*;

import javax.swing.*;

import GUI.*;

public class LoginTemplate {
	public static void generateLogin(JPanel background) {
		//add the logo to top of background panel
		background.add(GUI.Label.getImageLabel("./Images/Logo.png"), BorderLayout.PAGE_START);
		//create panel for login
		JPanel panel = GUI.Panel.panel();
		//fill login panel
		fillPanel(panel);
		//add panel to background panel
		background.add(panel, BorderLayout.CENTER);
	}
	
	//fill out the login information panel
	private static void fillPanel(JPanel panel) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Helpers.Spacer(100, 150));
		panel.add(Helpers.textField("Username", new Dimension(200, 50), Helpers.newFont(22)));
		panel.add(Helpers.Spacer(200, 50));
		panel.add(Helpers.textField("Password", new Dimension(200, 50), Helpers.newFont(22)));
		panel.add(Helpers.Spacer(200,  50));
		panel.add(Helpers.Button("Login", new Dimension(100, 30)));
		panel.add(Helpers.Spacer(200, 50));
		panel.add(Helpers.Button("Dont have an account? Create one now!", new Dimension(250, 30)));
	}
}
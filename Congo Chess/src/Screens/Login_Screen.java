package Screens;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

import GUI.Helpers;
import GUI.Panel;

public class Login_Screen {
	//given this screen is the start screen, its method is the bootup method.
	public static void Screen(){
		JPanel panel = Panel.getWorkingPanel();
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

package Screens;

import java.awt.Color;

import javax.swing.JPanel;

import App.Application;
import GUI.Panel;

public class RegistrationScreen {
	public static void screen(){
		Application.setCurrentScreen("Register");
		System.out.println("here in register");
		JPanel panel = Panel.getWorkingPanel();
		panel.setBackground(Color.PINK);
	}
}

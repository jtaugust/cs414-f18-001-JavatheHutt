package Screens;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import App.Application;
import GUI.Panel;

public class AccountScreen {
	public static void screen() {
		Application.setCurrentScreen("Account");
		JPanel panel = Panel.getWorkingPanel();
		panel.setBackground(new Color(50,50,50));
	}
}

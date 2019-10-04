package Screens;

import java.awt.Color;

import javax.swing.JPanel;

import App.Application;
import GUI.Panel;

public class AccountScreen {
	public static void screen() {
		System.out.println("here in account");
		Application.setCurrentScreen("Account");
		JPanel panel = Panel.getWorkingPanel();
		panel.setBackground(Color.black);
		panel.setBackground(new Color(50,50,50));
	}
}
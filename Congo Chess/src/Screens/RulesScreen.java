package Screens;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import App.Application;
import GUI.Panel;

public class RulesScreen {
	public static void screen() {
		Application.setCurrentScreen("Rules");
		JPanel panel = Panel.getWorkingPanel();
		panel.setBackground(new Color(50,50,50));
	}
}

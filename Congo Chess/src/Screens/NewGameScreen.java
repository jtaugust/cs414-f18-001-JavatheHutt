package Screens;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import App.Application;
import GUI.Panel;

public class NewGameScreen {
	public static void screen() {
		Application.setCurrentScreen("New Game");
		JPanel panel = Panel.getWorkingPanel();
		panel.setBackground(new Color(50,50,50));
	}
}

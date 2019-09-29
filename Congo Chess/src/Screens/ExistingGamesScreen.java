package Screens;

import java.awt.Color;

import javax.swing.JPanel;

import App.Application;
import GUI.Panel;

public class ExistingGamesScreen {
	public static void screen() {
		Application.setCurrentScreen("Existing Games");
		System.out.println("here in existing games");
		JPanel panel = Panel.getWorkingPanel();
		panel.setBackground(Color.cyan);
	}
}

package Screens;

import java.awt.Color;

import javax.swing.JPanel;

import App.Application;
import GUI.Panel;

public class MatchHistoryScreen {
	public static void screen() {
		Application.setCurrentScreen("Match History");
		System.out.println("here in match history");
		JPanel panel = Panel.getWorkingPanel();
		panel.setBackground(Color.green);
	}
}

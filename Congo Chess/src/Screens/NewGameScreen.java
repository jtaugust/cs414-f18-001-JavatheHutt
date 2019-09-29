package Screens;

import java.awt.Color;

import javax.swing.JPanel;

import App.Application;
import GUI.Panel;

public class NewGameScreen {
	public static void screen() {
		Application.setCurrentScreen("New Game");
		System.out.println("here in new game");
		JPanel panel = Panel.getWorkingPanel();
		panel.setBackground(Color.magenta);
	}
}

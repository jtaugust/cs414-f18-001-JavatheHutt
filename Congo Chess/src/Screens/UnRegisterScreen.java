package Screens;

import java.awt.Color;

import javax.swing.JPanel;

import App.Application;
import GUI.Panel;

public class UnRegisterScreen {
	public static void screen() {
		Application.setCurrentScreen("UnRegister");
		System.out.println("here in unregister");
		JPanel panel = Panel.getWorkingPanel();
		panel.setBackground(Color.yellow);

	}
}

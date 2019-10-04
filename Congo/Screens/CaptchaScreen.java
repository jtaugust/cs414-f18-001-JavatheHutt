package Screens;

import java.awt.Color;

import javax.swing.JPanel;

import App.Application;
import GUI.Panel;

public class CaptchaScreen {
	public static void screen() {
		Application.setCurrentScreen("Captcha");
		System.out.println("here in captcha");
		JPanel panel = Panel.getWorkingPanel();
		panel.setBackground(Color.blue);
	}
}

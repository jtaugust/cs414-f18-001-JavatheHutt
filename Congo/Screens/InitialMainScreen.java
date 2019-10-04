package Screens;

import java.awt.Color;

import javax.swing.*;

import App.Application;
import GUI.Panel;

public class InitialMainScreen {
	
	public static void screen(){
		Application.setCurrentScreen("InitialMain");
		JPanel panel = Panel.getWorkingPanel();
		panel.setBackground(Color.DARK_GRAY);
	}
}

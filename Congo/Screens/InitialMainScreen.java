package Screens;

import java.awt.Color;

import javax.swing.*;

import App.Application;
import GUI.Panel;

public class InitialMainScreen extends Screen{
	
	public static void screen(){
		Application.setCurrentScreen("InitialMain");
		JPanel panel = Panel.getWorkingPanel();
		panel.setBackground(Color.DARK_GRAY);
	}

	@Override
	void setPanel() {
		// TODO Auto-generated method stub
		
	}
}

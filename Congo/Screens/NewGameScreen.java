package Screens;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import App.Application;
import GUI.Panel;

public class NewGameScreen extends Screen  {
	
	public static final Color darkGray = new Color(50,50,50);
	public static final Color mediumGray = new Color(70,70,70);
	public static final Color lightGray = new Color(90,90,90);
	public static final Color highlightGray = new Color(120,120,120);
	public static final Color blue = new Color(79,175,255);
	
	public NewGameScreen() {
		error = 0;
		name = "New Game";
		
	}

	@Override
	public void setScreen() {
		workingPanel.setBackground(darkGray);
	}

	@Override
	void setErrorCards() {
		// TODO Auto-generated method stub
		
	}
}
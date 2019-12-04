package Screens;

import java.awt.Color;

import javax.swing.*;

import App.Application;
import GUI.Panel;

public class InitialMainScreen extends Screen{
	
	public static final Color darkGray = new Color(50,50,50);
	public static final Color mediumGray = new Color(70,70,70);
	public static final Color lightGray = new Color(90,90,90);
	public static final Color highlightGray = new Color(120,120,120);
	public static final Color blue = new Color(79,175,255);
	
	public InitialMainScreen() {
		this.error = 0;
		this.name = "InitialMain";
		
	}

	@Override
	public void setScreen() {
		workingPanel.setOpaque(true);
		workingPanel.setBackground(darkGray);
	}

	@Override
	void setErrorCards() {
		// TODO Auto-generated method stub
		
	}
}

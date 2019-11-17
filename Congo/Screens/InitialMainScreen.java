package Screens;

import java.awt.Color;

import javax.swing.*;

import App.Application;
import GUI.Panel;

public class InitialMainScreen extends Screen{
	
	public InitialMainScreen() {
		this.error = 0;
		this.name = "InitialMain";
		
	}

	@Override
	public void setScreen() {
		workingPanel.setOpaque(true);
		workingPanel.setBackground(Color.black);
	}

	@Override
	void setErrorCards() {
		// TODO Auto-generated method stub
		
	}
}

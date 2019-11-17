package Screens;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import App.Application;
import GUI.Panel;

public class NewGameScreen extends Screen  {
	public NewGameScreen() {
		error = 0;
		name = "New Game";
		
	}

	@Override
	public void setScreen() {
		System.out.println("here in new game");
		workingPanel.setBackground(Color.magenta);
		workingPanel.setBackground(new Color(50,50,50));
	}

	@Override
	void setErrorCards() {
		// TODO Auto-generated method stub
		
	}
}
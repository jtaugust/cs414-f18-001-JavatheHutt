package Screens;

import java.awt.Color;

import javax.swing.JPanel;

import App.Application;
import GUI.Panel;

public class MatchHistoryScreen extends Screen{
	
	public MatchHistoryScreen() {
		error = 0;
		name = "Match History";
	}
	
	public void setScreen() {
		System.out.println("here in match history");
		workingPanel.setBackground(Color.green);
	}

	@Override
	void setErrorCards() {
		// TODO Auto-generated method stub
		
	}
}

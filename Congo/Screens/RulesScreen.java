package Screens;

import java.awt.Color;

import javax.swing.JPanel;

import App.Application;
import GUI.Panel;

public class RulesScreen extends Screen{
	
	public RulesScreen() {
		this.error = 0;
		this.name = "Rules";
		setErrorCards();
	}
	
	@Override
	public void setScreen() {
		System.out.println("here in rules");
		workingPanel.setBackground(new Color(50,50,50));
	}

	@Override
	void setErrorCards() {
		// TODO Auto-generated method stub
		
	}
}

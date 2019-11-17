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
	void setScreen() {
		//unhide the working panel (login hides it)
		workingPanel.setOpaque(true);
		workingPanel.setBackground(Color.black);
	}

	@Override
	void setErrorCards() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void showErrorCard() {
		// TODO Auto-generated method stub
		
	}
}

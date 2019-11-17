package Screens;

import java.awt.CardLayout;

import javax.swing.JPanel;

import GUI.Frame;



abstract class Screen {
	//this is the parent to all screens
	int error;
	
	WorkingPanel WorkingPanel;
	
	String name;
	
	JPanel workingPanel;
	
	JPanel errorCards;

	abstract void setScreen();
	
	abstract void setErrorCards();
	
	protected void setError(int err){
		this.error = err;
		showErrorCard();
	}
	
	protected void showErrorCard() {
		((CardLayout) errorCards.getLayout()).show(errorCards, String.valueOf(error));
	}
	
}

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

	abstract void setPanel();
	
	abstract void setErrorCards();
	
	abstract void showErrorCard();
	
}

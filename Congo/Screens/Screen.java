package Screens;

import javax.swing.JPanel;

import GUI.Frame;



abstract class Screen {
	//this is the parent to all screens
	int error;
	
	WorkingPanel WorkingPanel;
	
	String name;
	
	JPanel workingPanel;

	abstract void setPanel();
	
}

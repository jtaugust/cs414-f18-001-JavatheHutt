package Screens;

import java.awt.Color;

import javax.swing.JPanel;

import App.Application;
import GUI.Panel;

public class UnRegisterScreen extends Screen {
	
	public UnRegisterScreen() {
		error = 0;
		name = "UnRegister";
	}
	
	@Override
	public void setScreen() {
		System.out.println("here in unregister");
		workingPanel.setBackground(Color.yellow);
	}


	@Override
	void setErrorCards() {
		// TODO Auto-generated method stub
		
	}
}

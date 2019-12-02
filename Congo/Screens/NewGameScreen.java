package Screens;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.JDialog;
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
		JDialog newGame = new JDialog();
		newGame.setBackground(Color.GREEN);
		newGame.add(new JLabel("testing"));
		newGame.setSize(500,500);
		newGame.setVisible(true);
		newGame.setLocationRelativeTo(this.WorkingPanel.frame.getThisFrame());
	}

	@Override
	void setErrorCards() {
		// TODO Auto-generated method stub
		
	}
}
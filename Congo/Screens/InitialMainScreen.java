package Screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InitialMainScreen extends Screen{
	
	public static final Color darkGray = new Color(50,50,50);
	public static final Color mediumGray = new Color(70,70,70);
	public static final Color lightGray = new Color(90,90,90);
	public static final Color highlightGray = new Color(120,120,120);
	public static final Color blue = new Color(79,175,255);
	
	public InitialMainScreen() {
		this.error = 0;
		this.name = "InitialMain";
		
	}

	@Override
	public void setScreen() {
		workingPanel.setOpaque(true);
		workingPanel.setBackground(darkGray);
		workingPanel.setLayout(new GridBagLayout());
		
		JPanel bar = new JPanel();
		bar.setBackground(blue);
		bar.setLayout(new GridBagLayout());
		bar.setPreferredSize(new Dimension(1000, 100));
		bar.setMinimumSize(new Dimension(1000, 100));
		bar.setMaximumSize(new Dimension(1000, 100));
		
		JLabel welcome = new JLabel("Welcome to Congo Chess!");
		welcome.setFont(new Font("Verdana", Font.BOLD, 40));
		bar.add(welcome);
		workingPanel.add(bar);
		
	}

	@Override
	void setErrorCards() {
		
		
	}
}

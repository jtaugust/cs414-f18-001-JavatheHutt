package Screens;

import javax.swing.*;

import App.Application;
import GUI.Panel;

public class InitialMainScreen {
	
	public static void screen(){
		JPanel panel = Panel.getWorkingPanel();
		
		System.out.println(Application.getUser());
	}
}

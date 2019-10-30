package Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import App.Application;
import BoardLogic.CongoBoard;
import GUI.Panel;

public class ExistingGamesScreen {
	private static JLayeredPane mostRecent = null;
	public static void screen() {
		//set the screen to existing games
		Application.setCurrentScreen("Existing Games");
		
		//get the working panel
		JPanel workingPanel = Panel.getWorkingPanel();
		
		workingPanel.setLayout(new GridBagLayout());
	
		JPanel board = new JPanel();
		
		//TODO:
		//Switch this with the most recent board
		//when clicking on the list of existing games, the game clicked will have stored
		//both users in its players[] variable.
		
		String user1 = "Admin1";
		String user2 = "Admin2";
		
		/*
		 * 
		 * TODO:
		 * 
		 * DB method:
		 * SQL connection
		 * sql update to userMatches (completely separate table from all users, primary key is an incrementing number)
		 * userMatches stores: game #, user1, user2, turn (white or black), java object 
		 * 			-java object to SQL table: http://www.java2s.com/Code/Java/Database-SQL-JDBC/Storeandretrieveanobjectfromatable.htm
		 * 
		 * 
		 * 
		 * TODO:
		 * method that calls matchboard
		 * when user clicks on a game from the existing games sideboard, it should have the game #
		 *      -existing games sideboard is a scrollable list, perhaps its id is the game #
		 * 
		 * select * from userMatches where game# = existingGames#
		 * 
		 * this method should call the lock check method to lock and flip the board if necessary 
		 * 
		 * 
		 */
		
		
		//line below should be moved to the invitation class, once user 2 accepts, create board
		//a sent invite should include the user1 in some way to actually create the board.
		board.add(CongoBoard.createBoard(user1, user2));
		board.setBackground(new Color(90,90,90));
	
		workingPanel.add(board);
		
		workingPanel.setBackground(new Color(90,90,90));
	}
}
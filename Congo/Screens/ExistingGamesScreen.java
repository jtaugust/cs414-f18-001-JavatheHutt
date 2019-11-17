package Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.plaf.basic.BasicScrollBarUI;

import BoardLogic.CongoBoard;
import GUI.Helpers;
import GUI.Panel;
import Server.serverGamesHelpers;

public class ExistingGamesScreen extends Screen{
	private static JLayeredPane mostRecent = null;
	public ExistingGamesScreen() {
		this.error = 0;
		this.name = "Existing Games";
		setErrorCards();
	}
	
	@Override
	public void setScreen() {

		//get current games info from database
		serverGamesHelpers current = new serverGamesHelpers();
		ArrayList<String[]> games = null;
		try {
			games = current.readCurrentGames_T(WorkingPanel.getUser());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		workingPanel.setLayout(new BorderLayout());

		
		//create panel for board to be placed on
		JPanel board = new JPanel();
		board.setLayout(new GridBagLayout());
		
		//create panel for game boxes to be placed in
		JPanel sidebar = new JPanel();
		sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.PAGE_AXIS));
		sidebar.setBackground(new Color(90,90,90));
		
		// iterate current games and create game box in sidebar for each
		for(int i = 0; i < games.size(); i++) {
			//get players in game
			String user1 = games.get(i)[1];
			String user2 = games.get(i)[2];
			
			//add space between games
			sidebar.add(Helpers.spacer(10, 10));
			
			//create panel to display game info
			JPanel gameBox = new JPanel();
			gameBox.setLayout(new BorderLayout());
			gameBox.setPreferredSize(new Dimension(295,140));
			gameBox.setMaximumSize(new Dimension(295,140));
			gameBox.setBorder(BorderFactory.createMatteBorder(10,10,0,10, new Color(90,90,90)));
			gameBox.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.black));
			
			//top section of gameBox
			JPanel gameNumber = new JPanel();
			gameNumber.setLayout(new GridBagLayout());
			gameNumber.add(new JLabel(games.get(i)[0]));
			gameNumber.setBorder(BorderFactory.createMatteBorder(5,0,5,0,new Color(110,190,255)));
			gameNumber.setBackground(new Color(110,190,255));
			
			//lower section of gameBox
			JPanel opponent = new JPanel();
			opponent.setLayout(new GridBagLayout());
			opponent.add(new JLabel(user1));
			opponent.add(new JLabel("   vs   "));
			opponent.add(new JLabel(user2));
			opponent.setBackground(new Color(130,130,130));
			
			//add gameNumber and opponent to the gameBox
			gameBox.add(gameNumber, BorderLayout.PAGE_START);
			gameBox.add(opponent, BorderLayout.CENTER);
			
			gameBox.setBackground(new Color(120,120,120));
			gameBox.addMouseListener(new MouseAdapter() {
		  		@Override
		  		public void mousePressed(final MouseEvent e) {
		  			//highlight click
					opponent.setBackground(new Color(160,160,160));
					
					
		  		}
		  		@Override
		  		public void mouseReleased(final MouseEvent e) {
		  			//change board based on gameBox pressed
					opponent.setBackground(new Color(130,130,130));
					board.removeAll();
		  			board.add(CongoBoard.createBoard(user1, user2));
					workingPanel.repaint();
					workingPanel.validate();
		  		}
			});
			
			//add to sidebar
			sidebar.add(gameBox);
		}
		
	
		// add scrollbar and change the style of it
		JScrollPane scrollPane = new JScrollPane(sidebar, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(new Color(90,90,90));
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI(){
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = new Color(120,120,120);
                this.thumbDarkShadowColor = new Color(120,120,120);
                this.thumbLightShadowColor = new Color(120,120,120);
                this.thumbHighlightColor = new Color(120,120,120);
                this.trackHighlightColor = new Color(120,120,120);
                this.trackColor = Color.BLACK;
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
            	JButton button = new JButton();
            	button.setPreferredSize(new Dimension(0,0));
            	return button;
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
            	JButton button = new JButton();
            	button.setPreferredSize(new Dimension(0,0));
            	return button;
            }
        });
		
		board.add(CongoBoard.createBoard(games.get(0)[1], games.get(0)[2]));
		board.setBackground(new Color(50,50,50));
		board.setBorder(BorderFactory.createEmptyBorder(0,10,0,70));
		
		//create panel on the opposite side of the sidebar (main area with board)
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BorderLayout());
		rightSide.add(board, BorderLayout.LINE_START);
		
		//add the sidebar and the main area to the working panel
		workingPanel.add(scrollPane, BorderLayout.CENTER);
		workingPanel.add(rightSide, BorderLayout.LINE_END);
		workingPanel.setBackground(new Color(90,90,90));
	}

	@Override
	void setErrorCards() {
		// TODO Auto-generated method stub
		
	}
}
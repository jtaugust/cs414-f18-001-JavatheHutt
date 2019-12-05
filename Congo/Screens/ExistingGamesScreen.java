package Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import App.Application;
import BoardLogic.CongoBoard;
import GUI.Frame;
import GUI.Helpers;
import GUI.Panel;
import Server.serverGamesHelpers;

public class ExistingGamesScreen extends Screen{
	
	public static final Color darkGray = new Color(50,50,50);
	public static final Color lightGray = new Color(90,90,90);
	public static final Color highlightGray = new Color(120,120,120);
	public static final Color blue = new Color(79,175,255);
	
	public ExistingGamesScreen() {
		this.error = 0;
		this.name = "Existing Games";
		setErrorCards();
	}
	
	@Override
	public void setScreen() {
		
		//get current games info from database
		serverGamesHelpers current = new serverGamesHelpers();
		ArrayList<String[]> games = new ArrayList<String[]>();
		try {
			games = current.readCurrentGames_T(WorkingPanel.getUser());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		workingPanel.setLayout(new BorderLayout());
		
		//check if user has any existing games
		if(games.size() != 0) {
			
			//create panel for board to be placed on
			JPanel board = new JPanel();
			board.setLayout(new GridBagLayout());
			
			//create panel for game boxes to be placed in
			JPanel sidebar = new JPanel();
			sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.PAGE_AXIS));
			sidebar.setBackground(lightGray);
			
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
				gameBox.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,10,0,10, lightGray), new MatteBorder(2,2,2,2, Color.black)));
				
				//top section of gameBox
				JPanel gameNumber = new JPanel();
				gameNumber.setLayout(new GridBagLayout());
				gameNumber.add(new JLabel(games.get(i)[0]));
				gameNumber.setBorder(BorderFactory.createMatteBorder(5,0,5,0,blue));
				gameNumber.setBackground(blue);
				
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
				
				gameBox.setBackground(highlightGray);
				
				final Integer innerGameID = new Integer(games.get(i)[0]);
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
			  			board.add(CongoBoard.createBoard(user1, user2, innerGameID, WorkingPanel.getUser()));
						workingPanel.repaint();
						workingPanel.validate();
			  		}
				});
				
				//add to sidebar
				sidebar.add(gameBox);
			}
			sidebar.add(Helpers.spacer(10, 10));
			
			// add scrollbar and change the style of it
			JScrollPane scrollPane = new JScrollPane(sidebar, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBackground(lightGray);
			scrollPane.getVerticalScrollBar().setUnitIncrement(16);
			scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI(){
	            @Override
	            protected void configureScrollBarColors(){
	                this.thumbColor = highlightGray;
	                this.thumbDarkShadowColor = highlightGray;
	                this.thumbLightShadowColor = highlightGray;
	                this.thumbHighlightColor = highlightGray;
	                this.trackHighlightColor = highlightGray;
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
			
			final Integer defaultGameID = new Integer(games.get(0)[0]);
			board.add(CongoBoard.createBoard(games.get(0)[1], games.get(0)[2], defaultGameID, WorkingPanel.getUser()));
			board.setBackground(darkGray);
			board.setBorder(BorderFactory.createEmptyBorder(0,10,0,70));
			
			//create panel on the opposite side of the sidebar (main area with board)
			JPanel rightSide = new JPanel();
			rightSide.setLayout(new BorderLayout());
			rightSide.add(board, BorderLayout.LINE_START);
			
			//add the sidebar and the main area to the working panel
			workingPanel.add(scrollPane, BorderLayout.CENTER);
			workingPanel.add(rightSide, BorderLayout.LINE_END);
			workingPanel.setBackground(lightGray);
			
		} else { //no existing games
			
			JPanel outer = new JPanel();
			outer.setBackground(darkGray);
			outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));
			outer.setBorder(new EmptyBorder(300,150,0,150));
			
			JPanel labelHolder = new JPanel();
			outer.add(labelHolder);
			labelHolder.setBackground(lightGray);
			labelHolder.setLayout(new GridBagLayout());
			labelHolder.setMaximumSize(new Dimension(1000,75));
		    
			JLabel label = new JLabel("Looks like you don't have any games in progress. Head over to \"New Game\" to get started!");
			label.setFont(new Font("Verdana", Font.PLAIN, 14));
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			labelHolder.add(label);
			
			workingPanel.add(outer);
			workingPanel.setBackground(darkGray);
			
		}
	}

	@Override
	void setErrorCards() {
		// TODO Auto-generated method stub
		
	}
}
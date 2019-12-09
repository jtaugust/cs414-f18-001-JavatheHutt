package Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import BoardLogic.CongoBoard;
import GUI.Helpers;
import Server.serverGamesHelpers;

public class ExistingGamesScreen extends Screen{
	
	public static final Color darkGray = new Color(50,50,50);
	public static final Color lightGray = new Color(90,90,90);
	public static final Color highlightGray = new Color(120,120,120);
	public static final Color blue = new Color(79,175,255);
	ArrayList<JPanel> highlights1 = new ArrayList<JPanel>();
	ArrayList<JPanel> highlights2 = new ArrayList<JPanel>();
	
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
				final Integer innerGameID = new Integer(games.get(i)[0]);

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
				gameNumber.add(new JLabel("Game ID: " + games.get(i)[0]));
				gameNumber.setBorder(BorderFactory.createMatteBorder(10,0,10,0,new Color(60,130,180)));
				gameNumber.setBackground(new Color(60,130,180));
				highlights1.add(gameNumber);
				
				
				//lower section of gameBox
				JPanel opponent = new JPanel();
				opponent.setLayout(new GridBagLayout());
				opponent.setBackground(new Color(110,110,110));
				JLabel username1 = new JLabel(user1);
				JLabel vs = new JLabel("   vs   ");
				JLabel username2 = new JLabel(user2);
				highlights2.add(opponent);
				
				username1.setFont(new Font("Verdana", Font.PLAIN, 16));
				vs.setFont(new Font("Verdana", Font.BOLD, 12));
				username2.setFont(new Font("Verdana", Font.PLAIN, 16));

				opponent.add(username1);
				opponent.add(vs);
				opponent.add(username2);
				
				//add gameNumber and opponent to the gameBox
				gameBox.add(gameNumber, BorderLayout.PAGE_START);
				gameBox.add(opponent, BorderLayout.CENTER);
				
				gameBox.setBackground(highlightGray);
				
				gameBox.addMouseListener(new MouseAdapter() {
			  		@Override
			  		public void mousePressed(final MouseEvent e) {
			  			//reset all highlights
			  			for(int i = 0; i < highlights1.size(); i++) {
			  				highlights1.get(i).setBackground(new Color(60,130,180));
			  				highlights1.get(i).setBorder(BorderFactory.createMatteBorder(10,0,10,0,new Color(60,130,180)));
			  				highlights2.get(i).setBackground(new Color(110,110,110));
			  			}
			  			//highlight game selected
			  			gameNumber.setBackground(blue);
			  			gameNumber.setBorder(BorderFactory.createMatteBorder(10,0,10,0,blue));
						opponent.setBackground(new Color(180,180,180));
						
						
			  		}
			  		@Override
			  		public void mouseReleased(final MouseEvent e) {
			  			//change board based on gameBox pressed
						opponent.setBackground(new Color(160,160,160));
						board.removeAll();
			  			board.add(CongoBoard.createBoard(user1, user2, innerGameID, WorkingPanel.getUser()));
						workingPanel.repaint();
						workingPanel.validate();
			  		}
				});
				
				//highlight game selected
	  			highlights1.get(0).setBackground(blue);
	  			highlights1.get(0).setBorder(BorderFactory.createMatteBorder(10,0,10,0,blue));
	  			highlights2.get(0).setBackground(new Color(180,180,180));
				
				//add to sidebar
				sidebar.add(gameBox);
			}
			sidebar.add(Helpers.spacer(10, 10));
			
			// add scrollbar and change the style of it
			JScrollPane scrollPane = new JScrollPane(sidebar, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBackground(lightGray);
			scrollPane.setBorder(BorderFactory.createEmptyBorder());
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
			board.setBorder(new MatteBorder(30,40,30,40,darkGray));
			
			//create panel on the opposite side of the sidebar (main area with board)
			JPanel rightSide = new JPanel();
			rightSide.setBackground(darkGray);
			rightSide.setLayout(new BorderLayout());
			rightSide.add(board, BorderLayout.PAGE_START);
			
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
		
	}
}
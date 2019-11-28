package Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import App.Application;
import GUI.Panel;
import Server.serverHelpers;

public class AccountScreen extends Screen{
	
	JPanel bottomSection;
	serverHelpers helper;
	public AccountScreen() {
		error = 0;
		name = "Acccount";
		bottomSection = new JPanel();
		helper = new serverHelpers();
		
	}
	
	public void setScreen() {
		workingPanel.setBackground(new Color(90,90,90));
		workingPanel.setLayout(new BoxLayout(workingPanel, BoxLayout.Y_AXIS));
		
		//get user info from database
		String[] userInfo = null;
		try {
			userInfo = helper.readUserInfo_T(WorkingPanel.getUser());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//create and add profile picture
		//TODO: allow user to upload new profile picture, save it to the database, and get that image whenever displaying their avatar
		ImageIcon image = new ImageIcon(new ImageIcon("./Images/AccountDefaultImage.jpg").getImage().getScaledInstance(175, 175, Image.SCALE_DEFAULT));
		JLabel avatar = new JLabel(image);
		avatar.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(25,0,25,0), new MatteBorder(5,5,5,5, Color.black)));
		avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//create and add username
		JLabel username = new JLabel(userInfo[0]);
		username.setFont(new Font("Verdana", Font.PLAIN, 28));
		username.setBorder(new EmptyBorder(0,0,20,0));
		username.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//create and add email
		JLabel email = new JLabel(userInfo[1]);
		email.setFont(new Font("Verdana", Font.PLAIN, 20));
		email.setBorder(new EmptyBorder(0,0,25,0));
		email.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//create bottom section to hold "Match History" and "Unregister"
		JPanel buttonSection = new JPanel();
	    buttonSection.setBackground(new Color(50,50,50));
	    buttonSection.setLayout(new BoxLayout(buttonSection, BoxLayout.X_AXIS));
		buttonSection.setMaximumSize(new Dimension(1005,75));
		buttonSection.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,5,0,Color.black), new EmptyBorder(10,10,10,10)));
	    
	    //create "Match History" button
	    JPanel historyButtonHolder = new JPanel();
	    historyButtonHolder.setBackground(new Color(50,50,50));
	    historyButtonHolder.setLayout(new GridBagLayout());
	    
	    JPanel editButton = new JPanel();
	    JPanel historyButton = new JPanel();
	    historyButtonHolder.add(historyButton);
	    historyButton.setBackground(new Color(79,175,255));
	    historyButton.setLayout(new GridBagLayout());
	    historyButton.setBorder(new MatteBorder(2,2,2,2,new Color(79,175,255)));
	   
	    JLabel historylabel = new JLabel("Match History");
	    historyButton.add(historylabel);
	    historylabel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));
	    historyButton.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			historyButton.setBackground(new Color(79,175,255));
	  		    editButton.setBackground(new Color(90,90,90));
	  			createMatchHistory();
	  			
	  		}
		});
	    
	    //create "Edit Profile" button
	    JPanel editButtonHolder = new JPanel();
	    editButtonHolder.setBackground(new Color(50,50,50));
	    editButtonHolder.setLayout(new GridBagLayout());
	    
	    
	    editButtonHolder.add(editButton);
	    editButton.setBackground(new Color(90,90,90));
	    editButton.setLayout(new GridBagLayout());
	    editButton.setBorder(new MatteBorder(2,2,2,2,new Color(79,175,255)));
	   
	    JLabel editlabel = new JLabel("Edit Account Information");
	    editButton.add(editlabel);
	    editlabel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));
	    editButton.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			editButton.setBackground(new Color(79,175,255));
	  			historyButton.setBackground(new Color(90,90,90));
	  			createEditProfile();
	  			
	  		}
		});
	    
		//create "Unregister" button
	    JPanel unregisterButtonHolder = new JPanel();
	    unregisterButtonHolder.setBackground(new Color(50,50,50));
	    unregisterButtonHolder.setLayout(new GridBagLayout());
	    
	    JPanel unregisterButton = new JPanel();
	    unregisterButtonHolder.add(unregisterButton);
	    unregisterButton.setBackground(new Color(90,90,90));
	    unregisterButton.setLayout(new GridBagLayout());
	    unregisterButton.setBorder(new MatteBorder(2,2,2,2,new Color(79,175,255)));

	    JLabel unregisterlabel = new JLabel("Unregister");
	    unregisterButton.add(unregisterlabel);
	    unregisterlabel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));
	    unregisterButton.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {
	  			unregisterButton.setBackground(new Color(120,120,120));
	  		}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			unregisterButton.setBackground(new Color(90,90,90));
	  			String Username = WorkingPanel.getUser();
	  			int err = serverHelpers.tryUnregister(Username);
	  			
	  			if (err == 0) {
	  				WorkingPanel.changeScreen(new LoginScreen());
	  			}
	  			else {
	  				setError(err);
	  			}
	  		}
		});
	    
	    //add to button area
	    buttonSection.add(historyButtonHolder);
	    buttonSection.add(editButtonHolder);
	    buttonSection.add(unregisterButtonHolder);
	    
	    //add to the working panel
	    workingPanel.add(avatar);
	    workingPanel.add(username);
	    workingPanel.add(email);
	    workingPanel.add(buttonSection);
	    
	    //initially load match history
	    createMatchHistory();
	    
	    
	}
	
	void createMatchHistory() {
		
		//clear current components
		this.bottomSection.removeAll();
		this.bottomSection.setLayout(new BorderLayout());
		
		//get info from database
		String[][] history = new String[0][0];
		try {
			history = helper.readMatchHistory_T(WorkingPanel.getUser());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//check if user has a match history
		if(history.length != 0) {
			
			//describe info contents
			JPanel key = new JPanel();
			key.setLayout(new BoxLayout(key, BoxLayout.X_AXIS));
			key.setPreferredSize(new Dimension(1000,100));
			key.setMaximumSize(new Dimension(1000,100));
			key.setMinimumSize(new Dimension(1000,100));
			key.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10,0,0,0, new Color(50,50,50)), new MatteBorder(2,2,2,0, new Color(50,50,50))));
			key.setBackground(new Color(90,90,90));
			
			//create game number label
			JPanel gameNumberKey = new JPanel();
			gameNumberKey.setLayout(new GridBagLayout());
			gameNumberKey.add(new JLabel("#"));
			gameNumberKey.setPreferredSize(new Dimension(75,100));
			gameNumberKey.setMaximumSize(new Dimension(75,100));
			gameNumberKey.setMinimumSize(new Dimension(75,100));
			gameNumberKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(5,0,5,0,new Color(110,190,255)), new MatteBorder(0,5,0,0, new Color(70,70,70))));
			gameNumberKey.setBackground(new Color(90,90,90));
			
			//create username label
			JPanel usernameKey = new JPanel();
			usernameKey.setLayout(new GridBagLayout());
			usernameKey.add(new JLabel("Username"));
			usernameKey.setPreferredSize(new Dimension(200,100));
			usernameKey.setMaximumSize(new Dimension(200,100));
			usernameKey.setMinimumSize(new Dimension(200,100));
			usernameKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(5,0,5,0,new Color(110,190,255)), new MatteBorder(0,5,0,0, new Color(70,70,70))));
			usernameKey.setBackground(new Color(90,90,90));
			
			//create opponent label
			JPanel opponentKey = new JPanel();
			opponentKey.setLayout(new GridBagLayout());
			opponentKey.add(new JLabel("Opponent"));
			opponentKey.setPreferredSize(new Dimension(200,100));
			opponentKey.setMaximumSize(new Dimension(200,100));
			opponentKey.setMinimumSize(new Dimension(200,100));
			opponentKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(5,0,5,0,new Color(110,190,255)), new MatteBorder(0,5,0,0, new Color(70,70,70))));
			opponentKey.setBackground(new Color(90,90,90));
			
			//create start time label
			JPanel startTimeKey = new JPanel();
			startTimeKey.setLayout(new GridBagLayout());
			startTimeKey.add(new JLabel("Start Time"));
			startTimeKey.setPreferredSize(new Dimension(200,100));
			startTimeKey.setMaximumSize(new Dimension(200,100));
			startTimeKey.setMinimumSize(new Dimension(200,100));
			startTimeKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(5,0,5,0,new Color(110,190,255)), new MatteBorder(0,5,0,0, new Color(70,70,70))));
			startTimeKey.setBackground(new Color(90,90,90));
			
			//create end time label
			JPanel endTimeKey = new JPanel();
			endTimeKey.setLayout(new GridBagLayout());
			endTimeKey.add(new JLabel("End Time"));
			endTimeKey.setPreferredSize(new Dimension(200,100));
			endTimeKey.setMaximumSize(new Dimension(200,100));
			endTimeKey.setMinimumSize(new Dimension(200,100));
			endTimeKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(5,0,5,0,new Color(110,190,255)), new MatteBorder(0,5,0,0, new Color(70,70,70))));
			endTimeKey.setBackground(new Color(90,90,90));
			
			//create status label
			JPanel statusKey = new JPanel();
			statusKey.setLayout(new GridBagLayout());
			statusKey.add(new JLabel("Outcome"));
			statusKey.setPreferredSize(new Dimension(125,100));
			statusKey.setMaximumSize(new Dimension(125,100));
			statusKey.setMinimumSize(new Dimension(125,100));
			statusKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(5,0,5,0,new Color(110,190,255)), new MatteBorder(0,5,0,0, new Color(70,70,70))));
			statusKey.setBackground(new Color(90,90,90));
			
			//add info to gamebox
			key.add(gameNumberKey);
			key.add(usernameKey);
			key.add(opponentKey);
			key.add(startTimeKey);
			key.add(endTimeKey);
			key.add(statusKey);
			
			//create panel to store game info
			JPanel dataPanel = new JPanel();
			dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.PAGE_AXIS));
			dataPanel.setBackground(new Color(50,50,50));
			dataPanel.setBorder(new EmptyBorder(0,0,10,0));
	
			//iterate 2D array of match history
			for(int i = 0; i < history.length; i++) {
				//create panel to display game info
				JPanel gameBox = new JPanel();
				gameBox.setLayout(new BoxLayout(gameBox, BoxLayout.X_AXIS));
				gameBox.setPreferredSize(new Dimension(950,75));
				gameBox.setMaximumSize(new Dimension(950,75));
				gameBox.setMinimumSize(new Dimension(950,75));
				gameBox.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10,0,0,0, new Color(50,50,50)), new MatteBorder(2,2,2,0, new Color(50,50,50))));
				gameBox.setBackground(new Color(90,90,90));
				
				//create game number label
				JPanel gameNumber = new JPanel();
				gameNumber.setLayout(new GridBagLayout());
				gameNumber.add(new JLabel(history[i][0]));
				gameNumber.setPreferredSize(new Dimension(50,100));
				gameNumber.setMaximumSize(new Dimension(50,100));
				gameNumber.setMinimumSize(new Dimension(50,100));
				gameNumber.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, new Color(70,70,70))));
				gameNumber.setBackground(new Color(90,90,90));
				
				//create username label
				JPanel username = new JPanel();
				username.setLayout(new GridBagLayout());
				username.add(new JLabel(history[i][1]));
				username.setPreferredSize(new Dimension(200,100));
				username.setMaximumSize(new Dimension(200,100));
				username.setMinimumSize(new Dimension(200,100));
				username.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, new Color(70,70,70))));
				username.setBackground(new Color(90,90,90));
				
				//create opponent label
				JPanel opponent = new JPanel();
				opponent.setLayout(new GridBagLayout());
				opponent.add(new JLabel(history[i][2]));
				opponent.setPreferredSize(new Dimension(200,100));
				opponent.setMaximumSize(new Dimension(200,100));
				opponent.setMinimumSize(new Dimension(200,100));
				opponent.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, new Color(70,70,70))));
				opponent.setBackground(new Color(90,90,90));
				
				//create start time label
				JPanel startTime = new JPanel();
				startTime.setLayout(new GridBagLayout());
				startTime.add(new JLabel(history[i][3]));
				startTime.setPreferredSize(new Dimension(200,100));
				startTime.setMaximumSize(new Dimension(200,100));
				startTime.setMinimumSize(new Dimension(200,100));
				startTime.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, new Color(70,70,70))));
				startTime.setBackground(new Color(90,90,90));
				
				//create end time label
				JPanel endTime = new JPanel();
				endTime.setLayout(new GridBagLayout());
				endTime.add(new JLabel(history[i][4]));
				endTime.setPreferredSize(new Dimension(200,100));
				endTime.setMaximumSize(new Dimension(200,100));
				endTime.setMinimumSize(new Dimension(200,100));
				endTime.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, new Color(70,70,70))));
				endTime.setBackground(new Color(90,90,90));
				
				//create status label
				JPanel status = new JPanel();
				status.setLayout(new GridBagLayout());
				status.add(new JLabel(history[i][5]));
				status.setPreferredSize(new Dimension(100,100));
				status.setMaximumSize(new Dimension(100,100));
				status.setMinimumSize(new Dimension(100,100));
				status.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, new Color(70,70,70))));
				status.setBackground(new Color(90,90,90));
				
				//add info to gamebox
				gameBox.add(gameNumber);
				gameBox.add(username);
				gameBox.add(opponent);
				gameBox.add(startTime);
				gameBox.add(endTime);
				gameBox.add(status);
				
				//add gamebox to the data panel
				dataPanel.add(gameBox);
				
			}
			
			// add scrollbar and change the style of it
			JScrollPane scrollPane = new JScrollPane(dataPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBackground(new Color(90,90,90));
			scrollPane.setBorder(BorderFactory.createEmptyBorder());
			scrollPane.getVerticalScrollBar().setUnitIncrement(16);
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
	
			this.bottomSection.add(key, BorderLayout.PAGE_START);
			this.bottomSection.add(scrollPane, BorderLayout.CENTER);
			this.bottomSection.setBackground(new Color(90,90,90));
			
		} else { //user has no match history
			
			//create message
			JPanel outer = new JPanel();
			outer.setBackground(new Color(50,50,50));
			outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));
			outer.setBorder(new EmptyBorder(175,150,0,150));
			JPanel labelHolder = new JPanel();
			outer.add(labelHolder);
			labelHolder.setBackground(new Color(90,90,90));
			labelHolder.setLayout(new GridBagLayout());
			labelHolder.setMaximumSize(new Dimension(1000,75));
			JLabel label = new JLabel("Looks like you haven't completed any games yet. Head over to \"New Game\" to get started!");
			label.setFont(new Font("Verdana", Font.PLAIN, 14));
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			labelHolder.add(label);
			
			//add message
			this.bottomSection.add(outer);
			
		}
		
		//update panel
		this.bottomSection.revalidate();
		this.bottomSection.repaint();
	
		//add it back to the working panel
		workingPanel.add(this.bottomSection);
	}
	
	void createEditProfile() {
		//clear current components
		this.bottomSection.removeAll();
		
		//TESTING: check for tab change
		this.bottomSection.setBackground(Color.white);
		
		//update panel
		this.bottomSection.revalidate();
		this.bottomSection.repaint();
		
		//add it back to the working panel
		workingPanel.add(this.bottomSection);
	}
	
	@Override
	void setErrorCards() {
		// TODO Auto-generated method stub
		
	}
	
	
}
package Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import GUI.Helpers;
import GUI.Label;
import GUI.Panel;
import Server.serverHelpers;

public class AccountScreen extends Screen{
	
	public static final Color darkGray = new Color(50,50,50);
	public static final Color mediumGray = new Color(70,70,70);
	public static final Color lightGray = new Color(90,90,90);
	public static final Color highlightGray = new Color(120,120,120);
	public static final Color blue = new Color(79,175,255);
	
	JPanel bottomSection;
	serverHelpers helper;
	
	public AccountScreen() {
		error = 0;
		name = "Account";
		setErrorCards();
		
		bottomSection = new JPanel();
		helper = new serverHelpers();
		
	}
	
	public void setScreen() {
		workingPanel.setBackground(lightGray);
		workingPanel.setLayout(new BoxLayout(workingPanel, BoxLayout.Y_AXIS));
		
		//get user info from database
		String[] userInfo = null;
		try {
			userInfo = helper.readUserInfo_T(WorkingPanel.getUser());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//create and add profile picture
		ImageIcon image = new ImageIcon(new ImageIcon("./Images/AccountDefaultImage.jpg").getImage().getScaledInstance(125, 125, Image.SCALE_DEFAULT));
		JLabel avatar = new JLabel(image);
		avatar.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(20,0,20,0), new MatteBorder(5,5,5,5, Color.black)));
		avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//create and add username
		JLabel username = new JLabel(userInfo[0]);
		username.setFont(new Font("Verdana", Font.PLAIN, 24));
		username.setBorder(new EmptyBorder(0,0,20,0));
		username.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//create and add email
		JLabel email = new JLabel(userInfo[1]);
		email.setFont(new Font("Verdana", Font.PLAIN, 18));
		email.setBorder(new EmptyBorder(0,0,20,0));
		email.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//create bottom section to hold "Match History" and "Unregister"
		JPanel buttonSection = new JPanel();
	    buttonSection.setBackground(darkGray);
	    buttonSection.setLayout(new BoxLayout(buttonSection, BoxLayout.X_AXIS));
		buttonSection.setMaximumSize(new Dimension(1005,75));
		buttonSection.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,10,0,mediumGray), new EmptyBorder(10,0,10,0)));
	    
		JPanel editButton = new JPanel();
	    JPanel historyButton = new JPanel();
	    JPanel unregisterButton = new JPanel();
	    JPanel invitesReceivedButton = new JPanel();
	    JPanel invitesSentButton = new JPanel();
	    
	    //create "Match History" button
	    JPanel historyButtonHolder = new JPanel();
	    historyButtonHolder.setBackground(darkGray);
	    historyButtonHolder.setLayout(new GridBagLayout());

	    historyButtonHolder.add(historyButton);
	    historyButton.setBackground(lightGray);
	    historyButton.setLayout(new GridBagLayout());
	    historyButton.setBorder(new MatteBorder(2,2,2,2,blue));
	   
	    JLabel historylabel = new JLabel("Match History");
	    historyButton.add(historylabel);
	    historylabel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));
	    historyButton.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			historyButton.setBackground(blue);
	  			invitesReceivedButton.setBackground(lightGray);
	  			invitesSentButton.setBackground(lightGray);
	  		    editButton.setBackground(lightGray);
	  			unregisterButton.setBackground(lightGray);
	  			createMatchHistory();
	  			
	  		}
		});
	    
	    //create "Invitations Received" button
	    JPanel invitesReceivedHolder = new JPanel();
	    invitesReceivedHolder.setBackground(darkGray);
	    invitesReceivedHolder.setLayout(new GridBagLayout());

	    invitesReceivedHolder.add(invitesReceivedButton);
	    invitesReceivedButton.setBackground(blue);
	    invitesReceivedButton.setLayout(new GridBagLayout());
	    invitesReceivedButton.setBorder(new MatteBorder(2,2,2,2,blue));
	   
	    JLabel invitesReceivedlabel = new JLabel("Invitations Received");
	    invitesReceivedButton.add(invitesReceivedlabel);
	    invitesReceivedlabel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));
	    invitesReceivedButton.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			historyButton.setBackground(lightGray);
	  			invitesReceivedButton.setBackground(blue);
	  			invitesSentButton.setBackground(lightGray);
	  		    editButton.setBackground(lightGray);
	  			unregisterButton.setBackground(lightGray);
	  			createInvitesReceived();
	  			
	  		}
		});
	    
	    //create "Invites Sent" button
	    JPanel invitesSentHolder = new JPanel();
	    invitesSentHolder.setBackground(darkGray);
	    invitesSentHolder.setLayout(new GridBagLayout());

	    invitesSentHolder.add(invitesSentButton);
	    invitesSentButton.setBackground(lightGray);
	    invitesSentButton.setLayout(new GridBagLayout());
	    invitesSentButton.setBorder(new MatteBorder(2,2,2,2,blue));
	   
	    JLabel invitesSentlabel = new JLabel("Invitations Sent");
	    invitesSentButton.add(invitesSentlabel);
	    invitesSentlabel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));
	    invitesSentButton.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			historyButton.setBackground(lightGray);
	  			invitesReceivedButton.setBackground(lightGray);
	  			invitesSentButton.setBackground(blue);
	  		    editButton.setBackground(lightGray);
	  			unregisterButton.setBackground(lightGray);
	  			createInvitesSent();
	  			
	  		}
		});
	    
	    //create "Edit Profile" button
	    JPanel editButtonHolder = new JPanel();
	    editButtonHolder.setBackground(darkGray);
	    editButtonHolder.setLayout(new GridBagLayout());
	    
	    editButtonHolder.add(editButton);
	    editButton.setBackground(lightGray);
	    editButton.setLayout(new GridBagLayout());
	    editButton.setBorder(new MatteBorder(2,2,2,2,blue));
	   
	    JLabel editlabel = new JLabel("Edit Account Information");
	    editButton.add(editlabel);
	    editlabel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));
	    editButton.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			historyButton.setBackground(lightGray);
	  			invitesReceivedButton.setBackground(lightGray);
	  			invitesSentButton.setBackground(lightGray);
	  		    editButton.setBackground(blue);
	  			unregisterButton.setBackground(lightGray);
	  			createEditProfile();
	  			
	  		}
		});
	    
		//create "Unregister" button
	    JPanel unregisterButtonHolder = new JPanel();
	    unregisterButtonHolder.setBackground(darkGray);
	    unregisterButtonHolder.setLayout(new GridBagLayout());
	    
	    unregisterButtonHolder.add(unregisterButton);
	    unregisterButton.setBackground(lightGray);
	    unregisterButton.setLayout(new GridBagLayout());
	    unregisterButton.setBorder(new MatteBorder(2,2,2,2,blue));

	    JLabel unregisterlabel = new JLabel("Unregister");
	    unregisterButton.add(unregisterlabel);
	    unregisterlabel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));
	    unregisterButton.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			historyButton.setBackground(lightGray);
	  			invitesReceivedButton.setBackground(lightGray);
	  			invitesSentButton.setBackground(lightGray);
	  		    editButton.setBackground(lightGray);
	  			unregisterButton.setBackground(blue);
	  			createUnregisterConfirm();
	  		}
		});
	    
	    //add to button area
	    buttonSection.add(invitesReceivedHolder);
	    buttonSection.add(invitesSentHolder);
	    buttonSection.add(historyButtonHolder);
	    buttonSection.add(editButtonHolder);
	    buttonSection.add(unregisterButtonHolder);
	    
	    //add to the working panel
	    workingPanel.add(avatar);
	    workingPanel.add(username);
	    workingPanel.add(email);
	    workingPanel.add(buttonSection);
	    
	    //initially load match history
	    createInvitesReceived();
	    
	}
	
	//creates panel for user to view invites received
	void createInvitesReceived() {
		
		//clear current components on bottom section
		this.bottomSection.removeAll();
		this.bottomSection.setLayout(new BorderLayout());
		
		//get info from database
		ArrayList<String[]> receivedInv = new ArrayList<String[]>();
		try {
			receivedInv = helper.readReceivedUserInvites_T(WorkingPanel.getUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//check if user has invites
		if(receivedInv.size() != 0) {
			
			//create panel to display game info
			JPanel inviteBoxKey = new JPanel();
			inviteBoxKey.setLayout(new BoxLayout(inviteBoxKey, BoxLayout.X_AXIS));
			inviteBoxKey.setPreferredSize(new Dimension(1000,75));
			inviteBoxKey.setMaximumSize(new Dimension(1000,75));
			inviteBoxKey.setMinimumSize(new Dimension(1000,75));
			inviteBoxKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,0,0, darkGray), new MatteBorder(2,2,2,0, darkGray)));
			inviteBoxKey.setBackground(lightGray);
			
			//create receiver label
			JPanel receiverKey = new JPanel();
			receiverKey.setLayout(new GridBagLayout());
			receiverKey.add(new JLabel("Sender"));
			receiverKey.setPreferredSize(new Dimension(275,75));
			receiverKey.setMaximumSize(new Dimension(275,75));
			receiverKey.setMinimumSize(new Dimension(275,75));
			receiverKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,5,0,blue), new MatteBorder(0,5,0,0, mediumGray)));
			receiverKey.setBackground(lightGray);
			
			//create empty label
			JPanel paddingKey = new JPanel();
			paddingKey.setLayout(new GridBagLayout());
			paddingKey.setPreferredSize(new Dimension(450,75));
			paddingKey.setMaximumSize(new Dimension(450,75));
			paddingKey.setMinimumSize(new Dimension(450,75));
			paddingKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,5,0,blue), new MatteBorder(0,5,0,0, mediumGray)));
			paddingKey.setBackground(lightGray);
			
			//create status label
			JPanel statusKey = new JPanel();
			statusKey.setLayout(new GridBagLayout());
			statusKey.add(new JLabel("Response"));
			statusKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,5,0,blue), new MatteBorder(0,5,0,0, mediumGray)));
			statusKey.setBackground(lightGray);
			
			//add info to gamebox
			inviteBoxKey.add(receiverKey);
			inviteBoxKey.add(paddingKey);
			inviteBoxKey.add(statusKey);
			
			//create panel to store game info
			JPanel dataPanel = new JPanel();
			dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.PAGE_AXIS));
			dataPanel.setBackground(darkGray);
			dataPanel.setBorder(new EmptyBorder(0,0,10,0));
	
			//generate games from list of match history
			for(int i = 0; i < receivedInv.size(); i++) {
				
				if(receivedInv.get(i)[3].equals("active")) {
					
					//create panel to display game info
					JPanel inviteBox = new JPanel();
					inviteBox.setLayout(new BoxLayout(inviteBox, BoxLayout.X_AXIS));
					inviteBox.setPreferredSize(new Dimension(950,75));
					inviteBox.setMaximumSize(new Dimension(950,75));
					inviteBox.setMinimumSize(new Dimension(950,75));
					inviteBox.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10,0,0,0, darkGray), new MatteBorder(2,2,2,0, darkGray)));
					inviteBox.setBackground(lightGray);
					
					//create sender label
					JPanel sender = new JPanel();
					sender.setLayout(new GridBagLayout());
					sender.add(new JLabel(receivedInv.get(i)[1]));
					sender.setPreferredSize(new Dimension(250,75));
					sender.setMaximumSize(new Dimension(250,75));
					sender.setMinimumSize(new Dimension(250,75));
					sender.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, blue)));
					sender.setBackground(lightGray);
					
					//create empty label
					JPanel padding = new JPanel();
					padding.setLayout(new GridBagLayout());
					padding.setPreferredSize(new Dimension(475,75));
					padding.setMaximumSize(new Dimension(475,75));
					padding.setMinimumSize(new Dimension(475,75));
					padding.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, mediumGray)));
					padding.setBackground(mediumGray);
					
					//create "Accept" button
				    JPanel acceptButtonHolder = new JPanel();
				    acceptButtonHolder.setBackground(mediumGray);
				    acceptButtonHolder.setLayout(new GridBagLayout());	
				    
				    JPanel acceptButton = new JPanel();
				    acceptButtonHolder.add(acceptButton);
				    acceptButton.setBackground(lightGray);
				    acceptButton.setLayout(new GridBagLayout());
				    acceptButton.setBorder(new MatteBorder(2,2,2,2,blue));
	
				    JLabel acceptlabel = new JLabel("Accept");
				    acceptButton.add(acceptlabel);
				    acceptlabel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));
				    acceptButton.addMouseListener(new MouseAdapter() {
				  		@Override
				  		public void mousePressed(final MouseEvent e) {
				  			acceptButton.setBackground(blue);
				  		}
				  		@Override
				  		public void mouseReleased(final MouseEvent e) {
				  			acceptButton.setBackground(lightGray);
				  		}
					});
				    
				    //create "Decline" button
				    JPanel declineButtonHolder = new JPanel();
				    declineButtonHolder.setBackground(mediumGray);
				    declineButtonHolder.setLayout(new GridBagLayout());	
				    
				    JPanel declineButton = new JPanel();
				    declineButtonHolder.add(declineButton);
				    declineButton.setBackground(lightGray);
				    declineButton.setLayout(new GridBagLayout());
				    declineButton.setBorder(new MatteBorder(2,2,2,2,blue));
	
				    JLabel declinelabel = new JLabel("Decline");
				    declineButton.add(declinelabel);
				    declinelabel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));
				    declineButton.addMouseListener(new MouseAdapter() {
				  		@Override
				  		public void mousePressed(final MouseEvent e) {
				  			declineButton.setBackground(blue);
				  		}
				  		@Override
				  		public void mouseReleased(final MouseEvent e) {
				  			declineButton.setBackground(lightGray);
				  		}
					});
				    
					//add info to gamebox
					inviteBox.add(sender);
					inviteBox.add(padding);
					inviteBox.add(acceptButtonHolder);
					inviteBox.add(declineButtonHolder);

					
					//add gamebox to the data panel
					dataPanel.add(inviteBox);
				}
			}
			
			// add scrollbar and change the style of it
			JScrollPane scrollPane = new JScrollPane(dataPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
			this.bottomSection.add(inviteBoxKey, BorderLayout.PAGE_START);
			this.bottomSection.add(scrollPane, BorderLayout.CENTER);
			this.bottomSection.setBackground(lightGray);
			
		} else { //user has no invites received
			
			//create message
			JPanel outer = new JPanel();
			outer.setBackground(darkGray);
			outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));
			outer.setBorder(new EmptyBorder(125,350,0,350));
			JPanel labelHolder = new JPanel();
			outer.add(labelHolder);
			labelHolder.setBackground(lightGray);
			labelHolder.setLayout(new GridBagLayout());
			labelHolder.setMaximumSize(new Dimension(1000,75));
			JLabel label = new JLabel("You haven't received any invitations.");
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
	
	//creates panel for user to view their invites sent
	void createInvitesSent() {
		
		//clear current components on bottom section
		this.bottomSection.removeAll();
		this.bottomSection.setLayout(new BorderLayout());
		
		//get info from database
		ArrayList<String[]> receivedInv = new ArrayList<String[]>();
		try {
			receivedInv = helper.readSentUserInvites_T(WorkingPanel.getUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//check if user has invites
		if(receivedInv.size() != 0) {
			
			//create panel to display game info
			JPanel inviteBoxKey = new JPanel();
			inviteBoxKey.setLayout(new BoxLayout(inviteBoxKey, BoxLayout.X_AXIS));
			inviteBoxKey.setPreferredSize(new Dimension(1000,75));
			inviteBoxKey.setMaximumSize(new Dimension(1000,75));
			inviteBoxKey.setMinimumSize(new Dimension(1000,75));
			inviteBoxKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,0,0, darkGray), new MatteBorder(2,2,2,0, darkGray)));
			inviteBoxKey.setBackground(lightGray);
			
			//create receiver label
			JPanel receiverKey = new JPanel();
			receiverKey.setLayout(new GridBagLayout());
			receiverKey.add(new JLabel("Receiver"));
			receiverKey.setPreferredSize(new Dimension(275,75));
			receiverKey.setMaximumSize(new Dimension(275,75));
			receiverKey.setMinimumSize(new Dimension(275,75));
			receiverKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,5,0,blue), new MatteBorder(0,5,0,0, mediumGray)));
			receiverKey.setBackground(lightGray);
			
			//create empty label
			JPanel paddingKey = new JPanel();
			paddingKey.setLayout(new GridBagLayout());
			paddingKey.setPreferredSize(new Dimension(550,75));
			paddingKey.setMaximumSize(new Dimension(550,75));
			paddingKey.setMinimumSize(new Dimension(550,75));
			paddingKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,5,0,blue), new MatteBorder(0,5,0,0, mediumGray)));
			paddingKey.setBackground(lightGray);
			
			//create status label
			JPanel statusKey = new JPanel();
			statusKey.setLayout(new GridBagLayout());
			statusKey.add(new JLabel("Status"));
			statusKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,5,0,blue), new MatteBorder(0,5,0,0, mediumGray)));
			statusKey.setBackground(lightGray);
			
			//add info to gamebox
			inviteBoxKey.add(receiverKey);
			inviteBoxKey.add(paddingKey);
			inviteBoxKey.add(statusKey);
			
			
			//create panel to store game info
			JPanel dataPanel = new JPanel();
			dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.PAGE_AXIS));
			dataPanel.setBackground(darkGray);
			dataPanel.setBorder(new EmptyBorder(0,0,10,0));
	
			//generate games from list of match history
			for(int i = 0; i < receivedInv.size(); i++) {
			
				//create panel to display game info
				JPanel inviteBox = new JPanel();
				inviteBox.setLayout(new BoxLayout(inviteBox, BoxLayout.X_AXIS));
				inviteBox.setPreferredSize(new Dimension(950,75));
				inviteBox.setMaximumSize(new Dimension(950,75));
				inviteBox.setMinimumSize(new Dimension(950,75));
				inviteBox.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10,0,0,0, darkGray), new MatteBorder(2,2,2,0, darkGray)));
				inviteBox.setBackground(lightGray);
				
				//create receiver label
				JPanel receiver = new JPanel();
				receiver.setLayout(new GridBagLayout());
				receiver.add(new JLabel(receivedInv.get(i)[2]));
				receiver.setPreferredSize(new Dimension(250,75));
				receiver.setMaximumSize(new Dimension(250,75));
				receiver.setMinimumSize(new Dimension(250,75));
				receiver.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, blue)));
				receiver.setBackground(lightGray);
				
				//create empty label
				JPanel padding = new JPanel();
				padding.setLayout(new GridBagLayout());
				padding.setPreferredSize(new Dimension(550,75));
				padding.setMaximumSize(new Dimension(550,75));
				padding.setMinimumSize(new Dimension(550,75));
				padding.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, mediumGray)));
				padding.setBackground(mediumGray);
				
				//create status label
				JPanel status = new JPanel();
				status.setLayout(new GridBagLayout());
				status.add(new JLabel(receivedInv.get(i)[3]));
				status.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, mediumGray)));
				status.setBackground(lightGray);
				
				//add info to gamebox
				inviteBox.add(receiver);
				inviteBox.add(padding);
				inviteBox.add(status);
				
				//add gamebox to the data panel
				dataPanel.add(inviteBox);
				
			}
			
			// add scrollbar and change the style of it
			JScrollPane scrollPane = new JScrollPane(dataPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
			
			this.bottomSection.add(inviteBoxKey, BorderLayout.PAGE_START);
			this.bottomSection.add(scrollPane, BorderLayout.CENTER);
			this.bottomSection.setBackground(lightGray);
			
		} else { //user has no invites received
			
			//create message
			JPanel outer = new JPanel();
			outer.setBackground(darkGray);
			outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));
			outer.setBorder(new EmptyBorder(125,350,0,350));
			JPanel labelHolder = new JPanel();
			outer.add(labelHolder);
			labelHolder.setBackground(lightGray);
			labelHolder.setLayout(new GridBagLayout());
			labelHolder.setMaximumSize(new Dimension(1000,75));
			JLabel label = new JLabel("You haven't sent any invitations.");
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
	
	//creates panel for user to view their match history
	void createMatchHistory() {
		
		//clear current components on bottom section
		this.bottomSection.removeAll();
		this.bottomSection.setLayout(new BorderLayout());
		
		//get info from database
		String[][] history = new String[0][0];
		try {
			history = helper.readMatchHistory_T(WorkingPanel.getUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//check if user has a match history
		if(history != null) {
			
			//describe info contents
			JPanel key = new JPanel();
			key.setLayout(new BoxLayout(key, BoxLayout.X_AXIS));
			key.setPreferredSize(new Dimension(1000,75));
			key.setMaximumSize(new Dimension(1000,75));
			key.setMinimumSize(new Dimension(1000,75));
			key.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,0,0, darkGray), new MatteBorder(2,2,2,0, darkGray)));
			key.setBackground(lightGray);
			
			//create game number label
			JPanel gameNumberKey = new JPanel();
			gameNumberKey.setLayout(new GridBagLayout());
			gameNumberKey.add(new JLabel("#"));
			gameNumberKey.setMaximumSize(new Dimension(75,75));
			gameNumberKey.setMinimumSize(new Dimension(75,75));
			gameNumberKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,5,0,blue), new MatteBorder(0,5,0,0, mediumGray)));
			gameNumberKey.setBackground(lightGray);
			
			//create username label
			JPanel usernameKey = new JPanel();
			usernameKey.setLayout(new GridBagLayout());
			usernameKey.add(new JLabel("Username"));
			usernameKey.setMaximumSize(new Dimension(200,100));
			usernameKey.setMinimumSize(new Dimension(200,100));
			usernameKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,5,0,blue), new MatteBorder(0,5,0,0, mediumGray)));
			usernameKey.setBackground(lightGray);
			
			//create opponent label
			JPanel opponentKey = new JPanel();
			opponentKey.setLayout(new GridBagLayout());
			opponentKey.add(new JLabel("Opponent"));
			opponentKey.setMaximumSize(new Dimension(200,100));
			opponentKey.setMinimumSize(new Dimension(200,100));
			opponentKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,5,0,blue), new MatteBorder(0,5,0,0, mediumGray)));
			opponentKey.setBackground(lightGray);
			
			//create start time label
			JPanel startTimeKey = new JPanel();
			startTimeKey.setLayout(new GridBagLayout());
			startTimeKey.add(new JLabel("Start Time"));
			startTimeKey.setMaximumSize(new Dimension(200,100));
			startTimeKey.setMinimumSize(new Dimension(200,100));
			startTimeKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,5,0,blue), new MatteBorder(0,5,0,0, mediumGray)));
			startTimeKey.setBackground(lightGray);
			
			//create end time label
			JPanel endTimeKey = new JPanel();
			endTimeKey.setLayout(new GridBagLayout());
			endTimeKey.add(new JLabel("End Time"));
			endTimeKey.setMaximumSize(new Dimension(200,100));
			endTimeKey.setMinimumSize(new Dimension(200,100));
			endTimeKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,5,0,blue), new MatteBorder(0,5,0,0, mediumGray)));
			endTimeKey.setBackground(lightGray);
			
			//create status label
			JPanel statusKey = new JPanel();
			statusKey.setLayout(new GridBagLayout());
			statusKey.add(new JLabel("Outcome"));
			statusKey.setMaximumSize(new Dimension(125,100));
			statusKey.setMinimumSize(new Dimension(125,100));
			statusKey.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0,0,5,0,blue), new MatteBorder(0,5,0,0, mediumGray)));
			statusKey.setBackground(lightGray);
			
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
			dataPanel.setBackground(darkGray);
			dataPanel.setBorder(new EmptyBorder(0,0,10,0));
	
			//generate games from list of match history
			for(int i = 0; i < history.length; i++) {
				//create panel to display game info
				JPanel gameBox = new JPanel();
				gameBox.setLayout(new BoxLayout(gameBox, BoxLayout.X_AXIS));
				gameBox.setPreferredSize(new Dimension(950,50));
				gameBox.setMaximumSize(new Dimension(950,50));
				gameBox.setMinimumSize(new Dimension(950,50));
				gameBox.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(10,0,0,0, darkGray), new MatteBorder(2,2,2,0, darkGray)));
				gameBox.setBackground(lightGray);
				
				//create game number label
				JPanel gameNumber = new JPanel();
				gameNumber.setLayout(new GridBagLayout());
				gameNumber.add(new JLabel(history[i][0]));
				gameNumber.setMaximumSize(new Dimension(50,50));
				gameNumber.setMinimumSize(new Dimension(50,50));
				gameNumber.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, mediumGray)));
				gameNumber.setBackground(lightGray);
				
				//create username label
				JPanel username = new JPanel();
				username.setLayout(new GridBagLayout());
				username.add(new JLabel(history[i][1]));
				username.setMaximumSize(new Dimension(200,50));
				username.setMinimumSize(new Dimension(200,50));
				username.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, mediumGray)));
				username.setBackground(lightGray);
				
				//create opponent label
				JPanel opponent = new JPanel();
				opponent.setLayout(new GridBagLayout());
				opponent.add(new JLabel(history[i][2]));
				opponent.setMaximumSize(new Dimension(200,50));
				opponent.setMinimumSize(new Dimension(200,50));
				opponent.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, mediumGray)));
				opponent.setBackground(lightGray);
				
				//create start time label
				JPanel startTime = new JPanel();
				startTime.setLayout(new GridBagLayout());
				startTime.add(new JLabel(history[i][3]));
				startTime.setMaximumSize(new Dimension(200,50));
				startTime.setMinimumSize(new Dimension(200,50));
				startTime.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, mediumGray)));
				startTime.setBackground(lightGray);
				
				//create end time label
				JPanel endTime = new JPanel();
				endTime.setLayout(new GridBagLayout());
				endTime.add(new JLabel(history[i][4]));
				endTime.setMaximumSize(new Dimension(200,50));
				endTime.setMinimumSize(new Dimension(200,50));
				endTime.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, mediumGray)));
				endTime.setBackground(lightGray);
				
				//create status label
				JPanel status = new JPanel();
				status.setLayout(new GridBagLayout());
				status.add(new JLabel(history[i][5]));
				status.setMaximumSize(new Dimension(100,50));
				status.setMinimumSize(new Dimension(100,50));
				status.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0,0,0,0), new MatteBorder(0,5,0,0, mediumGray)));
				status.setBackground(lightGray);
				
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
	
			this.bottomSection.add(key, BorderLayout.PAGE_START);
			this.bottomSection.add(scrollPane, BorderLayout.CENTER);
			this.bottomSection.setBackground(lightGray);
			
		} else { //user has no match history
			
			//create message
			JPanel outer = new JPanel();
			outer.setBackground(darkGray);
			outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));
			outer.setBorder(new EmptyBorder(125,150,0,150));
			JPanel labelHolder = new JPanel();
			outer.add(labelHolder);
			labelHolder.setBackground(lightGray);
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
	
	//creates panel for user to edit their credentials
	void createEditProfile() {
		
		//clear current components on bottom section
		this.bottomSection.removeAll();
		this.bottomSection.setLayout(new GridLayout(1,2));
		
//		//create left half
//		JPanel leftSection = new JPanel();
//		leftSection.setBackground(lightGray);
//		leftSection.setBorder(new MatteBorder(1,1,1,1,darkGray));
//		leftSection.setLayout(new BoxLayout(leftSection, BoxLayout.Y_AXIS));
		
		//create right half
		JPanel rightSection = new JPanel();
		rightSection.setBackground(darkGray);
		rightSection.setBorder(new MatteBorder(0,1,1,1,darkGray));
		rightSection.setLayout(new BoxLayout(rightSection, BoxLayout.Y_AXIS));
		
		//create text fields
		JTextField newUsername = Helpers.newTextField("Username", "New Username");
		JPasswordField oldPassword = Helpers.newPasswordField(16, "Old Password");
		JPasswordField newPassword = Helpers.newPasswordField(16, "New Password");
		JPasswordField confirmNewPassword = Helpers.newPasswordField(16, "Confirm New Password");

//		//create update username button
//		JPanel newUsernameButtonHolder = new JPanel();
//		newUsernameButtonHolder.setLayout(new BorderLayout());
//		newUsernameButtonHolder.setBackground(lightGray);
//		newUsernameButtonHolder.setMinimumSize(new Dimension(400, 50));
//		newUsernameButtonHolder.setMaximumSize(new Dimension(400, 50));
//		JPanel newUsernameButton = new JPanel();
//		newUsernameButton.setBackground(lightGray);
//		newUsernameButton.setLayout(new GridBagLayout());
//		newUsernameButton.setBorder(BorderFactory.createMatteBorder(2,2,2,2,blue));
//		newUsernameButton.setMinimumSize(new Dimension(150, 50));
//		newUsernameButton.setMaximumSize(new Dimension(150, 50));
//	    newUsernameButton.addMouseListener(new MouseAdapter() {
//	  		@Override
//	  		public void mousePressed(final MouseEvent e) {
//	  			newUsernameButton.setBackground(highlightGray);
//	  		}
//	  		@Override
//	  		public void mouseReleased(final MouseEvent e) {
//	  			newUsernameButton.setBackground(lightGray);
//	  			String name = newUsername.getText();
//				changeUsername(name);
//	  		}
//		});
//	    JLabel usernamelabel = new JLabel("Update Username");
//	    usernamelabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
//	    newUsernameButtonHolder.add(newUsernameButton, BorderLayout.LINE_START);
//		newUsernameButton.add(usernamelabel);
		
		//create update password button
		JPanel newPasswordButtonHolder = new JPanel();
		newPasswordButtonHolder.setLayout(new BorderLayout());
		newPasswordButtonHolder.setBackground(lightGray);
		newPasswordButtonHolder.setMinimumSize(new Dimension(400, 50));
		newPasswordButtonHolder.setMaximumSize(new Dimension(400, 50));
		JPanel newPasswordButton = new JPanel();
		newPasswordButton.setBackground(lightGray);
		newPasswordButton.setLayout(new GridBagLayout());
		newPasswordButton.setBorder(BorderFactory.createMatteBorder(2,2,2,2,blue));
		newPasswordButton.setMinimumSize(new Dimension(150, 50));
		newPasswordButton.setMaximumSize(new Dimension(150, 50));
		newPasswordButton.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {
	  			newPasswordButton.setBackground(highlightGray);
	  		}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			if(!isDefaultInput(oldPassword, newPassword, confirmNewPassword)) {
	  				newPasswordButton.setBackground(lightGray);
		  			String oldPass = new String(oldPassword.getPassword());
		  			String newPass = new String(newPassword.getPassword());
		  			String newPassCon = new String(confirmNewPassword.getPassword());
					changePassword(oldPass, newPass, newPassCon);
	  			} else {
	  				setError(1);
	  			}
	  			
	  		}
		});
	    JLabel passwordlabel = new JLabel("Update Password");
	    passwordlabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	    newPasswordButtonHolder.add(newPasswordButton, BorderLayout.CENTER);
	    newPasswordButton.add(passwordlabel);
		
//	    //add to left side
//		leftSection.add(Helpers.spacer(10, 70));
//	    leftSection.add(newUsername);
//	    leftSection.add(Helpers.spacer(10, 30));
//	    leftSection.add(newUsernameButtonHolder);
	    
	    //add to right side
	    rightSection.add(Helpers.spacer(10, 30));
	    rightSection.add(oldPassword);
	    rightSection.add(Helpers.spacer(10, 30));
	    rightSection.add(newPassword);
		rightSection.add(Helpers.spacer(10, 30));
		rightSection.add(confirmNewPassword);
		rightSection.add(Helpers.spacer(10, 30));
		rightSection.add(newPasswordButtonHolder);
		rightSection.add(errorCards);	
		
		//add left and right sections to the bottom
//		this.bottomSection.add(leftSection);
		this.bottomSection.add(rightSection);
	
		//update panel
		this.bottomSection.revalidate();
		this.bottomSection.repaint();
		
		//add it back to the working panel
		workingPanel.add(this.bottomSection);
	}
	
	//creates panel for user to confirm unregister
	void createUnregisterConfirm() {
		
		//clear current components on bottom section
		this.bottomSection.removeAll();
		this.bottomSection.setLayout(new BoxLayout(this.bottomSection, BoxLayout.Y_AXIS));
		
		this.bottomSection.setBackground(Color.red);
		
		//create "Unregister" button
	    JPanel unregisterButtonHolder = new JPanel();
	    unregisterButtonHolder.setBackground(darkGray);
	    unregisterButtonHolder.setLayout(new GridBagLayout());
	    unregisterButtonHolder.setBorder(new EmptyBorder(0,0,75,0));
	    
	    JPanel unregisterButton = new JPanel();
	    unregisterButtonHolder.add(unregisterButton);
	    unregisterButton.setBackground(lightGray);
	    unregisterButton.setLayout(new GridBagLayout());
	    unregisterButton.setBorder(new MatteBorder(2,2,2,2,blue));

	    JLabel unregisterlabel = new JLabel("Yes, I want to unregister my account.");
	    unregisterButton.add(unregisterlabel);
	    unregisterlabel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));
	    unregisterButton.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {
	  			unregisterButton.setBackground(highlightGray);
	  		}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
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
	    
	    //create message
		JPanel outer = new JPanel();
		outer.setBackground(darkGray);
		outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));
		outer.setBorder(new EmptyBorder(75,100,0,100));
		JPanel labelHolder = new JPanel();
		outer.add(labelHolder);
		labelHolder.setBackground(lightGray);
		labelHolder.setLayout(new GridBagLayout());
		labelHolder.setPreferredSize(new Dimension(1000,75));
		labelHolder.setMaximumSize(new Dimension(1000,75));
		JLabel label = new JLabel("You will lose your current games and match history if you unregister. Are you sure you want to continue?");
		label.setFont(new Font("Verdana", Font.PLAIN, 14));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelHolder.add(label);
		
		//add message
		this.bottomSection.add(outer);
	    this.bottomSection.add(unregisterButtonHolder);
	    
		//update panel
		this.bottomSection.revalidate();
		this.bottomSection.repaint();
		
		//add it back to the working panel
		workingPanel.add(this.bottomSection);
	}
	
//	//try to change username
//	private void changeUsername(String username) {
//		System.out.println("Testing user: " + username);
//	}
	
	//try to change password
	private void changePassword(String oldPass, String newPass, String newPassCon) {
		int err1 = serverHelpers.tryLogin(WorkingPanel.getUser(), oldPass);
		if(err1 == 0) {
			if(newPass.equals(newPassCon)) {
				try {
					int err2 = this.helper.insertUserLogin_T(WorkingPanel.getUser(), "Password", newPass);
					if(err2 == 4) { //invalid password
						setError(4);
					} else { //success
//						this.errorCards.removeAll();
//						setErrorCards();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				setError(3); //passwords dont match

			}
		} else {
			setError(err1); //incorrect current password
		}
	}
	
	private boolean isDefaultInput(JTextField passwordOldField, JTextField passwordField, JTextField passwordConField) {
		if (passwordOldField.getText().contentEquals("Old Password") || passwordField.getText().contentEquals("New Password")
				|| passwordConField.getText().contentEquals("Confirm New Password")) { 
			return true;
		}
		return false;
	}
	
	@Override
	void setErrorCards() {
		errorCards = Panel.errorCards(new Dimension(400,400));
		String errStr = "";
		int err = 0;
		boolean cont = true;
		while(cont) {
			
	    	switch (err) {
	    		case 0: break;
	    		case 1: errStr = "<html>You must fill out the entire form.</html>"; break;
	    		case 2: errStr = "<html>Current password is incorrect.</html>"; break;
	    		case 3: errStr = "<html>New passwords do not match.</html>"; break;
	    		case 4: errStr = "<html>Password contains illegal characters.</html>"; break;
	    		default: cont = false; break;
	    	}
    	
	    	if (cont) {
	    		this.errorCards.add(Label.errorLabel(errStr, Color.red), String.valueOf(err));
	    	}
    		
	    	err++;
		
		}

	}
}
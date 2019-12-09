package Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import Server.serverHelpers;


public class NewGameScreen extends Screen  {
	
	public static final Color darkGray = new Color(50,50,50);
	public static final Color mediumGray = new Color(70,70,70);
	public static final Color lightGray = new Color(90,90,90);
	public static final Color highlightGray = new Color(120,120,120);
	public static final Color blue = new Color(79,175,255);
	
	public NewGameScreen() {
		error = 0;
		name = "New Game";
		
	}

	@Override
	public void setScreen() {
		serverHelpers server = new serverHelpers();
		JDialog newGame = new JDialog(this.WorkingPanel.frame.getThisFrame(), "Invite Players");
		newGame.setName("New Game");
		ArrayList<String> users = null;
		newGame.setSize(300,400);
		
		JPanel backpanel = new JPanel();
		backpanel.setLayout(new BorderLayout());		
				
		try {
			users = server.getAllUsers(WorkingPanel.getUser());
		} catch (Exception e) {
			//e.printStackTrace();
		}
				
		ArrayList<String> selectedUsers = new ArrayList<String>();

		JPanel userList = new JPanel();
		userList.setLayout(new BoxLayout(userList, BoxLayout.Y_AXIS));
		userList.setBackground(darkGray);

		for (int i = 0; i < users.size(); i++){
		    
			JPanel user = new JPanel();
			user.setLayout(new GridBagLayout());
			user.setMinimumSize(new Dimension(300,40));
			user.setMaximumSize(new Dimension(300,40));
			user.setPreferredSize(new Dimension(300,40));
			
			String username = users.get(i);
			user.add(new JLabel(username));
			user.setName(username);
			user.setBackground(lightGray);
			user.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(2,2,0,2,darkGray), new EmptyBorder(5,0,5,0)));
			user.addMouseListener(new MouseAdapter() {
		  		@Override
		  		public void mousePressed(final MouseEvent e) {
		  		}
		  		@Override
		  		public void mouseReleased(final MouseEvent e) {
		  			
		  			if(!selectedUsers.contains(e.getComponent().getName())) {
		  				selectedUsers.add(e.getComponent().getName());
						user.setBackground(blue);

		  			} else {
		  				selectedUsers.remove(e.getComponent().getName());
						user.setBackground(lightGray);
		  			}
		  					  			
					workingPanel.repaint();
					workingPanel.validate();
		  		}
			});
            GridBagConstraints gb = new GridBagConstraints();
            gb.gridwidth = GridBagConstraints.REMAINDER;
            gb.weightx = 1;
            gb.fill = GridBagConstraints.HORIZONTAL;
            userList.add(user, gb, 0);
                       
            WorkingPanel.updateWorking();
		}
		
		// add scrollbar and change the style of it
		JScrollPane scrollPane = new JScrollPane(userList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
	
		backpanel.add(scrollPane, BorderLayout.CENTER);
		
		//create "Send Invites" button
	    JPanel inviteButtonHolder = new JPanel();
	    inviteButtonHolder.setBackground(mediumGray);
	    inviteButtonHolder.setLayout(new BorderLayout());
	    inviteButtonHolder.setBorder(new MatteBorder(20,20,20,20, darkGray));
	    
	    JPanel inviteButton = new JPanel();
	    inviteButtonHolder.add(inviteButton);
	    inviteButton.setBackground(lightGray);
	    inviteButton.setLayout(new GridBagLayout());
	    inviteButton.setBorder(new MatteBorder(2,2,2,2,blue));

	    JLabel invitelabel = new JLabel("Send Invites");
	    inviteButton.add(invitelabel);
	    invitelabel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));
	    inviteButton.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {
	  			inviteButton.setBackground(blue);
	  		}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			inviteButton.setBackground(lightGray);
	  			if(selectedUsers.size() != 0) {
	  				for(int i = 0; i < selectedUsers.size(); i++) {
	  					serverHelpers createInvites = new serverHelpers();
	  					try {
							createInvites.createUserInvites_T(WorkingPanel.getUser(), selectedUsers.get(i), "active");
							newGame.dispose();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
	  				}
	  				
		  			System.out.println(selectedUsers);
	  			}
	  		}
		});
	    
		backpanel.add(inviteButtonHolder, BorderLayout.PAGE_END);
	
		newGame.add(backpanel);
		newGame.setVisible(true);
		newGame.setLocationRelativeTo(this.WorkingPanel.frame.getThisFrame());
	}

	@Override
	void setErrorCards() {
		
	}
}
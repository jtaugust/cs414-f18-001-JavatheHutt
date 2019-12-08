package Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

import App.Application;
import BoardLogic.CongoBoard;
import GUI.Panel;
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
		JDialog newGame = new JDialog(this.WorkingPanel.frame.getThisFrame(), "New Game");
		newGame.setName("New Game");
		ArrayList<String> users = null;
		newGame.setSize(300,300);
		
		JPanel backpanel = new JPanel();
		backpanel.setLayout(new GridBagLayout());		
				
		try {
			users = server.getAllUsers(WorkingPanel.getUser());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
				
		String[] selected = new String[users.size()];

		JPanel userList = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		userList.add(new JPanel(), gbc);

		//newGame.getContentPane().add(new JScrollPane(userList));

		for (int i = 0; i < users.size(); i++){
			JPanel user = new JPanel();
			String username = users.get(i);
			user.add(new JLabel(username));
			user.setName(username);
			user.setBackground(Color.RED);
			user.addMouseListener(new MouseAdapter() {
		  		@Override
		  		public void mousePressed(final MouseEvent e) {
		  		}
		  		@Override
		  		public void mouseReleased(final MouseEvent e) {
		  			//change board based on gameBox pressed
					if(user.getBackground() == Color.RED){
						user.setBackground(Color.GREEN);
					}else{
						user.setBackground(Color.RED);
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
		JScrollPane pane = new JScrollPane(userList);
	
		JPanel t1 = new JPanel();
		JPanel t2 = new JPanel();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.ipady = 220;
		t1.setBackground(Color.MAGENTA);
		backpanel.add(pane, gbc);
		
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.ipady = 20;
		gbc.weightx = 1;
		gbc.weighty = 2;
		t2.setBackground(Color.GREEN);
		
		JPanel inviteButton = new JPanel();
		inviteButton.add(new JLabel("Send Invites"));
		
		inviteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
            	inviteButton.setBackground(new Color(110,190,255));
            }
            @Override
            public void mouseReleased(final MouseEvent e) {
            	
            }
        });
		backpanel.add(inviteButton, gbc);
	
		newGame.add(backpanel);
		newGame.setVisible(true);
		newGame.setLocationRelativeTo(this.WorkingPanel.frame.getThisFrame());
	}

	@Override
	void setErrorCards() {
		// TODO Auto-generated method stub
		
	}
}
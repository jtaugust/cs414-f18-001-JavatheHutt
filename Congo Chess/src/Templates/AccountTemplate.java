//package Templates;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//import App.Application;
//import GUI.Frame;
//import GUI.Helpers;
//import GUI.Panel;
//
//public class AccountTemplate {
//	public static void generateAccount(JFrame frame) {
//		// this template is the exact same as the main, except it must also have the
//		// sidebar
//		MainTemplate.generateMain(frame, false);
//
//		// get the main template working panel
//		JPanel background = Panel.getWorkingPanelBackground();
//		
//		//get the current working panel;
//		JPanel workingPanel = Panel.getWorkingPanel();
//		
//		//add sidebar to background
//		background.add(generateSidebar(), BorderLayout.LINE_START);
//		
//		// set the panel that a screen should load into
//		Panel.setWorkingPanel(workingPanel, true);
//		
//		//add workingPanel to background
//		background.add(workingPanel, BorderLayout.CENTER);
//
//		// set the template variable
//		Frame.setTemplate(3);
//		
//		Frame.finalize(frame);
//	}
//
//	private static JPanel generateSidebar() {
//		// create the sidepanel
//		JPanel sidebar = new JPanel();
//
//		// set the sidePanel layout to a 3 row by 1 column grid
//		sidebar.setLayout(new GridLayout(2, 1));
//
//		// create and add the 2 buttons
//		JButton history = Helpers.button("Match History");
//		history.addActionListener(
//					new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						Application.changeScreen("Match History");
//					}
//				}
//		);
//		sidebar.add(history);
//		
//		//unregister button
//		JButton unreg = Helpers.button("UnRegister");
//		unreg.addActionListener(
//					new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						Application.changeScreen("UnRegister");
//					}
//				}
//		);
//		sidebar.add(unreg);
//
//		return sidebar;
//	}
//}

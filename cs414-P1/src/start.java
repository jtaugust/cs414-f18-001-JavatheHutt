import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

import Screens.*;

import javax.imageio.*;

public class start extends JFrame{
	public static void main(String[] args) {
		//initialize bootup
		Login_Screen.Bootup();
	}
	/* to be removed soon
	 
	private static void getLoginFrame() {
		int width = 600;
		int height = 800;
		JFrame initialFrame = new JFrame();
		initialFrame.setLayout(new BorderLayout());
		//add background to frame (change this to create a background panel, where the components are added to the background panel)
		setBackground(initialFrame, width, height);
		
		//initializeFrameComponents(initialFrame.getContentPane());
		//setting frame size
		initialFrame.setMinimumSize(new Dimension(width, height));
		//set the start position (null is center)
		initialFrame.setLocationRelativeTo(null);
		//set default close action
		initialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set a title
		initialFrame.setTitle("XGames online: Congo Chess");
		//disable resize
		initialFrame.setResizable(false);
		//make frame visible
		initialFrame.setVisible(true);
	}
	
	private static void initializeFrameComponents(Container pane) {
		//set logo to top of pane
		pane.add(getLogo(), BorderLayout.PAGE_START);
		//set login form to center of pane to bottom

		pane.add(getLoginForm(), BorderLayout.CENTER);
	}
	
	private static Component getLogo() {
		JPanel logo = new JPanel();
		//make logo background invisible
		logo.setOpaque(false);
		logo.add(getImageLabel("./Images/logo.png"));
		return logo;
	}
	
	private static Component getLoginForm() {
		JPanel form = new JPanel();
		//form.setLayout(new BoxLayout(form, BoxLayout.PAGE_AXIS));
		JButton button = new JButton("This is a button");
		form.add(button);
		return form;
	}
	
	private static JLabel getImageLabel(String location) {
		BufferedImage image = getImage(location);
		JLabel label = new JLabel(new ImageIcon(image));
		return label;
	}

	private static BufferedImage getImage(String location){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(location));
		}catch (IOException e) {
			//do nothing, should never reach here
		}
		return image;
	}
	
	private static void setBackground(JFrame frame, int width, int height) {
		frame.setContentPane(new JPanel() {
			BufferedImage image = getImage("./Images/Background.jpg");
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, 600, 800, this);
			}
		});
	}
	
	
	/*   set image size
	private static JLabel getImageLabel(String location, int width, int height) {
		BufferedImage image = getImage(location, width, height);
		JLabel label = new JLabel(new ImageIcon(image));
		return label;
	}
	
	private static BufferedImage getImage(String location, int width, int height) {
		//store the image
		BufferedImage image = getImage(location);
		//alter the image
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
		Graphics2D g = resizedImage.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(image, 0, 0, width, height,null);
		g.dispose();
		return resizedImage;
	}
	*/
}



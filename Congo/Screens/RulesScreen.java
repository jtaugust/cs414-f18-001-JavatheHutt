package Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

public class RulesScreen extends Screen {

	public static final Color darkGray = new Color(50, 50, 50);
	public static final Color mediumGray = new Color(70, 70, 70);
	public static final Color lightGray = new Color(90, 90, 90);
	public static final Color highlightGray = new Color(120, 120, 120);
	public static final Color blue = new Color(79, 175, 255);
	JPanel rulesPanel;

	public RulesScreen() {
		this.error = 0;
		this.name = "Rules";
		setErrorCards();

		rulesPanel = new JPanel();
	}

	@Override
	public void setScreen() {

		workingPanel.setBackground(darkGray);
		workingPanel.setLayout(new BorderLayout());

		// Bottom Button layout
		JPanel buttonRow = new JPanel();
		buttonRow.setLayout(new FlowLayout());
		buttonRow.setBackground(darkGray);
		buttonRow.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(5,0,0,0,blue), new MatteBorder(20,0,20,0,darkGray)));
		
		// Piece Buttons
		JPanel generalButton = new JPanel();
		JPanel drowningButton = new JPanel();
		JPanel pawnButton = new JPanel();
		JPanel superPawnButton = new JPanel();
		JPanel lionButton = new JPanel();
		JPanel crocodileButton = new JPanel();
		JPanel zebraButton = new JPanel();
		JPanel giraffeButton = new JPanel();
		JPanel elephantButton = new JPanel();
		JPanel monkeyButton = new JPanel();

		// General Rules Button
		JPanel generalButtonHolder = new JPanel();
		generalButtonHolder.setBackground(darkGray);
		generalButtonHolder.setLayout(new GridBagLayout());

		generalButtonHolder.add(generalButton);
		generalButton.setBackground(blue);
		generalButton.setLayout(new GridBagLayout());
		generalButton.setBorder(new MatteBorder(2, 2, 2, 2, blue));

		JLabel generalLabel = new JLabel("General");
		generalButton.add(generalLabel);
		generalLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		generalButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
			}

			@Override
			public void mouseReleased(final MouseEvent e) {
				generalButton.setBackground(blue);
				drowningButton.setBackground(lightGray);
				pawnButton.setBackground(lightGray);
				superPawnButton.setBackground(lightGray);
				lionButton.setBackground(lightGray);
				crocodileButton.setBackground(lightGray);
				zebraButton.setBackground(lightGray);
				giraffeButton.setBackground(lightGray);
				elephantButton.setBackground(lightGray);
				monkeyButton.setBackground(lightGray);
				createPieceInfo("General Game Rules");
			}
		});
		buttonRow.add(generalButtonHolder);

		// Drowning Button
		JPanel drownButtonHolder = new JPanel();
		drownButtonHolder.setBackground(darkGray);
		drownButtonHolder.setLayout(new GridBagLayout());

		drownButtonHolder.add(drowningButton);
		drowningButton.setBackground(lightGray);
		drowningButton.setLayout(new GridBagLayout());
		drowningButton.setBorder(new MatteBorder(2, 2, 2, 2, blue));

		JLabel drownLabel = new JLabel("Drowning");
		drowningButton.add(drownLabel);
		drownLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		drowningButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
			}

			@Override
			public void mouseReleased(final MouseEvent e) {
				generalButton.setBackground(lightGray);
				drowningButton.setBackground(blue);
				pawnButton.setBackground(lightGray);
				superPawnButton.setBackground(lightGray);
				lionButton.setBackground(lightGray);
				crocodileButton.setBackground(lightGray);
				zebraButton.setBackground(lightGray);
				giraffeButton.setBackground(lightGray);
				elephantButton.setBackground(lightGray);
				monkeyButton.setBackground(lightGray);
				createPieceInfo("Drowning");
			}
		});
		buttonRow.add(drownButtonHolder);

		// Pawn Button
		JPanel pawnButtonHolder = new JPanel();
		pawnButtonHolder.setBackground(darkGray);
		pawnButtonHolder.setLayout(new GridBagLayout());

		pawnButtonHolder.add(pawnButton);
		pawnButton.setBackground(lightGray);
		pawnButton.setLayout(new GridBagLayout());
		pawnButton.setBorder(new MatteBorder(2, 2, 2, 2, blue));

		JLabel pawnLabel = new JLabel("Pawn");
		pawnButton.add(pawnLabel);
		pawnLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		pawnButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
			}

			@Override
			public void mouseReleased(final MouseEvent e) {
				generalButton.setBackground(lightGray);
				drowningButton.setBackground(lightGray);
				pawnButton.setBackground(blue);
				superPawnButton.setBackground(lightGray);
				lionButton.setBackground(lightGray);
				crocodileButton.setBackground(lightGray);
				zebraButton.setBackground(lightGray);
				giraffeButton.setBackground(lightGray);
				elephantButton.setBackground(lightGray);
				monkeyButton.setBackground(lightGray);
				createPieceInfo("Pawn");
			}
		});
		buttonRow.add(pawnButtonHolder);

		// Super Pawn Button
		JPanel superPawnButtonHolder = new JPanel();
		superPawnButtonHolder.setBackground(darkGray);
		superPawnButtonHolder.setLayout(new GridBagLayout());

		superPawnButtonHolder.add(superPawnButton);
		superPawnButton.setBackground(lightGray);
		superPawnButton.setLayout(new GridBagLayout());
		superPawnButton.setBorder(new MatteBorder(2, 2, 2, 2, blue));

		JLabel superPawnLabel = new JLabel("Super Pawn");
		superPawnButton.add(superPawnLabel);
		superPawnLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		superPawnButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
			}

			@Override
			public void mouseReleased(final MouseEvent e) {
				generalButton.setBackground(lightGray);
				drowningButton.setBackground(lightGray);
				pawnButton.setBackground(lightGray);
				superPawnButton.setBackground(blue);
				lionButton.setBackground(lightGray);
				crocodileButton.setBackground(lightGray);
				zebraButton.setBackground(lightGray);
				giraffeButton.setBackground(lightGray);
				elephantButton.setBackground(lightGray);
				monkeyButton.setBackground(lightGray);
				createPieceInfo("Super Pawn");
			}
		});
		buttonRow.add(superPawnButtonHolder);

		// Lion Button
		JPanel lionButtonHolder = new JPanel();
		lionButtonHolder.setBackground(darkGray);
		lionButtonHolder.setLayout(new GridBagLayout());

		lionButtonHolder.add(lionButton);
		lionButton.setBackground(lightGray);
		lionButton.setLayout(new GridBagLayout());
		lionButton.setBorder(new MatteBorder(2, 2, 2, 2, blue));

		JLabel lionLabel = new JLabel("Lion");
		lionButton.add(lionLabel);
		lionLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		lionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
			}

			@Override
			public void mouseReleased(final MouseEvent e) {
				generalButton.setBackground(lightGray);
				drowningButton.setBackground(lightGray);
				pawnButton.setBackground(lightGray);
				superPawnButton.setBackground(lightGray);
				lionButton.setBackground(blue);
				crocodileButton.setBackground(lightGray);
				zebraButton.setBackground(lightGray);
				giraffeButton.setBackground(lightGray);
				elephantButton.setBackground(lightGray);
				monkeyButton.setBackground(lightGray);
				createPieceInfo("Lion");
			}
		});
		buttonRow.add(lionButtonHolder);

		// Crocodile Button
		JPanel crocodileButtonHolder = new JPanel();
		crocodileButtonHolder.setBackground(darkGray);
		crocodileButtonHolder.setLayout(new GridBagLayout());

		crocodileButtonHolder.add(crocodileButton);
		crocodileButton.setBackground(lightGray);
		crocodileButton.setLayout(new GridBagLayout());
		crocodileButton.setBorder(new MatteBorder(2, 2, 2, 2, blue));

		JLabel crocodileLabel = new JLabel("Crocodile");
		crocodileButton.add(crocodileLabel);
		crocodileLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		crocodileButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
			}

			@Override
			public void mouseReleased(final MouseEvent e) {
				generalButton.setBackground(lightGray);
				drowningButton.setBackground(lightGray);
				pawnButton.setBackground(lightGray);
				superPawnButton.setBackground(lightGray);
				lionButton.setBackground(lightGray);
				crocodileButton.setBackground(blue);
				zebraButton.setBackground(lightGray);
				giraffeButton.setBackground(lightGray);
				elephantButton.setBackground(lightGray);
				monkeyButton.setBackground(lightGray);
				createPieceInfo("Crocodile");
			}
		});
		buttonRow.add(crocodileButtonHolder);

		// Zebra Button
		JPanel zebraButtonHolder = new JPanel();
		zebraButtonHolder.setBackground(darkGray);
		zebraButtonHolder.setLayout(new GridBagLayout());

		zebraButtonHolder.add(zebraButton);
		zebraButton.setBackground(lightGray);
		zebraButton.setLayout(new GridBagLayout());
		zebraButton.setBorder(new MatteBorder(2, 2, 2, 2, blue));

		JLabel zebraLabel = new JLabel("Zebra");
		zebraButton.add(zebraLabel);
		zebraLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		zebraButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
			}

			@Override
			public void mouseReleased(final MouseEvent e) {
				generalButton.setBackground(lightGray);
				drowningButton.setBackground(lightGray);
				pawnButton.setBackground(lightGray);
				superPawnButton.setBackground(lightGray);
				lionButton.setBackground(lightGray);
				crocodileButton.setBackground(lightGray);
				zebraButton.setBackground(blue);
				giraffeButton.setBackground(lightGray);
				elephantButton.setBackground(lightGray);
				monkeyButton.setBackground(lightGray);
				createPieceInfo("Zebra");
			}
		});
		buttonRow.add(zebraButtonHolder);

		// Giraffe Button
		JPanel giraffeButtonHolder = new JPanel();
		giraffeButtonHolder.setBackground(darkGray);
		giraffeButtonHolder.setLayout(new GridBagLayout());

		giraffeButtonHolder.add(giraffeButton);
		giraffeButton.setBackground(lightGray);
		giraffeButton.setLayout(new GridBagLayout());
		giraffeButton.setBorder(new MatteBorder(2, 2, 2, 2, blue));

		JLabel giraffeLabel = new JLabel("Giraffe");
		giraffeButton.add(giraffeLabel);
		giraffeLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		giraffeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
			}

			@Override
			public void mouseReleased(final MouseEvent e) {
				generalButton.setBackground(lightGray);
				drowningButton.setBackground(lightGray);
				pawnButton.setBackground(lightGray);
				superPawnButton.setBackground(lightGray);
				lionButton.setBackground(lightGray);
				crocodileButton.setBackground(lightGray);
				zebraButton.setBackground(lightGray);
				giraffeButton.setBackground(blue);
				elephantButton.setBackground(lightGray);
				monkeyButton.setBackground(lightGray);
				createPieceInfo("Giraffe");
			}
		});
		buttonRow.add(giraffeButtonHolder);

		// Elephant Button
		JPanel elephantButtonHolder = new JPanel();
		elephantButtonHolder.setBackground(darkGray);
		elephantButtonHolder.setLayout(new GridBagLayout());

		elephantButtonHolder.add(elephantButton);
		elephantButton.setBackground(lightGray);
		elephantButton.setLayout(new GridBagLayout());
		elephantButton.setBorder(new MatteBorder(2, 2, 2, 2, blue));

		JLabel elephantLabel = new JLabel("Elephant");
		elephantButton.add(elephantLabel);
		elephantLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		elephantButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
			}

			@Override
			public void mouseReleased(final MouseEvent e) {
				generalButton.setBackground(lightGray);
				drowningButton.setBackground(lightGray);
				pawnButton.setBackground(lightGray);
				superPawnButton.setBackground(lightGray);
				lionButton.setBackground(lightGray);
				crocodileButton.setBackground(lightGray);
				zebraButton.setBackground(lightGray);
				giraffeButton.setBackground(lightGray);
				elephantButton.setBackground(blue);
				monkeyButton.setBackground(lightGray);
				createPieceInfo("Elephant");
			}
		});
		buttonRow.add(elephantButtonHolder);

		// Monkey Button
		JPanel monkeyButtonHolder = new JPanel();
		monkeyButtonHolder.setBackground(darkGray);
		monkeyButtonHolder.setLayout(new GridBagLayout());

		monkeyButtonHolder.add(monkeyButton);
		monkeyButton.setBackground(lightGray);
		monkeyButton.setLayout(new GridBagLayout());
		monkeyButton.setBorder(new MatteBorder(2, 2, 2, 2, blue));

		JLabel monkeyLabel = new JLabel("Monkey");
		monkeyButton.add(monkeyLabel);
		monkeyLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		monkeyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
			}

			@Override
			public void mouseReleased(final MouseEvent e) {
				generalButton.setBackground(lightGray);
				drowningButton.setBackground(lightGray);
				pawnButton.setBackground(lightGray);
				superPawnButton.setBackground(lightGray);
				lionButton.setBackground(lightGray);
				crocodileButton.setBackground(lightGray);
				zebraButton.setBackground(lightGray);
				giraffeButton.setBackground(lightGray);
				elephantButton.setBackground(lightGray);
				monkeyButton.setBackground(blue);
				createPieceInfo("Monkey");
			}
		});
		buttonRow.add(monkeyButtonHolder);

		workingPanel.add(buttonRow, BorderLayout.SOUTH);

		// initially load as Lion
		createPieceInfo("General Game Rules");
	}

	protected void createPieceInfo(String piece) {

		this.rulesPanel.removeAll();
		this.rulesPanel.setLayout(new BorderLayout());
		this.rulesPanel.setBackground(darkGray);

		// rules text on left side
		JPanel rulesText = new JPanel();
		rulesText.setLayout(new GridBagLayout());
		rulesText.setBackground(lightGray);
		rulesText.setPreferredSize(new Dimension(365, 600));

		JTextArea rules = new JTextArea(getRules(piece));

		rules.setFont(new Font("Verdana", Font.PLAIN, 18));
		rules.setEditable(false);
		rules.setLineWrap(true);
		rules.setWrapStyleWord(true);
		rules.setAlignmentX(Component.CENTER_ALIGNMENT);
		rules.setPreferredSize(new Dimension(300, 500));
		rules.setBackground(lightGray);
		rulesText.add(rules);
		this.rulesPanel.add(rulesText, BorderLayout.WEST);

		// rules image for movement
		JPanel rulesImage = new JPanel();
		rulesImage.setLayout(new GridBagLayout());
		rulesImage.setBackground(darkGray);

		ImageIcon image = new ImageIcon(getImage(piece));
		JLabel rulesLabel = new JLabel(image);

		rulesImage.add(rulesLabel);
		this.rulesPanel.add(rulesImage);

		// update rulesPanel
		this.rulesPanel.revalidate();
		this.rulesPanel.repaint();

		// add it back to the working panel
		this.workingPanel.add(this.rulesPanel);
	}

	private Image getImage(String piece) {

		switch (piece) {

		case "Lion":

			return new ImageIcon("./Images/LionMoves.png").getImage().getScaledInstance(550, 550, Image.SCALE_DEFAULT);

		case "Zebra":

			return new ImageIcon("./Images/ZebraMoves.png").getImage().getScaledInstance(550, 550, Image.SCALE_DEFAULT);

		case "General Game Rules":

			return new ImageIcon("./Images/GeneralRules.png").getImage().getScaledInstance(550, 550,
					Image.SCALE_DEFAULT);

		case "Drowning":

			return new ImageIcon("./Images/DrowningRules.png").getImage().getScaledInstance(550, 550,
					Image.SCALE_DEFAULT);

		case "Pawn":

			return new ImageIcon("./Images/PawnMoves.png").getImage().getScaledInstance(550, 550, Image.SCALE_DEFAULT);

		case "Super Pawn":

			return new ImageIcon("./Images/SuperPawnMoves.png").getImage().getScaledInstance(550, 550,
					Image.SCALE_DEFAULT);

		case "Crocodile":

			return new ImageIcon("./Images/CrocodileMoves.png").getImage().getScaledInstance(550, 550,
					Image.SCALE_DEFAULT);

		case "Giraffe":

			return new ImageIcon("./Images/GireffeMoves.png").getImage().getScaledInstance(550, 550,
					Image.SCALE_DEFAULT);

		case "Elephant":

			return new ImageIcon("./Images/ElephantMoves.png").getImage().getScaledInstance(550, 550,
					Image.SCALE_DEFAULT);

		case "Monkey":

			return new ImageIcon("./Images/MonkeyMoves.png").getImage().getScaledInstance(550, 550,
					Image.SCALE_DEFAULT);

		default:
			return null;
		}
	}

	private String getRules(String piece) {

		String rules = "";

		switch (piece) {

		case "Lion":
			rules = "Lion\n\n" + "A lion can move and capture one space in any direction as"
					+ " long as it is inside its \"castle\". The lion has a special capture move that"
					+ " can only be used on another lion. For this special capture there must be a "
					+ "straight or diagonal line with no pieces in-between the two lions.";

			break;

		case "Zebra":
			rules = "Zebra\n\n" + "The zebra moves and captures like a chess knight, it can move "
					+ "and capture in a \"L\" shape. Either two up/down then one left/right, or "
					+ "one up/down then two left/right.";
			break;

		case "General Game Rules":
			rules = "General\n\n" + "Congo is a chess variant that ends when the lion is "
					+ "captured. The game is also won by having a lion and "
					+ "any piece(s) against a bare lion. A draw occurs when "
					+ "there are two bare lions against each other.";
			break;

		case "Drowning":
			rules = "Drowning\n\n" + "For all pieces except the crocodile, if a piece spends "
					+ "two turns in the river, it will drown (piece is removed).";
			break;

		case "Pawn":
			rules = "Pawn\n\n" + "A pawn can move and capture in front of itself one space "
					+ "diagonally or orthogonally. Once the pawn is past the river "
					+ "it can move up to two spaces backwards (without jumping). "
					+ "It can be promoted to a Super Pawn by reaching the last row.";
			break;

		case "Super Pawn":
			rules = "Super Pawn\n\n" + "Super Pawn can move just like a pawn but has multiple additional moves."
					+ " It can capture one space to its left and right. It can move one or "
					+ "two spaces backwards or diagonally(without jumping). Unlike pawn, "
					+ "its moves are not affected by its position on the board.";
			break;

		case "Crocodile":
			rules = "Crocodile\n\n" + "The crocodile can move and capture one space in any direction. "
					+ "It can also move to the river (from either side) and capture on "
					+ "the way or on the river directly in front; it may not jump during "
					+ "this move. While in the river, the Crocodile can move left or "
					+ "right until it reaches an enemy or an empty space.";
			break;

		case "Giraffe":
			rules = "Giraffe\n\n" + "The giraffe can move but not capture one space in any direction. "
					+ "It can move to and capture two spaces any way orthogonally or diagonally.";
			break;

		case "Elephant":
			rules = "Elephant\n\n" + "The elephant can move and capture two spaces in the orthogonal direction. "
					+ "The elephant can jump (whether occupied or not) to capture the second space away.";
			break;

		case "Monkey":
			rules = "Monkey\n\n" + "Monkey can move but not capture one step in any direction."
					+ " Monkey can only capture by jumping an enemy, thus moving two spaces away"
					+ " the orthogonal or diagonal direction.  This capture move can be performed"
					+ " any number of times during a turn (It is the only piece that can move more"
					+ " than once). The monkey cannot jump over its own pieces, it cannot jump the"
					+ " same piece twice, and all the pieces are removed after the full multi-capture is done.";
			break;

		default:
			rules = "";
		}

		return rules;

	}

	@Override
	void setErrorCards() {

	}
}

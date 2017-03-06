package draughts.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import draughts.model.Board;

/**
 * part of the VIEW component in a MVC pattern for this application. 
 */
public class MainFrame extends JFrame implements Cloneable {
	
	private static final long serialVersionUID = 1L;

	private static final int MINIMUM_INFO_PANEL = 250;

	private static Color BORDER_LINE_COLOR;
	private static LineBorder panelsBorderLine;
	
	private Board board;
	private JMenuBar menuBar;
	private JPanel boardPanel;
	private BoardView boardView;
	private JPanel infoPanel;

	
	/**
	 * Constructor for the main window displayed by the program. <p>
	 * <i>Default frame size is set to NORMAL. <br>
	 * Either 3/4 of screen size or full screen for small screens ( width < 600 || height  < 400 ). </i>
	 * @param board : the board model. 
	 * @param gameType : the type of draughts game. 
	 */
	public MainFrame(Board board) {
		this(board, MainFrameSize.NORMAL);
	}

	/**
	 * Constructor for the main window displayed by the program. 
	 * @param board : the board model. 
	 * @param gameType : the type of draughts game. 
	 * @param frameSize : the size of the frame relatively to the screen (minimal, 1/2 screen, 3/4 screen, or full screen). <br>
	 */
	public MainFrame(Board board, MainFrameSize frameSize) {

		super();
		this.board = board; 
		// Parameters for the main frame
		setLayout(new BorderLayout());
		setTitle("Draugths");
		URL iconURL = getClass().getResource("../Ressources/Board_10x10_64px.png");
		ImageIcon icon = new ImageIcon(iconURL);
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMinimumSize(MainFrameSize.getMinimumFrameSize());
		setFrameSize(frameSize);
		this.setResizable(true);
		
		// Adds MenuBar
		menuBar = MenuBar.SINGLETON.get();
		setJMenuBar(menuBar);
		

		// Create board and information panels
		BORDER_LINE_COLOR = new ColorScheme(board.getGameType()).OPPONENT_PIECE_COLOR;
		panelsBorderLine = new LineBorder(BORDER_LINE_COLOR, 2, true);
		boardPanel = new JPanel();
		infoPanel = new JPanel();
		resizePanels();
		
		// Customizes board panel
		boardPanel.setBorder(new TitledBorder(panelsBorderLine, board.getDesignation(), TitledBorder.CENTER, TitledBorder.BELOW_TOP));
		boardPanel.setLayout(new GridBagLayout());
		boardView = new BoardView(getInsideDimension(boardPanel), board);
		boardPanel.add(boardView);
		add(boardPanel, BorderLayout.WEST);

		// Customizes the information panel
		infoPanel.setBorder(new TitledBorder(panelsBorderLine, "Information", TitledBorder.CENTER, TitledBorder.BELOW_TOP));
		add(infoPanel, BorderLayout.EAST);
		
		setVisible(true);
				
		// Adds a component listener to dynamically resize the inner panels whenever the window is resized
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) { resizePanels(); } });

	}	
		
	public void changeBoard(Board board) {
		
		if (boardView != null ) {
			this.board = board;
			boardPanel.removeAll();
			TitledBorder border = (TitledBorder) boardPanel.getBorder();
			border.setTitle(board.getDesignation());
			boardPanel.setBorder(border);
			boardView = new BoardView(getInsideDimension(boardPanel), board);
			boardPanel.add(boardView);
			boardPanel.revalidate();
			boardPanel.repaint();
		}
		
	}
	
	public void displayLabels(boolean displayLabels) {
		BoardView.displayLabels = displayLabels;
		boardView.revalidate();
		boardView.repaint();
	}
	

	/**
	 * Helper method for setting up the size of the main frame. 
	 * @param frameSize : the size of the frame, relatively to the screen size.
	 */
	public void setFrameSize(MainFrameSize frameSize) {
		setSize(frameSize.getSettings().dimension);
		setLocation(frameSize.getSettings().x, frameSize.getSettings().y);
	}
	
	/**
	 * Helper method determining inside dimensions of a container
	 * @param container : the container to analyze. 
	 * @return the available dimension inside the given container (dimension - insets). 
	 */
	private static Dimension getInsideDimension(Container container) {
		
		boolean containerState = container.isVisible();
		container.setVisible(true); // insures container is visible in order to get inside dimensions
		Insets insets = container.getInsets();
		Dimension dimension = new Dimension(container.getWidth() - (insets.left  + insets.right), 
				container.getHeight() - (insets.top  + insets.bottom));
		container.setVisible(containerState);
		return dimension;
		
	}
	
	/**
	 * Helper method resizing the mainframe inner panels: 
	 * <li>boardPanel as a square and infoPanel occupying remaining space if width above a minimum size, 
	 * <li>or minimal size for infoPanel and remaining space for the board panel (rectangle). 
	 */
	private void resizePanels() {
		
		Dimension frameDimension = getInsideDimension(this);
		frameDimension = new Dimension(frameDimension.width, frameDimension.height - menuBar.getSize().height);
		if (boardPanel != null ) {
			boardPanel.setPreferredSize(new Dimension(
				Math.min(frameDimension.height, frameDimension.width - MINIMUM_INFO_PANEL), 
				frameDimension.height));
			boardPanel.setSize(boardPanel.getPreferredSize());
			changeBoard(board);
		}
		if ( infoPanel != null ) {
			infoPanel.setPreferredSize(new Dimension(
				Math.max(MINIMUM_INFO_PANEL, frameDimension.width - frameDimension.height), 
				frameDimension.height));
			infoPanel.setSize(infoPanel.getPreferredSize());
		}
		this.revalidate();
		
	}
	
}

package draughts.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

import draughts.model.Piece;

/**
 * part of the VIEW component in a MVC pattern for this application
 */
public class PieceView extends JComponent implements MouseListener {

	private static final long serialVersionUID = 1L;

	private static PieceView selectedPiece = null;
	
	/** 
	 * @param REDUCTION_FACTOR determines the size of the pieces relatively to the square size. 
	 * value must be in the range 0 to 1. 
	 */
	private final double REDUCTION_FACTOR = 0.8;
	/**
	 * Holds the color scheme for board and pieces display. 
	 * @see {@link ColorScheme}
	 */
	private ColorScheme displayColors;
	
	// TODO implement highlighting of pieces with forced moves (if any)...
	private Piece piece;
	private int pieceSize;
	private boolean mouseIn;
	private boolean selected;
	
	/**
	 * Constructor for a {@code PieceView} object.
	 * @param piece : a {@code Piece} object. 
	 * @param SQUARE_SIZE : the size of the component to draw in pixel. 
	 * @see {@link #paintComponent(Graphics)} (@Override)
	 */
	public PieceView(Piece piece, int SQUARE_SIZE) {
		super();
		this.piece = piece;
		this.pieceSize = (int) (SQUARE_SIZE * REDUCTION_FACTOR);
		setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
		setBounds(0, 0, SQUARE_SIZE, SQUARE_SIZE);
		addMouseListener(this);
	}
	
	/**
	 * Method used to deselect the currently selected piece. 
	 */
	public static void deselectPiece() {
		if ( selectedPiece != null ) {
			selectedPiece.selected = false;
			selectedPiece.repaint();
			selectedPiece = null;			
		}
	}

	/**
	 * Method used to display a piece as selected on the board. 
	 * 
	 * @param pieceView : the pieceView to display as selected. 
	 */
	public static void selectPiece(PieceView pieceView) {
		deselectPiece();
		selectedPiece = pieceView;
		pieceView.selected = true;
		selectedPiece.repaint();
		// TODO implement link to potential moves available from this piece...
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		selectPiece(this);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		mouseIn = true;
		repaint();
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		mouseIn = false;
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int center = (int) (pieceSize / 2);
		// Selects the color corresponding to the piece
		Color PIECE_COLOR = (piece.isWhite()) ? displayColors.PLAYER_PIECE_COLOR : displayColors.OPPONENT_PIECE_COLOR;
		// Uses a darker color to highlight the square when mouse hovering
		PIECE_COLOR = ( mouseIn ) ? PIECE_COLOR.darker() : PIECE_COLOR;
		/*
		 * Default display for a MAN
		 */		
		g2.setColor(PIECE_COLOR);
		g2.fillOval(center, center, pieceSize, pieceSize);
		// Selected piece is highlighted by a darker border ring 
		if ( selected ) {
			g2.setColor(displayColors.HIGHLIGHT_COLOR);
			g2.setStroke(new BasicStroke(2.5f));
			g2.drawOval(center, center, pieceSize, pieceSize);
		}
		/*
		 * Add a crown to the KING
		 */ 
		if ( piece.isKing() ) {
			// Inverts the colors and inserts a circle to crown the KING.
			g2.setColor( (piece.isWhite()) ? displayColors.OPPONENT_PIECE_COLOR : displayColors.PLAYER_PIECE_COLOR);
			g2.setStroke(new BasicStroke(2.5f));
			g2.drawOval(center, center, center, center);
		}
	}

}

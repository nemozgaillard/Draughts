package draughts.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.border.LineBorder;

import draughts.model.Board;
import draughts.model.Piece;


/**
 * part of the VIEW component in a MVC pattern for this application. 
 */
public class BoardView extends JComponent{

	private static final long serialVersionUID = 1L;	
	
	/**
	 * boardView:<p>
	 * The board view is modelized as a two-dimension array, {@code boardView}, containing the board's squares views, <br> 
	 * following the same pattern as the board model. 
	 */
	private JComponent[][] boardView;
	/**
	 * Holds the color scheme for board and pieces display. 
	 * @see {@link ColorScheme}
	 */
	private ColorScheme colorScheme;
	/**
	 * The dimension of the board: number of columns and rows
	 */
	private Dimension boardSize;
	private Dimension margins;
	/**
	 * @param SQUARE_SIZE is the size of the square side in pixel. 
	 */
	private int SQUARE_SIZE;
	/**
	 * displayLabels: determines if labels are displayed or not. 
	 */
	public static boolean displayLabels;

	/**
	 * Constructor for the board view. 
	 * @param dimension : the size of a squared JPanel where to display the drawn board.  
	 * @param board : the model of the board to be drawn. 
	 * @param gameType : the game type of the draughts game to be drawn. 
	 */
	public BoardView(Dimension dimension, Board board) {
		
		super();
		this.colorScheme = new ColorScheme(board.getGameType());
		
		// In pixel, the height of the board to be drawn. 
		int minDimension = Math.min(dimension.width, dimension.height); 
		this.boardSize = board.getSize();
		int maxSize = Math.max(boardSize.width, boardSize.height);
		this.SQUARE_SIZE = (minDimension - minDimension  % (maxSize + 1)) / (maxSize + 1);
		
		this.margins = new Dimension(
				(int) ( (dimension.width - boardSize.width * SQUARE_SIZE) / 2 ), 
				(int) ( (dimension.height - boardSize.height * SQUARE_SIZE) / 2 )
				);
		
		// Initiates and populates the boardView array. 
		boardView = new JComponent[boardSize.width][boardSize.height];
		for (int row = 0 ; row < boardSize.width ; row++) {
			for (int col = 0 ; col < boardSize.height ; col++) {	
				if (board.getSquare(row, col).isActive()) {
					boardView[row][col] = new ActiveSquareView((Board.ActiveSquare) board.getSquare(row, col));
				}
				else {
					boardView[row][col] = new InactiveSquareView((Board.InactiveSquare) board.getSquare(row, col));
				}
				boardView[row][col].setBounds(
						row * SQUARE_SIZE + margins.width, col * SQUARE_SIZE + margins.height, 
						SQUARE_SIZE, SQUARE_SIZE);
				add(boardView[row][col]);
			}
		}
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(
				boardSize.width * SQUARE_SIZE + 2 * margins.width, 
				boardSize.height * SQUARE_SIZE + 2 * margins.height);
	}
	
	private abstract class SquareView extends JComponent implements MouseListener {
		
		private static final long serialVersionUID = 1L;
		
		@SuppressWarnings("unused")
		protected Board.Square square;
		protected PieceView pieceView;
		protected boolean mouseIn;
		
		protected SquareView(Board.Square square) {
			super();
			this.square = square;
			this.pieceView = null;
			this.mouseIn = false;
			addMouseListener(this);
		}
		
		protected abstract void addPieceView(Piece piece);
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);		
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// Deselects the eventually selected piece if clicked outside. 
			PieceView.deselectPiece();
		}
		
		@Override
		public void mouseEntered(MouseEvent e) { }
		@Override
		public void mouseExited(MouseEvent e) { }
		@Override
		public void mousePressed(MouseEvent e) { }
		@Override
		public void mouseReleased(MouseEvent e) { }
		
	}
	
	
	/**
	 * Handles active squares display and behavior. 
	 */
	private class ActiveSquareView extends SquareView {
		
		private static final long serialVersionUID = 1L;
		
		private Board.ActiveSquare square;
		private LabelView labelView;
		
		private ActiveSquareView(Board.ActiveSquare square) {
			super(square);
			this.square = (Board.ActiveSquare) square;
			if ( this.square.isOccupied() ) {
				this.addPieceView(square.getPiece());
			}
			this.labelView = new LabelView(square.getLabel());
			labelView.setBounds(0, 0, SQUARE_SIZE, SQUARE_SIZE);
			add(labelView);
		}

		@Override
		protected void addPieceView(Piece piece) {
			this.pieceView = new PieceView(piece, SQUARE_SIZE, colorScheme);
			pieceView.setBounds(0, 0, SQUARE_SIZE, SQUARE_SIZE);
			add(pieceView);
		}
		
		@Override
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			LineBorder border;
			// Uses a darker color to highlight the square when mouse hovering
			Color SQUARE_COLOR = ( mouseIn ) ? colorScheme.DARK_FIELD_COLOR.darker() : colorScheme.DARK_FIELD_COLOR;
			// Highlights the square with a border if on a capture path
			
			// Default square display
			g.setColor(SQUARE_COLOR);
			g.fillRect(0, 0, SQUARE_SIZE, SQUARE_SIZE);
			// Displays label if required
			labelView.setVisible(displayLabels) ;
			if ( this.pieceView != null ) { this.pieceView.setVisible(true); }
			// Capture path highlighting
			if ( square.isOnCapturePath() ) {
				border = new LineBorder(colorScheme.HIGHLIGHT_COLOR, 3, true);
				this.setBorder(border);
			}
			else {
				this.setBorder(null);
			}
		
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

		private class LabelView extends JComponent {
			
			private static final long serialVersionUID = 1L;

			private final static int OFFSET = 4; 
			private String label;
			
			private LabelView(String label) {
				super();
				this.label = label;
			}
			
			@Override
			public void paintComponent(Graphics g) {
				
				super.paintComponent(g);
				g.setColor(colorScheme.LIGHT_FIELD_COLOR);
				g.setFont(g.getFont().deriveFont(Font.BOLD));
				g.drawString(label, OFFSET , SQUARE_SIZE - OFFSET);

			}

		}
		
	}

	
	/**
	 * Handles inactive squares display and behavior. 
	 */
	private class InactiveSquareView extends SquareView {

		private static final long serialVersionUID = 1L;

		private InactiveSquareView(Board.InactiveSquare square) {
			super(square);
			this.square = (Board.InactiveSquare) square;
		}
		
		@Override
		protected void addPieceView(Piece piece) { }

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(colorScheme.LIGHT_FIELD_COLOR);
			g.fillRect(0, 0, SQUARE_SIZE, SQUARE_SIZE);			
		}
		
	}
	
	
}

package draughts.model;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Vector;

import draughts.exceptions.EmptySquareException;
import draughts.exceptions.OccupiedSquareException;

/**
 * part of the MODEL component in a MVC pattern for the draughts application. <br>
 * The Board is modelized as a two-dimensional array of squares, either active (playable) or inactive. 
 */
public class Board{
	
	/**
	 * @param board (private)<p>
	 * The board is modelized as a two-dimensional array named {@code board} containing the board's squares. 
	 */
	private Square[][] board;
	private HashMap<String, Integer> labelMap = new HashMap<String, Integer> ();
	private GameType gameType;
	private Color activeColor;
	private Dimension size;
	private String designation;


	/**
	 * Constructor for the {@code Board} object. 
	 * 
	 * @param gameType (String): the type of draughts game played
	 * 
	 * @see {@link #getDesignation()} method
	 * @see {@link #getSize()} method
	 * @see {@link #getSquare(int, int)} method
	 */
	public Board(GameType gameType) {
		super();
		
		int width = gameType.getBoardWidth();
		int height = gameType.getBoardHeight();
		int invertFlag = gameType.getInvertFlag();
		
		this.gameType = gameType;
		this.activeColor = Color.valueOfString(gameType.getFirstPlayerColor());
		this.designation = gameType.getDesignation();
		this.size = new Dimension(width, height);
		
		board = new Square[width][height];
		// fills the board starting from the top left square board[0][0], proceeding row by row. 
		for (int row = 0 ; row < width ; row++) {
			for (int col = 0 ; col < height ; col++) {
				// the top left square is an inactive square if (height + invertFlag) is even, 
				// from there, squares alternate along rows and columns. 
				if ((row + col + height + invertFlag) % 2 == 0) {
					board[row][col] = new InactiveSquare(row, col);
				}
				else {
					board[row][col] = new ActiveSquare(row, col);
					labelMap.put(board[row][col].label, 100 * row + col);
				}
			}
		}	
	}
	
	public void addPieces(Vector<Piece> pieces) {
		// TODO debug... problem with pieces display...
		for ( Piece piece : pieces) {
			System.out.println(piece);
			String label = piece.getSquareLabel();
			System.out.println(label);
			this.getSquare(label).addPiece(piece);
		}
	}
	
	public String getDesignation() {
		return designation;
	}

	public GameType getGameType() {
		return gameType;
	}
	
	public Dimension getSize() {
		return size;
	}

	/**
	 * Gets the square according to its internal coordinates: row and column numbers. 
	 * @param row : the row number of this board model. 
	 * @param col : the column number of this board model.  
	 * @return the square at row {@code row} and column {@code col}. 
	 */
	public Square getSquare(int row, int col) {
		return board[row][col];
	}
	
	/**
	 * Gets the active square according to the label. 
	 * @param label : the label of the square according to the current game type's notation. 
	 * @return the square object corresponding to this label. 
	 */
	public ActiveSquare getSquare(String label) {
		int address = labelMap.get(label);
		return (ActiveSquare) board[(int) (address / 100)][address % 100];
	}
	
	/**
	 * Abstract {@code Square} class. 
	 * Parent of the {@code ActiveSquare} and {@code InactiveSquare} classes. 
	 * 
		 * @param row : the row number on the board internal representation. 
		 * @param col : the column number on the board internal representation. 
	 * @param active: whether a cell is active (playable) or not. 
	 * @param color : the color of the square, either {@code BLACK} or {@code WHITE}. 
	 * 
	 * @see {@link #getFirstPlayerColor()} method
	 * @see {@link #isActive()} method
	 */
	public abstract class Square{
		
		protected int row;
		protected int col;
		protected boolean active;
		protected Color color;
		protected String label;
		protected Piece piece;
		protected boolean onCapturePath; 
		
		/**
		 * Constructor for the Square class. <br>
		 * <i>Default values are:
		 * <li>active = false
		 * <li>label = null
		 * <li>piece = null;
		 * <li>onCapturePath = null
		 * 
		 * @param row : the row number on the board internal representation. 
		 * @param col : the column number on the board internal representation. 
		 * @param color : the color of the square, either {@code BLACK} or {@code WHITE}. 
		 */
		public Square(int row, int col, Color color) {
			super();
			this.row = row;
			this.col = col;
			this.color = color;
			// default values
			this.active = false;
			this.label = null;
			this.piece = null;
			this.onCapturePath = false;
		}
		
		/**
		 * @return the row number of this {@code Square}.
		 */
		public int getRow() {
			return row;
		}
		
		/**
		 * @return the column number of this {@code Square}. 
		 */
		public int getColumn() {
			return col;
		}

		/**
		 * Accessor for the {@code active} parameter of the square.  
		 * @return active: active indicates whether a cell is active (playable) or not.
		 */
		public boolean isActive() {
			return active;
		}
		
		@Override
		public String toString() {
			return String.format("square (%s, %s), color: %s%s", row, col, color, ( label != null ) ? ", " + label : "");
		}

	}

	
	/**
	 * {@code ActiveSquare} class. <p>
	 * Extends the {@link Board.Square} class...
	 * 
	 *  @param piece : the piece located on this square, either null, MAN or KING. 
	 *  @param label : the label corresponding to this square, according to notation rules - either numeric or alphanumeric values. 
	 *  
	 *  @see {@link #addPiece()} method
	 *  @see {@link #removePiece()} method
	 *  <p>
	 *  @see {@link #getPiece()} method
	 *  @see {@link #getColumn()} method
	 *  @see {@link #getRow()} method
	 *  @see {@link #getLabel()} method
	 */
	public class ActiveSquare extends Square{
		
		/**
		 * Generic constructor for an {@code ActiveSquare} object (playable square). 
		 * @see {@link Board.Square#Square(int, int, Color)}
		 */
		public ActiveSquare(int row, int col) {
			super(row, col, activeColor);
			this.active = true;
			this.label = PDN.getLabel(gameType, row, col);
		}
		
		/**
		 * Constructor for an {@code ActiveSquare} object (playable square). <p>
		 * 
		 * @param row : the row number on the board internal representation. 
		 * @param col : the column number on the board internal representation. 
		 * @param color : the color of the square, either {@code BLACK} or {@code WHITE}. <p>
		 * This constructor also add a Piece object to the ActiveSquare. 
		 * @param piece : the piece (Piece object) located on the square:
		 * <li> either null, MAN or KING, 
		 * <li> and either BLACK or WHITE.  
		 */
		public ActiveSquare(int row, int col, Piece piece) {
			this(row, col);
			this.piece = piece;
		}
		
		/**
		 * Adds a piece to this square
		 * @param piece
		 * Adds a {@code Piece} object to this {@code Square}, 
		 * @throws {@code OccupiedSquareException} if a piece is already located on the square. 
		 */
		public void addPiece(Piece piece) {
			if ( this.isEmpty() ) {
				this.piece = piece;				
			}
			else {
				throw new OccupiedSquareException("It is not allowed to add a piece to a non-empty square. ");
			}
		}
		
		/**
		 * Removes, if existing, this {@code Square}'s {@code Piece} object <b>piece</b>.
		 * @throws {@code EmptySquareException} if the square is already empty. 
		 */
		public void removePiece() {
			if ( this.isEmpty() ) {
				throw new EmptySquareException("It is not possible to remove a piece from an empty square. ");
			}
			else {
				this.piece = null;
			}
		}

		/** Accessor for this {@code Square}'s label. <p>
		 * @return label encoded according to the PDN standard. <p>
		 * <i>{@code Square} label is either encoded with a numeric or an alphanumeric notation according to the PDN. </i>
		 */
		public String getLabel() {
			return this.label;
		}

		/**
		 * @return the Piece object piece, if any, located on this {@code Square}. <p> 
		 * Possible return values are null or a Piece object (MAN or King): <p>
		 * Returned piece is "<a href="http://idioms.thefreedictionary.com/be+on+the+square">on the square</a>."
		 */
		public Piece getPiece() {
			return piece;
		}

		/**
		 * @return true if this {@code Square} is empty. <p> 
		 */
		public boolean isEmpty() {
			return ( piece == null );
		}

		/**
		 * @return true if a piece is located on this {@code Square}. <p> 
		 */
		public boolean isOccupied() {
			return ( !this.isEmpty() );
		}

		/**
		 * Accessor for this {@code Square}'s onCapturePath <b>boolean</b> parameter. 
		 * @return true if the square 
		 */
		public boolean isOnCapturePath() {
			return onCapturePath;
		}

		/**
		 * Setter for this {@code Square}'s onCapturePath <b>boolean</b> parameter. 
		 * @param bool
		 */
		public void setOnCapturePath(boolean bool) {
			this.onCapturePath = bool;
		}
		
	}
	
	
	/**
	 * {@code InactiveSquare} class. <p>
	 * Extends the {@link Board.Square} class, setting the {@code active} parameter to <b><i style="color:#800080">false</i></b>. 
	 */
	public class InactiveSquare extends Square{
		
		/**
		 * Constructor for an InactiveSquare object (non-playable square).
		 *  
		 * @param row : the row number on the board internal representation. 
		 * @param col : the column number on the board internal representation. 
		 * @param color : the color of the square, either {@code BLACK} or {@code WHITE}. 
		 * 
		 * @see {@link Board.Square#Square(int, int, Color)}
		 */
		public InactiveSquare(int row, int col) {
			// inactiveColor is not the activeColor among the two colors BLACK and WHITE
			super(row, col, (activeColor == Color.BLACK) ? Color.WHITE : Color.BLACK);			
		}
	
	}
	
}

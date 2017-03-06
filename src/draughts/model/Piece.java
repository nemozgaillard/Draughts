package draughts.model;

/**
 * part of the MODEL component in a MVC pattern for this application
 */

public class Piece {

	public static enum PieceType { 
		MAN("MAN") , KING("KING") ;
		private final String str;
		private PieceType(final String str) { this.str = str; }
		@Override
		public String toString() { return str; }
	}

	private Color color;
	private PieceType type;
	
	public String squareLabel;
	
	protected Piece() {
		super();
	}

	/**
	 * Constructor for a piece, either MAN or KING. 
	 * @param color
	 * @param type
	 */
	public Piece(Color color, PieceType type) {
		super();
		this.color = color;
		this.type = type;
	}
		
	/**
	 * Constructor for a piece, either MAN or KING. 
	 * @param color
	 * @param type
	 */
	public Piece(Color color, PieceType type, String label) {
		this(color, type);
		this.squareLabel = label;
	}
		
	/**
	 * Method to promote a MAN to KING status. 
	 */
	public void promote() {
		type = PieceType.KING;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getSquareLabel() {
		return squareLabel;
	}
	
	public boolean isBlack() {
		return (color == Color.BLACK);
	}
	
	public boolean isWhite() {
		return (!isBlack()); 
	}
	
	public boolean isMan() {
		return (type == PieceType.MAN);
	}
	
	public boolean isKing() {
		return (type == PieceType.KING);
	}
		
}

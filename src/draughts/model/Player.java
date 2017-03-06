package draughts.model;

import java.util.Vector;

/**
 * part of the MODEL component in a MVC pattern for this application
 */
public class Player {
	
	private String name;
	private Color color;
	private Vector<Piece> pieces;
	
	/**
	 * Constructor for a player with attributes name and color, and identified as first player or not. 
	 * @param name : the name of the player. 
	 * @param color : the color, either black or white. 
	 * @param IsFirstPlayer : whether or not this player is starting the game. 
	 */
	public Player(String name, Color color, boolean IsFirstPlayer) {
		super();
		this.name = name;
		this.color = color;
	}
	
	/**
	 * Constructor for a player with attributes name and color, and identified as first player or not. 
	 * This constructor also adds a list of the pieces available to the player. 
	 * @param name : the name of the player. 
	 * @param color : the color, either black or white. 
	 * @param IsFirstPlayer : whether or not this player is starting the game. 
	 * @param pieces : the list of the pieces available to this player. 
	 */
	public Player(String name, Color color, boolean IsFirstPlayer, Vector<Piece> pieces) {
		this(name, color, IsFirstPlayer);
		this.addPieces(pieces);
	}
	
	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public void addPieces(Vector<Piece> pieces) {
		
		if ( pieces != null ) {
			
			if ( this.pieces == null ) {
				this.pieces = new Vector<Piece>(pieces);
			}
			else {
				this.pieces.addAll(pieces);
			}

		}
		
	}
	
}

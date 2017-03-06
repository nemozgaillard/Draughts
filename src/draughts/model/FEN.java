package draughts.model;

import java.util.Arrays;
import java.util.Vector;

import net.percederberg.grammatica.parser.ParserCreationException;
import net.percederberg.grammatica.parser.ParserLogException;
import parsers.FenGenerator;



/**
 * part of the MODEL component in a MVC pattern for this application <p>
 * FEN class. <br>
 * The Forsyth-Edwards Notation or shortly FEN tag defines a position on the board. 
 * @see <a href="http://pdn.fmjd.org/fen.html">pdn.fmjd.org - FEN Tag</a>.
 * 
 * The following grammar describes the formal syntax of the value of a FEN tag.
 * 
 * // Productions
 * Fen                        : COLOR (NumericSquares | AlphaNumericSquares) DOT?
 * NumericSquares             : (COLON COLOR NumericSquareSequence)+
 * NumericSquareSequence      : NumericSquareRange (COMMA NumericSquareRange)*
 * NumericSquareRange         : KING? NUMSQUARE (HYPHEN NUMSQUARE)?
 * AlphaNumericSquares        : (COLON COLOR AlphaNumericSquareSequence)+
 * AlphaNumericSquareSequence : KING? ALPHASQUARE (COMMA KING? ALPHASQUARE)*
 * 
 * // Tokens
 * COLOR                      : "[WB?]"
 * KING                       : "K"
 * ALPHASQUARE                : "[a-h][1-8]"
 * NUMSQUARE                  : "([1-9][0-9]*)|(0[1-9][0-9]*)|0"
 * HYPHEN                     : "\-"
 * COMMA                      : "\,"
 * COLON                      : "\:"
 * DOT                        : "\."
 * 
 */
public class FEN {
	

	private enum notation {
		NUMERIC, ALPHANUMERIC;
		public static boolean isNumeric(String label) {
			return notation.natureOf(label) == NUMERIC;
		}
		public static notation natureOf(String label) {
			return ( label.matches("[1-9][0-9]?") ) ? NUMERIC : ALPHANUMERIC ;
			}
	}
	
	private enum separator {
		COMMA(","), HYPHEN("-");
		private final String str; 
		private separator(String str) { this.str =str; }
		@Override
		public String toString() { return str; }
	}

	public Color turn = null;
	public Vector<Piece> playerPieces = null;
	public Vector<Piece> opponentPieces = null;
	
	public FEN() {
		super();
		this.turn = null;
		this.playerPieces = new Vector<Piece> ();
		this.opponentPieces = new Vector<Piece> ();
	}
	
	public static FEN parseFEN(String expression) {
		FEN fen = new FEN();
		FenGenerator generator = new FenGenerator();
		try {
			fen = generator.generateFEN(expression);
		} catch (ParserCreationException | ParserLogException e) {
			e.printStackTrace();
		}
		return fen;
	}

	public static FEN parseInitialFEN(GameType gameType) {
		return parseFEN(FEN.getSartingPosition(gameType));
	}

		public static String getSartingPosition(GameType gameType) {
		
		String fen = "";
		switch ( gameType ) {
		
		case INTERNATIONAL:	
		case FRISIAN: 
			fen = "W:W31-50:B1-20";
			break;
			
		case ENGLISH:
		case AMERICAN_POOL:
			fen = "B:B1-12:W21-32";
			break;
			
		case ITALIAN:
			fen = "W:W21-32:B1-12";
			break;
			
		case SPANISH:
		case PORTUGUESE:
			fen = "W:W1-12:B21-32";
			break;
			
		case CANADIAN:
			fen = "W:W43-72:B1-30";
			break;
			
		case THAI:
			fen = "B:B25-32:W1-8";
			break;
			
        case RUSSIAN:
        case BRAZILIAN:
        case CZECH:
			fen = "W:Wa1,c1,e1,g1,b2,d2,f2,h2,a3,c3,e3,g3:Bb6,d6,f6,h6,a7,c7,e7,g7,b8,d8,f8,h8";
			break;

		case SPANTSIRETTI:
			fen = "W:Wa1,c1,e1,g1,b2,d2,f2,h2,a3,c3,e3,g3:Bb6,d6,f6,h6,a7,c7,e7,g7,b8,d8,f8,h8";
			break;

		// TODO Later implement TURKISH draughts game
		// case TURKISH:
		//	fen = "";
		//	break;
		
		}
		return fen;
	}
		
	/**
	 * Generates a FEN notation from a FEN object.
	 * @param fen : A FEN object containing the first player's and opponent's pieces. 
	 * @return A correctly formated fen String. 
	 */
	public static String writeFEN(FEN fen) {
		return writeFEN(fen.playerPieces, fen.opponentPieces);
	}
	
	/**
	 * Generates a FEN notation from the vectors containing the pieces of each player. .
	 * @param playerPieces : A vector containing the first player's pieces. 
	 * @param opponentPieces : A vector containing the opponent's pieces. 
	 * @return A correctly formated fen String. 
	 */
	public static String writeFEN(Vector<Piece> playerPieces, Vector<Piece> opponentPieces) {
		
		String result = "";
		if ( playerPieces != null && opponentPieces != null ) {
			String playerColor = playerPieces.elementAt(0).isWhite() ? "W" : "B";
			String opponentColor = opponentPieces.elementAt(0).isWhite() ? "W" : "B";
			result += playerColor + ":" + playerColor + writeFEN(playerPieces);
			result += ":" + opponentColor + writeFEN(opponentPieces);
		}
		return result;
		
	}

	/**
	 * Helper method generating a FEN notation from a vector containing pieces of the same color.
	 * @param pieces : A vector containing pieces of the same color. 
	 * @return A correctly formated String with the position of the pieces. 
	 */
	private static String writeFEN(Vector<Piece> pieces) {
		
		
		
		String result = "";
		
		String positions[] = new String[pieces.size()]; 
		for ( int i = 0 ; i < pieces.size() ; i++ ) {
			Piece piece = pieces.elementAt(i);
			positions[i] = piece.isKing() ? "K" : "";
			positions[i] += piece.squareLabel;
		}
		Arrays.sort(positions);
		
		separator sep = separator.COMMA;
		// Initiates position reading...
		result = positions[0];
		if ( positions.length > 1 ) {
			int previousPosition = -1;
			int currentPosition = -1;
			if ( notation.isNumeric(positions[0]) ) {
				previousPosition = Integer.parseInt(positions[0]); }
			for ( int i = 1 ; i < positions.length ; i++ ) {
				switch ( notation.natureOf(positions[i]) ) {
				case NUMERIC:
					currentPosition = Integer.parseInt(positions[i]);
					if ( currentPosition - previousPosition == 1 ) {
						previousPosition = currentPosition;
						sep = separator.HYPHEN;
						break;
					}
					previousPosition = currentPosition;
				case ALPHANUMERIC:
					switch ( sep ) {
					case HYPHEN:
						result += sep.toString() + previousPosition;
						sep = separator.COMMA;
					case COMMA:
						result += sep.toString() + positions[i];
						break;
					}					
				}	
			}
		}
		return result;
	}
	
}

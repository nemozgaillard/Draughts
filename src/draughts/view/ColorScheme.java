package draughts.view;

import java.awt.Color;

import draughts.model.GameType;

/**
 * part of the VIEW component in a MVC pattern for this application. <br>
 * {@code DisplayColors} holds the color scheme for board and pieces display: 
	 * <li>DARK_FIELD_COLOR
	 * <li>LIGHT_FIELD_COLOR
	 * <li>OPPONENT_PIECE_COLOR
	 * <li>PLAYER_PIECE_COLOR
	 * <li>HIGHLIGHT_COLOR
	 * <li>LABEL_COLOR</li>
	 */
public class ColorScheme {
	
	/**
	 * Color value for BLACK squares. 
	 */
	public Color DARK_FIELD_COLOR;
	/**
	 * Color value for WHITE squares. 
	 */
	public Color LIGHT_FIELD_COLOR;
	/**
	 * Color value for the OPPONENT pieces. 
	 */
	public Color OPPONENT_PIECE_COLOR;
	/**
	 * Color value for the (first) PLAYER pieces. 
	 */
	public Color PLAYER_PIECE_COLOR;
	/**
	 * Color value used for contrasting elements. <br>
	 */
	public Color HIGHLIGHT_COLOR;
	/**
	 * Color value for the square's notations. 
	 */
	public Color LABEL_COLOR;

	/**
	 * Constructor for  the color scheme for board and pieces display: 
	 * <li>DARK_FIELD_COLOR
	 * <li>LIGHT_FIELD_COLOR
	 * <li>OPPONENT_PIECE_COLOR
	 * <li>PLAYER_PIECE_COLOR
	 * <li>HIGHLIGHT_COLOR
	 * <li>LABEL_COLOR</li><br>
	 * 
	 * @param gameType : the color scheme changes with the type of game ({@code gameType}). 
	 */
	public ColorScheme(GameType gameType) {
	
		switch ( gameType ) {
		
		case ENGLISH:    case AMERICAN_POOL:
			DARK_FIELD_COLOR = new Color(0x32CD32); // LIMEGREEN
			LIGHT_FIELD_COLOR = new Color(0xFFFFE0); //  LIGHTYELLOW
			OPPONENT_PIECE_COLOR = Color.WHITE; // WHITE
			PLAYER_PIECE_COLOR = new Color(0xDC143C); // CRIMSON
			HIGHLIGHT_COLOR = new Color(0xCD32CD); //  LIMEGREEN COMPLEMENTARY
			LABEL_COLOR = Color.BLACK; // BLACK
			break;
			
/*
 * TODO Later: implement TURKISH draughts game
 * 
 * 		case TURKISH:  // TODO Later: implement TURKISH draughts game
			DARK_FIELD_COLOR = new Color(0XF5DEB3); // WHEAT
			LIGHT_FIELD_COLOR = new Color(0XF5DEB3); // WHEAT
			OPPONENT_PIECE_COLOR = new Color(0x696969); // DIMGREY
			PLAYER_PIECE_COLOR = Color.WHITE; // WHITE
			HIGHLIGHT_COLOR = new Color(0x3F87CD); // PERU COMPLEMENTARY 
			LABEL_COLOR = new Color(0X3F87CD); // CURIOUS BLUE
			break;
*/
			
		case THAI:
			DARK_FIELD_COLOR = new Color(0xCD853F); // PERU
			LIGHT_FIELD_COLOR = new Color(0XF5DEB3); // WHEAT
			OPPONENT_PIECE_COLOR = Color.WHITE; // WHITE
			PLAYER_PIECE_COLOR = new Color(0x696969); // DIMGREY
			HIGHLIGHT_COLOR = new Color(0x3F87CD); // PERU COMPLEMENTARY 
			LABEL_COLOR = new Color(0X3F87CD); // CURIOUS BLUE
			break;
		
		case INTERNATIONAL:    case ITALIAN:    case SPANISH:    case RUSSIAN:
		case BRAZILIAN:        case CANADIAN:   case PORTUGUESE: case CZECH:
		case FRISIAN:          case SPANTSIRETTI:
		default:
			DARK_FIELD_COLOR = new Color(0xCD853F); // PERU
			LIGHT_FIELD_COLOR = new Color(0XF5DEB3); // WHEAT
			OPPONENT_PIECE_COLOR = new Color(0x696969); // DIMGREY
			PLAYER_PIECE_COLOR = Color.WHITE; // WHITE
			HIGHLIGHT_COLOR = new Color(0x3F87CD); // PERU COMPLEMENTARY 
			LABEL_COLOR = new Color(0X3F87CD); // CURIOUS BLUE
			
		}
		
	}
	
}

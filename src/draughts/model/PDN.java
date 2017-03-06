package draughts.model;

import java.io.File;

/**
 *  part of the MODEL component in a MVC pattern for this application <p>
 *  {@code PDN} class - Manages PDN notation, which is the standard computer-processable format for recording draughts games.
 *  
 *  @param event : the name of the tournament or match event. <p>
 *  
 *  @param site : the location of the event. <br>
 *  This is in "City, Region COUNTRY" format, where COUNTRY is the 3-letter International Olympic Committee code for the country. <br>
 *  <i>An example is "New York City, NY USA". </i><p>
 *  
 *  @param date : the starting date of the game, in YYYY.MM.DD form. 
 *  <i>"??" are used for unknown values. </i>
 *  
 *  @param round : the playing round ordinal of the game. 
 *  @param white : the player of the White pieces, in "last name, first name" format. 
 *  @param black : the player of the Black pieces, same format as White. <p>
 *  
 *  @param result : the result of the game. <br>
 *  Possible values, depending on the type of result format : 
 *  <li>DEFAULT : "1-0" (white won), "0-1" (black won), "1/2-1/2" (draw), or "*" (other, e.g., the game is ongoing). 
 *  <li>INTERNATIONAL : "2-0" (white won), "0-2" (black won), "1-1" (draw), "0-0" (lost for both players), "*" (other, e.g., the game is ongoing). </li><p>
 *  
 *  @param GameType : the type of the draughts game. <br>
 *  Possible values: 
 *  <li>INTERNATIONAL (20), <li>ENGLISH (21), <li>ITALIAN (22), <li>AMERICAN_POOL (23), <li>SPANISH (24), <li>RUSSIAN (25), 
 *  <li>BRAZILIAN (26), <li>CANADIAN (27), <li>PORTUGUESE (28), <li>CZECH (29), <li>TURKISH (30), <li>THAI (31), 
 *  <li>FRISIAN (40), <li>SPANTSIRETTI (41). </li>
 *  <i>Default value = INTERNATIONAL (20). </i><p>
 *  
 *  @param FEN : the initial position of the draughts board. <br>
 *  <b>See</b> the {@code FEN} class for more information. <p>
 *  
 *  @param moveText : moveText contains the actual moves for the game. <br>
 *  Moves begin with the source square number, then a "-" or "x", finally destination square number. <br>
 *  Jumps must be specified by each square that would be jumped ("11x18x25"), or two squares only ("11x25").
 *  
 *  @see <a href="https://en.wikipedia.org/wiki/Portable_Draughts_Notation">Wikipedia: Portable Draughts Notation</a>
 *  @see <a href="http://pdn.fmjd.org/">pdn.fmjd.org: PDN 3.0 standard</a>
 */
public class PDN {

	/** 
	 * @param PDN_Tag (private) <p>
	 * The {@code enum Field} is used by the toString-helper method {@code formatPDN()} to output PDN variables in order. 
	 * moveText does not belong to the {@code enum Field}. It is formated separately, without enclosing brackets. 
	 */
	private enum PDN_Tag { 
		event("Event", "?"), 
		site("Site", "?"), 
		date("Date", "????.??.??"), 
		round("Round", "?"), 
		white("White", "?"), 
		black("Black", "?"), 
		gameType("GameType", GameType.INTERNATIONAL.toString()), 
		setup("Setup", ""), 
		fen("FEN", ""),
		result("Result", "*"); 
		
		private final String tag;
		private final String defaultValue;
		
		private PDN_Tag(String tag, String value) { 
			
			this.tag = tag; 
			this.defaultValue = value ; 
			
		}
		
		public String defaultValueOf() {
			return defaultValue;
		}
		
		@Override
		public String toString() { return tag; }
		
	}
	
	/**
	 * @param event : the name of the tournament or match event. <br>
	 * <i>Default value = "?". </i>
	 */
	public String event;
	/**
	 * @param site : the location of the event. <br>
	 * This is in "City, Region COUNTRY" format, where COUNTRY is the 3-letter International Olympic Committee code for the country. <br>
	 * An example is "New York City, NY USA". <br>
	 * <i>Default value = "?". </i>
	 */
	public String site;
	/**
	* @param date : the starting date of the game, in YYYY.MM.DD form. <br>
	* "??" are used for unknown values. <br>
	* <i>Default value = "????.??.??". </i>
	*/
	public String date;
	/**
	 * @param round : the playing round ordinal of the game. <br>
	 * <i>Default value = "?". </i>
	 */
	public String round;
	/**
	 * @param white : the player of the White pieces, in "last name, first name" format. <br>
	 * <i>Default value = "?". </i>
	 */
	public String white;
	/**
	 * @param black : the player of the Black pieces, same format as White. <br>
	 * <i>Default value = "?". </i>
	 */
	public String black;
	/**
	 * @param GameType : the type of the draughts game. <br>
	 *  Possible values: 
	 *  <li>INTERNATIONAL (20), <li>ENGLISH (21), <li>ITALIAN (22), <li>AMERICAN_POOL (23), <li>SPANISH (24), <li>RUSSIAN (25), 
	 *  <li>BRAZILIAN (26), <li>CANADIAN (27), <li>PORTUGUESE (28), <li>CZECH (29), <li>TURKISH (30), <li>THAI (31), 
	 *  <li>FRISIAN (40), <li>SPANTSIRETTI (41). <br>
	 *  <i>Default value = INTERNATIONAL (20). </i> <p>
	 */
	public GameType gameType;			
	/**
	 * @param setup : present if a setup (FEN field) is indicated. <br>
	 * <i>Default value = "". </i>
	 */
	public String setup;
	/**
	 * @param fen (optional) : the initial position on the checkers board. <br>
	 * <i>If absent, default position is loaded instead. </i>
	 * @see <a href="http://pdn.fmjd.org/fen.html">pdn.fmjd.org/fen.html</a>
	 */
	public String fen;
	/**
	 *  @param result : the result of the game. <br>
	 *  Possible values, depending on the type of result format : 
	 *  <li>DEFAULT : "1-0" (white won), "0-1" (black won), "1/2-1/2" (draw), or "*" (other, e.g., the game is ongoing). 
	 *  <li>INTERNATIONAL : "2-0" (white won), "0-2" (black won), "1-1" (draw), "0-0" (lost for both players), "*" (other, e.g., the game is ongoing).
	 */
	public String result;
	
	/**
	 * @param moveText : moveText contains the actual moves for the game. <br>
	 * Moves begin with the source square number, then a "-" or "x", finally destination square number. <br>
	 * Jumps must be specified by each square that would be jumped ("11x18x25"), or two squares only ("11x25"). <br>
	 * <i>Preferred notation is explicit, specifying all captured squares. </i>
	 */
	public String moveText;
	
	/**
	 * Default constructor for the PDN class. <br>
	 * <i>Default value for gameType is International Draughts. </i>
	 */
	public PDN() {
		this(GameType.INTERNATIONAL);
	}
	
	/**
	 * Minimal constructor for the PDN class. 
	 * @param gameType : the draughts game type. 
	 * @see {@link #gameType}
	 */
	public PDN(GameType gameType) {
		super();
		this.gameType = gameType;
		this.result = PDN_Tag.result.defaultValueOf();
	}
	
	/**
	 * Constructor for the PDN class - sets all fields to default values. 
	 * @param gameType : the draughts game type. 
	 * @see {@link #gameType}
	 */
	public PDN(GameType gameType, boolean setDefaultValues) {
		
		this(gameType);
		if ( setDefaultValues ) {
			for ( PDN_Tag field : PDN_Tag.values() ) {
				try {
					if ( field != PDN_Tag.gameType ) {
						this.getClass().getField(field.name()).set(this, field.defaultValueOf());;						
					}
				} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
					e.printStackTrace();
				}
			}
		this.gameType = gameType;
		}

	}
	
	/**
	 * Parses a PDN file into a PDN object. 
	 * @param file : file to parse
	 * @return a PDN object with read fields and corresponding values
	 */
	public static PDN parsePDNFile(File file) {
		// TODO implement the method
		return null;
	}
	
	   /**
	 * Parses a single PDN line or string. 
	 * @param line : a string containing the PDN field to be parsed. <br>
	 * Event, Site, Date, Round, White, Black, Result and FEN fields should be enclosed in brackets like this: <br>
	 * "[Site "Lyon, France, Europe, Earth, Solar System, Milky Way, Virgo Supercluster, Laniakea Supercluster, Observable Universe, Universe"]\r\n" <br>
	 * <i>MoveText is not enclosed into brackets [ ] or quotation marks " "</i>. 
	 * @return a PDN field with its value
	 */
	public static PDN parsePDNLine(String line) {
		// TODO implement the method
		return null;
	}
	

	/**
	 * Helper method returning the label of the corresponding square. 
	 * 
	 * @param row : the row number of the square. 
	 * @param col : the column number of the square. <p>
	 * @param pdnNotation <br>
	 * {@code pdnNotation} is the notation format to use for the labeling. It is composed of: <br>
	 * - a character indicating the notation type: 
	 * <li> A = alpha/numeric like chess,
	 * <li> N = numeric like draughts. <p>
	 * - followed by a number indicating the location of the first square (A1 or 1) from the perspective of the starting player: 
	 * <li> 0 = Bottom left
	 * <li> 1 = Bottom right
	 * <li> 2 = Top left
	 * <li> 3 = Top right 
	 */
	public static String getLabel(GameType gameType, int row, int col) {
		
		if ( gameType.hasAlphaNumericNotation() ) {
			return getAlphanumericLabel(gameType, row, col);
		}
		if ( gameType.hasNumericNotation() ) {
			return getNumericLabel(gameType, row, col);	
		}
		return null;
		
	}
	
	/**
	 * Helper method returning the (alphanumeric) label of the corresponding square. 
	 * 
	 * @param row : the row number of the square. 
	 * @param col : the column number of the square. 
	 * @param pdnNotation : the notation format to use for the labeling. 
	 * @return an alphanumeric label, like for chess. 
	 * The location of the first square (A1) from the perspective of the starting player is indicated by: 
	 * <li> 0 = Bottom left
	 * <li> 1 = Bottom right
	 * <li> 2 = Top left
	 * <li> 3 = Top right 
	 */	
	private static String getAlphanumericLabel(GameType gameType, int row, int col) {

		String str = null;
		// Determines letters according to columns
		switch ( gameType.getPDNNotation().substring(1, 2) ) {
		case ("0") : case ("2") : // FROM LEFT
			str = String.valueOf((char) (row + 97)); // (char) 65 == "a"
			break;
		case ("1") : case ("3") : // FROM RIGHT
			str = String.valueOf((char) ((gameType.getBoardWidth() - row) + 96)); // see above
			break;				
		}
		// Determines number according to rows
		switch ( gameType.getPDNNotation().substring(1, 2) ) {
		case ("0") : case ("1") : // FROM BOTTOM
			return str + (gameType.getBoardHeight() - col);
		case ("2") : case ("3") : // FROM TOP
			return str + (col + 1);
		}
		return null;
		
	}

	/**
	 * Helper method returning the (numeric) label of the corresponding square. <p>
	 * <b>Note : </b><br>
	 * <i>This assumes the square's colors are alternating... <br>
	 * and the game is played on squares of one color only... <br>
	 * on boards of even dimensions. <br></i>
	 * [Probably all draughts games with numeric notation]. 
	 *  
	 * @param row : the row number of the square. 
	 * @param col : the column number of the square. 
	 * @param pdnNotation : the notation format to use for the labeling. 
	 * @return a numeric label. 
	 * The location of the first square (1) from the perspective of the starting player is indicated by: 
	 * <li> 0 = Bottom left
	 * <li> 1 = Bottom right
	 * <li> 2 = Top left
	 * <li> 3 = Top right 
	 */
	private static String getNumericLabel(GameType gameType, int row, int col) {
		
		int label = 0;
		int width  = gameType.getBoardWidth();
		int height = gameType.getBoardHeight();
		switch ( gameType.getPDNNotation().substring(1, 2) ) {
		case ("0") : case ("2") : // FROM LEFT
			label = (int) (1 + (row / 2)) ; // label increments every other square...
			break;
		case ("1") : case ("3") : // FROM RIGHT
			label = (int) ( (width + 1 - row) / 2 ); // see above
			break;				
		}
		switch ( gameType.getPDNNotation().substring(1, 2) ) {
		case ("0") : case ("1") : // FROM BOTTOM
			return "" + (label + ((height - (col + 1)) * width / 2));
		case ("2") : case ("3") : // FROM TOP
			return "" + (label + (col * width /2)); //
		}
		return null;
		
	}

	public void addMove(Move move) {
		moveText += move.toString();
	}
	
	/**
	 * This game formatted as a valid PDN string. 
	 * @return A formatted PDN string describing this draughts game. 
	 * <i>Comments are currently not treated. </i>
	 */
	@Override
	public String toString() {
		
		String formatedPDN = "";
		
		for ( PDN_Tag field : PDN_Tag.values() ) {
			formatedPDN += formatPDN(field);
		}
		
		if ( !isNullEmptyOrBlank(moveText) ) {
			formatedPDN += moveText;
		}
		
		formatedPDN += " " + result + "\r\n";
		
		return formatedPDN;
		
	}
	
	/**
	 * Helper method formatting a PDN field into the corresponding PDN format. 
	 * 
	 * @param field : from {@code enum Field} containing the (ordered) tags used by the PDN notation. <p>
	 * 
	 * @return A correctly formatted PDN line. <p>
	 * For example, formats the field black of value "Fisher, Robert James" into [Black "Fisher, Robert James"]. <p>
	 * <i>Comments are not implemented at this time, but they should (will) appear enclosed into curly brackets: <p>
	 * {... actually, wasn't Bobby Fisher a chess player ?}</i>
	 */
	private String formatPDN(PDN_Tag field) {
		
		String str = "";
		String value = "";
		
		try {
			if ( this.getClass().getField(field.name()).get(this) != null ) {
				value = this.getClass().getField(field.name()).get(this).toString();
			}
			if (! isNullEmptyOrBlank(value)) {
				// Formats name = "Black", value = "Georgiev, Alexander" into [Black "Georgiev, Alexander"]\r\n"]
				str = "[" + PDN_Tag.valueOf(field.name()) + " \"" + value.trim() + "\"]\r\n";
			}
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) { }
		return str;
	
	}

	/**
	 * Helper method checking if a String is null OR empty ("") OR blank (for example : "   ")
	 * @param str : the string to check. 
	 * @return a boolean. <p>
	 * <b><i style="color:#800080">true</i></b> if {@code str} is (null OR empty OR blank) - 
	 * <b><i style="color:#800080">false</i></b> otherwise. 
	 */
	private static boolean isNullEmptyOrBlank(String str) {
		return (str == null || str.trim().isEmpty());
	}
	
}

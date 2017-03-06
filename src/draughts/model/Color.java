package draughts.model;

/**
 * In {@code BLACK} and {@code WHITE}: 
 * Squares and pieces colors, as simple as {@code BLACK} and {@code WHITE}. <br>
 * @see <a href="http://idioms.thefreedictionary.com/black+and+white">idioms.thefreedictionary.com: black and white</a>
 *
 */
public enum Color { 
	
	BLACK("B") , WHITE("W");
	
	private final String colorCode;
	private Color(final String str) { this.colorCode = str; }
	
	public Color getOppositeColor() {
		return ( this.colorCode == "W" ) ? Color.BLACK : Color.WHITE;
	}
	
	public static Color valueOfString(String colorCode) {
		
		for ( Color color : values()) {
			if ( color.colorCode.equals(colorCode) ) {
				return color;
			}
		}
		return null;
		
	}
	
	/**
	* @return one-letter encoded color, either "B" or "W". 
	**/
	@Override
	public String toString() { return colorCode; }
	
}

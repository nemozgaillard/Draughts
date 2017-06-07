package draughts.model;

import java.awt.Dimension;

/**
 * type : the type-number coding for the draughts game type. <p>
 * 
 * A valid GameType corresponding to the {@code type} encoding for type of draughts game: <br>
 * - INTERNATIONAL (20) <br>
 * - ENGLISH (21) <br>
 * - ITALIAN (22) <br>
 * - AMERICAN_POOL (23) <br>
 * - SPANISH (24) <br>
 * - RUSSIAN (25) <br>
 * - BRAZILIAN (26) <br>
 * - CANADIAN (27) <br>
 * - PORTUGUESE (28) <br>
 * - CZECH (29) <br>
 * - TURKISH (30) <br>
 * - THAI (31) <br>
 * - FRISIAN (40) <br>
 * - SPANTSIRETTI (41). <p>
 * 
 * <i>Default return value is INTERNATIONAL (20). </i>
 * 
 * @see <a href="http://pdn.fmjd.org/gametype.html#gametype-section">http://pdn.fmjd.org - gametype-section</a>
 */
public enum GameType {
    
    INTERNATIONAL (20, "International draughts", "W", 10, 10, "N2", 0, "International", "x"),
    ENGLISH (21, "English draughts", "B", 8, 8, "N1", 0, "Default", "x"),
    ITALIAN (22, "Italian draughts", "W", 8, 8, "N2", 1, "Default", "x"),
    AMERICAN_POOL (23, "American pool checkers", "B", 8, 8, "N1", 0, "Default", "x"),
    SPANISH (24, "Spanish draughts", "W", 8, 8, "N1", 1, "Default", "x"),
    RUSSIAN (25, "Russian draughts", "W", 8, 8, "A0", 0, "Default", ":"),
    BRAZILIAN (26, "Brazilian draughts", "W", 8, 8, "A0", 0, "Default", "x"),
    CANADIAN (27, "Canadian draughts", "W", 12, 12, "N2", 0, "International", "x"),
    PORTUGUESE (28, "Portuguese draughts", "W", 8, 8, "N1", 1, "Default", "x"),
    CZECH (29, "Czech draughts", "W", 8, 8, "A0", 0, "Default", "x"),
    // TURKISH (30, "Turkish draughts", "W", 8, 8, "A0", 0, "Default", "x"), // TODO Later: implement TURKISH draughts game
    THAI (31, "Thai draughts", "B", 8, 8, "N2", 0, "Default", "-"),
    FRISIAN (40, "Frisian draughts", "W", 10, 10, "N2", 0, "Default", "x"),
    SPANTSIRETTI (41, "Spantsiretti draughts", "W", 10, 8, "A0", 0, "Default", ":");
    
    private final int number;
    private final String designation;
    private final String firstPlayer;
    private final int width;
    private final int height;
    /**
     * @param notation : <br>
     * A character indicating the notation type: 
     * -  A = alpha/numeric like chess, <br>
     * -  N = numeric like draughts. <p>
     * followed by a number indicating the location of the first square (A1 or 1) from the perspective of the starting player: 
     * -  0 = Bottom left <br>
     * -  1 = Bottom right <br>
     * -  2 = Top left <br>
     * -  3 = Top right <br>
     */
    private final String notation;
    private final int flag;
    private final String resultType;
    private final String separator;
    
    GameType(int number, String designation, String firstPlayer, int width, int height, 
            String notation, int flag, String resultType, String separator) {
        
        this.number = number;
        this.designation = designation;
        this.firstPlayer = firstPlayer;
        this.width = width;
        this.height = height;
        this.notation = notation;
        this.flag = flag;
        this.resultType = resultType;
        this.separator= separator;
        
    }
    
    public int getNumber() { return number; }
    public String getDesignation() { return designation; }
    public String getDesignationAndNumber() { return designation + " (" + number + ")"; }
    public String getFirstPlayerColor() { return firstPlayer; }
    public int getBoardWidth() { return width; }
    public int getBoardHeight() { return height; }
    public Dimension getBoardDimension() { return new Dimension(width, height); }
    public String getPDNNotation() { return notation; }
    public int getInvertFlag() { return flag; }
    public String getResultType() { return resultType; }
    public String getSeparator() { return separator; }

    /**
     * @param type : the type-number coding for 
     * @return A valid GameType corresponding to the {@code type} encoding for type of draughts game: <br>
     * - INTERNATIONAL (20) <br>
     * - ENGLISH (21) <br>
     * - ITALIAN (22) <br>
     * - AMERICAN_POOL (23) <br>
     * - SPANISH (24) <br>
     * - RUSSIAN (25) <br>
     * - BRAZILIAN (26) <br>
     * - CANADIAN (27) <br>
     * - PORTUGUESE (28) <br>
     * - CZECH (29) <br>
     * - TURKISH (30) <br>
     * - THAI (31) <br>
     * - FRISIAN (40) <br>
     * - SPANTSIRETTI (41). <p>
     * <i>Default return value is INTERNATIONAL (20). </i>
     * @see <a href="http://pdn.fmjd.org/gametype.html#gametype-section">http://pdn.fmjd.org - gametype-section</a>
     */
    public static GameType gameTypeForNumber(int type) {
        GameType gameType = INTERNATIONAL;
        for (GameType gt : GameType.values() ) {
            if ( gt.number == type ) {
                gameType = gt;
            }
        }
        return gameType;
    }
    
    public boolean hasAlphaNumericNotation () {
        return this.notation.substring(0, 1).matches("A");
    }
    
    public boolean hasNumericNotation () {
        return this.notation.substring(0, 1).matches("N");
    }
    
    @Override
    public String toString() {
        return number + ", " + firstPlayer + ", " + width + ", " + height + ", " + notation + ", " + flag + ", " + resultType + ", " + separator ;
    }
}

package draughts.model;

/**
 * part of the MODEL component in a MVC pattern for the draughts application. <p>
 * 
 * CaptureSeparator enumerates characters used for capture notation according to PDN standards. <br>
 * @see <a href="http://pdn.fmjd.org/gametype.html#gametype-section">pdn.fmjd.org/gametype.html - Capture separator</a>
 */
public enum CaptureSeparator {
    
    DEFAULT("x"), COLON(":"), DASH("-");
    
    private final String separator;
    
    private CaptureSeparator (final String separator) { this.separator = separator; }
    
    @Override
    public String toString() { return separator; }
}

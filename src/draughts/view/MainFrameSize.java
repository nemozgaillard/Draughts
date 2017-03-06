package draughts.view;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Enum used to set the size of the frame relatively to the screen size. 
 * 
 */
public enum MainFrameSize {
	
	MINIMAL(0.0), 
	HALF(.5), 
	NORMAL(.75), 
	FULL(1.0);

	private static final Dimension MINIMUM_SIZE_FRAME = new Dimension(500, 310);
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	
	private final double ratio;
	
	private MainFrameSize(double ratio) { 
		this.ratio = ratio;
	}
		
	public static Dimension getMinimumFrameSize() {
		return MINIMUM_SIZE_FRAME;
	}
	
	public double getRatio() { return ratio; }
	
	public FrameSettings getSettings() {
		return new FrameSettings(this.ratio);
	}
	

	public class FrameSettings {
		
		public Dimension dimension;
		public int x;
		public int y;
		
		private FrameSettings(double ratio) {
			
		    int screenWidth = SCREEN_SIZE.width;
		    int screenHeight = SCREEN_SIZE.height;
		    
		    dimension = new Dimension(
		    		Math.max(MINIMUM_SIZE_FRAME.width, (int) (screenWidth * ratio)), 
		    		Math.max(MINIMUM_SIZE_FRAME.height, (int) (screenHeight * ratio))
		    		);
		    
			x = (int) ((screenWidth - dimension.width) / 2);
			y = (int) ((screenHeight - dimension.height) / 2);
			
		}
		
	}
	
}

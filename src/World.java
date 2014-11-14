import org.newdawn.slick.Image;

public class World {
	
	/**
	 * This class is meant to hold resources that worlds will need in our game.
	 * Note: this class does not hold what frame of reference the map is, this
	 * is instead in the play game state.
	 * 
	 * @author Jesse Deppisch
	 */
	
	// TODO maybe have image position contained here
	
	private Image foreground;
	private Image background;
	
	public World(Image background, Image foreground) {
		this.foreground = foreground;
		this.background = background;
	}
	
	public Image getForeground() {
		return foreground;
	}
	
	public Image getBackground() {
		return background;
	}
	

}

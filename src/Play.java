import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Play extends BasicGameState{
	
	/**
	 * A test of the game that has one player on the map, to demonstrate a static version of the game,
	 * and also used to test movement concepts for the game.
	 * 
	 * @author Jesse Deppisch
	 */
	
	public static final int ID = 3;
	private StateBasedGame game;
	private TrueTypeFont largeFont;
	private TrueTypeFont smallFont;
	
	private Image foreground;
	private Image background;
	private Image stickFigure;
	
	private float foregroundPosition; // X coordinate of the image, used for drawing
	private float backgroundPosition;
	private float playerPosition; // X coordinate of the stick figure
	
	private boolean leftMovement; // Used because there is no keyHeld method
	private boolean rightMovement;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		
		largeFont = new TrueTypeFont(new java.awt.Font("Verdana", Font.PLAIN, 20), false);
		smallFont = new TrueTypeFont(new java.awt.Font("Verdana", Font.PLAIN, 12), false);
		
		background = new Image("res/Clouds.png");
		foreground = new Image("res/Game Background.png");
		stickFigure = new Image("res/stickfigure.png");
		
		foregroundPosition = -500;
		backgroundPosition = -500;
		playerPosition = 450; 
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		g.drawImage(background, backgroundPosition, 0);
		g.drawImage(foreground, foregroundPosition, 0);
		g.drawImage(stickFigure, playerPosition, 300);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		// If the character isn't too close to the border and wants to move
		if (playerPosition > 10 && leftMovement) {
			playerPosition -= 1;
		} else if (playerPosition < 840 && rightMovement) {
			playerPosition += 1;
		}
		
		// If the character is close to the border and wants to move and the map will allow it
		if (playerPosition <= 10 && leftMovement && foregroundPosition < 0) {
			foregroundPosition += 1;
			backgroundPosition += .3f;
			
		} else if (playerPosition >= 840 && rightMovement && foregroundPosition > -1000) {
			foregroundPosition -= 1;
			backgroundPosition -= .3f;
		}
		
	}

	@Override
	public int getID() {
		return ID;
	}
	
	public void keyPressed(int key, char c) {
		
		if (key == 203) { // If the key pressed is the left arrow key
			leftMovement = true;
		}
		if (key == 205) { // If the key pressed is the right arrow key
			rightMovement = true;
		}
		
		// For testing purposes
		System.out.println("Key: " + key);
		System.out.println("Char: " + c);
	}
	
	public void keyReleased(int key, char c) {
		
		if (key == 203) { // If the key released is the left arrow key
			leftMovement = false;
		}
		
		if (key == 205) { // If the key released is the right arrow key
			rightMovement = false;
		}
	}
	
	

}

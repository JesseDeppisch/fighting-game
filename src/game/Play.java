package game;
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
	 * @author JesseDeppisch
	 */
	
	// StateBasedGame declarations
	public static final int ID = 3;
	private StateBasedGame game;
	
	// Map declarations
	private static Image foreground;
	private static Image background;
	private static Image stickFigure;
	
	// Positioning declarations
	private static float foregroundPosition; // X coordinate of the image, used for drawing
	private static float backgroundPosition;
	private static float playerPosition; // X coordinate of the stick figure
	
	private static boolean leftMovement; // Used because there is no keyHeld method
	private static boolean rightMovement;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		
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
	
	/**
	 * actionListener for all key presses (part of game loop - doesn't need to be executed)
	 * 
	 * @param key value of key pressed
	 * @param c value of key pressed 
	 */
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
	
	/**
	 * actionListener for all key releases (part of game loop - doesn't need to be executed)
	 * 
	 * @param key value of key released
	 * @param c value of key released
	 */
	public void keyReleased(int key, char c) {
		
		if (key == 203) { // If the key released is the left arrow key
			leftMovement = false;
		}
		
		if (key == 205) { // If the key released is the right arrow key
			rightMovement = false;
		}
	}
	
	

}

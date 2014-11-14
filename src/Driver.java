import java.awt.Menu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Driver extends StateBasedGame {
	
	/**
	 * This class drives the entire game - it's what happens when the user launches
	 * the game. This includes the main method, and handles states of the games.
	 * Note that this is NOT the main menu!
	 * 
	 * @author Jesse Deppisch
	 */
	
	// Constructor handles the superclass' constructor, which controls the title name of the window.
	public Driver() {
		super("Fighting Game ");
	}

	// Main method
	public static void main(String[] args) {
		/*
		 * Attempts to create a new AppGameContainer with the size 1000 * 625
		 * Using the Driver class as the game.
		 * The false parameter specifies that the game is not full screen.
		 */
		
		try {
			AppGameContainer container = new AppGameContainer(new Driver());
			container.setDisplayMode(1000,625,false);
			container.setVSync(true);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

	// List of all the game states. Note that the first one on this list will be executed first,
	// and thus the first state on this screen should ALWAYS be the splash screen.
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new SplashScreen());
		addState(new MainMenu());
		addState(new OptionMenu());
		addState(new Play());
		addState(new ActualPlay());
	}

	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
	}

	
}

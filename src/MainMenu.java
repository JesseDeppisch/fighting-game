import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.renderer.QuadBasedLineStripRenderer;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.CrossStateTransition;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenu extends BasicGameState{
	
	/**
	 * The game state of the main menu for the game.
	 * 
	 * @author Jesse Deppisch
	 */
	
	// ID number of the state
	public static final int ID = 1;
	// The game holding this state
	private static StateBasedGame game;
	// The font
	private static TrueTypeFont largeFont;
	private static TrueTypeFont smallFont;

	// Initializes the state, and loads any resources needed
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		largeFont = new TrueTypeFont(new java.awt.Font("Verdana", Font.PLAIN, 20), false);
		smallFont = new TrueTypeFont(new java.awt.Font("Verdana", Font.PLAIN, 12), false);
	}

	// Renders the game
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		container.getGraphics().setBackground(new Color(0.4f,0.6f,0.6f));
		
		// g.setFont(font);
		g.setColor(Color.white);
		g.setFont(largeFont);
		g.drawString("Main Menu", 450, 60);
		
		g.setFont(smallFont);
		
		g.drawString("Options", 100, 200);
		g.drawRect(95, 200, 60, 20);
		
		g.drawString("Play (1P Test)", 100, 250);
		g.drawRect(95, 250, 100, 20);
		
		g.drawString("Play (2P Test)", 100, 300);
		g.drawRect(95, 300, 100, 20);
		
		// Test later line directly below (for buttons)
		QuadBasedLineStripRenderer buttonTest = new QuadBasedLineStripRenderer();
		
		
		/** Only for testing purposes, remove later */
		int xPos = Mouse.getX();
		int yPos = 625 - Mouse.getY();
		g.drawString("X: " + xPos + " Y: " + yPos, 20, 40);
		
		
		
	}

	// Updates the state's logic (can be based on the amount of time that's passed)
	public void update(GameContainer conmtainer, StateBasedGame game, int delta) throws SlickException {
		//TODO - Not done so far - may not be needed
	}

	// Returns the ID of the BasicGameState
	public int getID() {
		return ID;
	}
	
	// When a mouse button is pressed
	public void mousePressed(int button, int x, int y) {
		
		// Looking to see if the person clicked in the "Options" rectangle
		if ((x > 95 && x < 155) && (y > 200 && y < 240)) {
			game.enterState(OptionMenu.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
			
		// Looking to see if the person clicked in the "Play (1P Test)" rectangle
		if ((x > 95 && x < 195) && (y > 250 && y < 270)) {
			game.enterState(Play.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
		
		// Looking to see if the person clicked in the "Play (2P Test)" rectangle
		if ((x > 95 && x < 195) && (y > 300 && y < 320)) {
			game.enterState(ActualPlay.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
	
	}
	
}

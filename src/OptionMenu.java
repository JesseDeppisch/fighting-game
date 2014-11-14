import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.particles.effects.FireEmitter;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class OptionMenu extends BasicGameState {
	
	/**
	 * A test for an options menu, which we will have in our game.
	 * 
	 * @author Jesse Deppisch
	 */
	
	public static final int ID = 2;
	
	private static StateBasedGame game;
	private static TrueTypeFont largeFont;
	private static TrueTypeFont smallFont;
	
	
	
	FireEmitter fireTest = new FireEmitter(200, 200);

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		largeFont = new TrueTypeFont(new java.awt.Font("Verdana", Font.PLAIN, 20), false);
		smallFont = new TrueTypeFont(new java.awt.Font("Verdana", Font.PLAIN, 12), false);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// Sets the background color
		container.getGraphics().setBackground(new Color(0.6f,0.2f,0.2f)); 
		
		g.setColor(Color.white);
		g.setFont(largeFont);
		g.drawString("Options", 450, 60);
		
		g.setFont(smallFont);
		g.drawString("Back to main menu", 100, 100);
		g.drawRect(95, 100, 130, 20);
		
		// Color Schemes
		g.setFont(largeFont);
		g.drawString("Color Schemes", 425, 150);
		
		// Color scheme options
		g.setLineWidth(2);
		
		g.setColor(Color.white);
		g.fillRect(475, 180, 50, 20);
		g.setColor(Color.black);
		g.drawRect(475, 180, 50, 20);
		
		g.setColor(Color.yellow);
		g.fillRect(475, 210, 50, 20);
		g.setColor(Color.cyan);
		g.drawRect(475, 210, 50, 20);
		
	}

	// Updates the state's logic (can be based on the amount of time that's passed) 
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO
	}

	// Returns the ID of the state
	public int getID() {
		return ID;
	}
	
	// WHenever the mouse button is pressed
	public void mousePressed(int button, int x, int y) {
		if ((x > 95 && x < 225) && (y > 100 && y < 120)) {
			game.enterState(MainMenu.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
		
		if ((x > 475 && x < 525) && (y > 180 && y < 200)) {
			ActualPlay.setColors(Color.white, Color.black);
		}
		
		if ((x > 475 && x < 525) && (y > 210 && y < 230)) {
			ActualPlay.setColors(Color.yellow, Color.cyan);
		}
	}
	

}

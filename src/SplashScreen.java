import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class SplashScreen extends BasicGameState {
	
	public static final int ID = 0;
	private static StateBasedGame game;
	
	private static Image splashScreen;

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		splashScreen = new Image("res/splashscreen.png"); // placeholder until we name the game
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(splashScreen, 0, 0);
	}

	// Only meant to run once, since this is the splash screen
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		game.enterState(MainMenu.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	}

	public int getID() {
		return 0;
	}

	

}

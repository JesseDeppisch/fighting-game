
import java.awt.Font;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.MaskUtil;


public class ActualPlay extends BasicGameState{
	
	/**
	 * A test of the game that includes 2 players on the map, and unlike
	 * the Play class, is built using objects.
	 * 
	 * @author Jesse
	 */
	
	private Animation idleAnimation;
	private Animation animationToReturn;
	
	public static final int ID = 4;          // Used to identify which game state this is
	private static StateBasedGame game;      // the game that the game is running in (ignore, but this is necessary)
	
	private Fighter player1;          // player 1
	private Fighter player2;          // player 2
	
	private World world;              // the map
	
	private Image p1;                 // ONLY IN TESTING!! (player identifier)
	private Image p2;                 // ONLY IN TESTING!! (player identifier)
	
	private float foregroundPosition; // X coordinate of the foreground
	private float backgroundPosition; // X coordinate of the background
	 
	private float fms;                       // fighter movement speed
	private float fgms;               // foreground movement speed
	// note that the foreground movement speed and the fighter movement speed MUST BE THE SAME, and thus are the same
	private float bgms;               // background movement speed
	
	// HUD Variables
	
	private String colorScheme;       // Color scheme to be used for the HUD
	
	private static Color color1;             // Filler Color
	private static Color color2;             // Outline Color
	
	private static Polygon bigLeft;          // Big left polygon
	private static Polygon bigRight;         // Big right polygon
	
	private static Polygon smallLeft;        // Small left polygon
	private static Polygon smallRight;       // Small right polygon
	

	private boolean haveDefined;             // Has the pause Mask been defined yet
	private boolean gamePaused;              // Is the game paused
	
	// TODO
	
	private float gravity;         
	private float jumpVelocity;
	

	/**************************************************** 
	 * Game Initialization Method - Load Resources Here *
	 ****************************************************/
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		haveDefined = false;
		
		gravity = 2.5f; // TODO eventually change this to pull from option menu
		jumpVelocity = 40f; // eventually change this to pull from the option menu (maybe!!)
		
		
		// TODO - add more spritesheets here
		SpriteSheet sheet = new SpriteSheet("res/idle.png", 150, 250);
		
		idleAnimation = new Animation(false);
		for (int y = 0; y < 2; y ++) {
			for (int x = 0; x < 6; x++) {
				idleAnimation.addFrame(sheet.getSprite(x,y), 150);
			}
		}
		//idleAnimation.addFrame(sheet.getSprite(2,0), 150); // Adding on the remaining last 3
		//idleAnimation.addFrame(sheet.getSprite(2,1), 150);
		//idleAnimation.addFrame(sheet.getSprite(2,2), 150);
		
		// Creating the world
		world = new World(new Image("res/Clouds.png"), new Image("res/Game Background.png"));
		
		// Creating the fighters
		player1 = new Fighter(new Image("res/stickFigure.png"), 100, 250);
		player2 = new Fighter(new Image("res/stickFigure.png"), 750, 250);
		
		// Movement speed
		fms = 5; // subject to change
		fgms = fms;
		bgms = fms * .2f;
		
		// ONLY IN TESTING (loading player identifiers)
		p1 = new Image("res/1P.png");
		p2 = new Image("res/2P.png");
		
		// Setting the starting positions of the map (DO NOT CHANGE!!)
		foregroundPosition = -500;
		backgroundPosition = -500;
		
		// Fail-safe for color schemes (if no color scheme specified, use black and white default)
		if (color1 == null || color2 == null) {
			color1 = Color.white;
			color2 = Color.black;
		}
		
		//
		// Setting the points for the polygons (for HUD)
		//
		
		// Left big polygon
		bigLeft = new Polygon();
		bigLeft.addPoint(40, 10);
		bigLeft.addPoint(400, 10);
		bigLeft.addPoint(380, 100);
		bigLeft.addPoint(20, 100);
		
		// Right big polygon
		bigRight = new Polygon();
		bigRight.addPoint(960, 10);
		bigRight.addPoint(600, 10);
		bigRight.addPoint(620, 100);
		bigRight.addPoint(980, 100);
		
		// Small left polygon
		smallLeft = new Polygon();
		smallLeft.addPoint(30, 55);
		smallLeft.addPoint(340, 55);
		smallLeft.addPoint(330, 100);
		smallLeft.addPoint(20, 100);
		
		// Small right polygon
		smallRight = new Polygon();
		smallRight.addPoint(970, 55);
		smallRight.addPoint(660, 55);
		smallRight.addPoint(670, 100);
		smallRight.addPoint(980, 100);
		
		// TODO - Add rest of HUD
	}

	/*******************************************
	 * Rendering Method - All Graphics Go Here *
	 *******************************************/
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setAntiAlias(true);
		
		// Drawing the map (background + foreground)
		g.drawImage(world.getBackground(), backgroundPosition, 0);
		g.drawImage(world.getForeground(), foregroundPosition, 0);
		
		
		// Drawing the fighters
		// first parameter used to be player1.getImage()
		g.drawImage(whichAnimation(player1).getCurrentFrame(), player1.getPosition().getX(), player1.getPosition().getY());
		g.drawImage(whichAnimation(player2).getCurrentFrame(), player2.getPosition().getX(), player2.getPosition().getY());
	
		drawHUD(g); // Draws the HUD
		
		// Drawing identifiers for the fighters (ONLY IN TESTING, to tell which is which, because same fighter image is used for both)
		g.drawImage(p1, player1.getPosition().getX() + 50, 200);
		g.drawImage(p2, player2.getPosition().getX() + 50, 200);
		
		// For testing sprite sheets
		//idleAnimation.draw(100,100);
		
		if (gamePaused) {                           // If the game is paused
			if (!haveDefined) {                     // If the Mask hasn't been defined yet (first pause)
				MaskUtil.defineMask();              // Start defining the Mask
				g.fillRect(0, 0, 1000f, 625f);      // (this defines the Mask, it doesn't actually draw this)
				MaskUtil.finishDefineMask();        // Stop defining the Mask
				haveDefined = true;                 // Change haveDefined to true, since we've now defined the Mask
			}
			
			MaskUtil.drawOnMask();                  // Start drawing on the Mask
			
			g.setColor(Color.cyan);                 // Set the color for the background
			g.fillRect(250, 156.25f, 500f, 312.5f); // Draw the background
			g.setColor(Color.black);                // Set the color for the outline
			g.drawRect(250, 156.25f, 500f, 312.5f); // Draw the outline
			
			g.drawString("To main menu", 300, 200);
			g.drawRect(295, 195, 120, 25);

			MaskUtil.drawOffMask();                 // Stop drawing on the Mask
			MaskUtil.resetMask();                   // Reset the mask (to clear it after rendering it)
			
		}
	}
	

	/**************************************
	 * Game Loop Method - Logic Goes Here *
	 **************************************/
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		if (!gamePaused) {
		// Moving the fighters
		fall(player1);
		fall(player2);
		updateXPositions(); 

		// Updating the collision boxes
		player1.updateAABB();
		player2.updateAABB();
		
		// updating logic for movement
		if (AABB.collides(player1.getCollisionBox(), player2.getCollisionBox())) { // If the collision boxes of the fighters are colliding
			if (player1.getPosition().getX() < player2.getPosition().getX()) {     // If player1 is to the left of player2
				player1.setRightMovementRestricted(true);
				player2.setLeftMovementRestricted(true);
			} else {
				player1.setLeftMovementRestricted(true);
				player2.setRightMovementRestricted(true);                          // If player2 is to the right of player2
			}
		} else {
			player1.setLeftMovementRestricted(false);
			player1.setRightMovementRestricted(false);
			player2.setLeftMovementRestricted(false);
			player2.setRightMovementRestricted(false);
		}
		
		// TODO finish implementation of animation logic
		
		// Animation Logic
		if (player1.getLastAction().equals(player1.getCurrentAction())) { // If player1 is continuing their action
			whichAnimation(player1).update(delta);                        // update their animation
		} else {                                                          // But if not
			lastAnimation(player1).restart();                             // restart their last animation
		}                                                                
		
		if (player2.getLastAction().equals(player2.getCurrentAction())) { // If player2 is continuing their action
			whichAnimation(player2).update(delta);                        // Update their animation
		} else {                                                          // But if not
			lastAnimation(player2).restart();                             // restart their last animation
		}
		
		
		// Updates the animation actions
		player1.setLastAction(player1.getCurrentAction());
		player2.setLastAction(player2.getCurrentAction());
		
		} else { // If the game is paused
			
		}
		
	}

	/************************************
	 * Returns the ID of the game state *
	 ************************************/
	public int getID() {
		return ID;
	}
	
	/************************************************
	 * Method to get what animation should be drawn *
	 * @author Jesse                                *
	 ************************************************/
	public Animation whichAnimation(Fighter fighter) {
		
		switch (fighter.getCurrentAction()) {
			// case "idle":
			//	animationToReturn = idleAnimation;
			//	break;
			// will eventually uncomment that code when are gotten
			default: // so, everything defaults to the idle animation
				animationToReturn = idleAnimation;
				break;
		}
		
		return animationToReturn;
	}
	
	/************************************************
	 * Method to get what animation should be reset *
	 * @author Jesse                                *
	 ************************************************/
	public Animation lastAnimation(Fighter fighter) {
		
		switch (fighter.getLastAction()) {
			// case "idle":
			// animationToReturn = idleAnimation;
			// break;
			// will eventually uncomment that code when are gotten
			default: // so, everything defaults to the idle animation
				animationToReturn = idleAnimation;
				break;
		}
		
		return animationToReturn;
	}
	
	/*****************************************
	 * Method to make things fall            *
	 * @author Jesse                         *
	 * @param fighter fighter that will fall *
	 *****************************************/
	public void fall(Fighter fighter) {
		
		// Update the velocity of the fighter
		fighter.setyVelocity(fighter.getyVelocity() - gravity);
		
		// Actual falling logic
		if (fighter.getPosition().getY() - fighter.getyVelocity() >= 250) {       // If, when the fighter were to fall, it would surpass the ground level
			fighter.setyVelocity(0);                                              // Set the velocity of the fighter to 0
			fighter.setPosition(new Vector2f(fighter.getPosition().getX(), 250)); // Set the position of the fighter to the ground level
		} else {                                                                  // but, if not
			// Update the position of the fighter to be where it should be, according to velocity
			fighter.setPosition(new Vector2f(fighter.getPosition().getX(), fighter.getPosition().getY() - fighter.getyVelocity()));
		}
	}
	
	
	/***********************************************
	 * Position Update - Left/Right Movement Logic *
	 * @author Jesse                               *
	 ***********************************************/
	public void updateXPositions() {
		
		// Note from Jesse - if you guys want, I'll comment this code, but if you ask me, I'll explain it (maybe)
		
		// Basic Movement - Happens when players are moving left/right without going near the border
		
		if (player1.getPosition().getX() > 10 && player1.getIntent("left") && player1.isLeftMovementRestricted() == false) {
			player1.moveLeft(fms);
			player1.setCurrentAction("moving left");
		} else if (player1.getPosition().getX() < 840 && player1.getIntent("right") && player1.isRightMovementRestricted() == false) {
			player1.moveRight(fms);
			player1.setCurrentAction("moving right");
		}
				
		if (player2.getPosition().getX() > 10 && player2.getIntent("left") && player2.isLeftMovementRestricted() == false) {
			player2.moveLeft(fms);
			player2.setCurrentAction("moving left");
		} else if (player2.getPosition().getX() < 840 && player2.getIntent("right") && player2.isRightMovementRestricted() == false) {
			player2.moveRight(fms);
			player2.setCurrentAction("moving right");
		}
				
		// Map-Moving Movement of Player 1 - Happens when player 1 moves left/right, going near the edge
				
		if (player1.getPosition().getX() <= 10 && player1.getIntent("left") && foregroundPosition < 0 && player2.getPosition().getX() < 840 && player1.isLeftMovementRestricted() == false) {
			foregroundPosition += fgms;
			backgroundPosition += bgms;
					
			player2.moveRight(fms);
			player1.setCurrentAction("moving left");
					
		} else if (player1.getPosition().getX() >= 840 && player1.getIntent("right") && foregroundPosition > -1000 && player2.getPosition().getX() > 10 && player1.isRightMovementRestricted() == false) {
			foregroundPosition -= fgms;
			backgroundPosition -= bgms;
					
			player2.moveLeft(fms);
			player1.setCurrentAction("moving right");
		}
				
		// Map-Moving Movement of Player 2 - Happens when player 2 moves left/right, going near the edge
				
		if (player2.getPosition().getX() <= 10 && player2.getIntent("left") && foregroundPosition < 0 && player1.getPosition().getX() < 840 && player1.isLeftMovementRestricted() == false) {
			foregroundPosition += fgms;
			backgroundPosition += bgms;
					
			player1.moveRight(fms);
			player2.setCurrentAction("moving left");
					
		} else if (player2.getPosition().getX() >= 840 && player2.getIntent("right") && foregroundPosition > -1000 && player1.getPosition().getX() > 10 && player1.isRightMovementRestricted() == false) {
			foregroundPosition -= fgms;
			backgroundPosition -= bgms;
					
			player1.moveLeft(fms);
			player2.setCurrentAction("moving right");
		}
	}
	
	/***************************************************
	 * Basically an actionListener for all key presses *
	 ***************************************************/
	public void keyPressed(int key, char c) {
		
		if (key == 17) { // If the key pressed is the 'W' key
			if (player1.getPosition().getY() == 250){
				player1.setyVelocity(jumpVelocity);
			}
		}
		
		if (key == 200) { // If the key pressed is the up arrow key
			if (player2.getPosition().getY() == 250){
				player2.setyVelocity(jumpVelocity);
			}
		}
		
		if (key == 25) { // If the key pressed is the 'P' key
			gamePaused = !gamePaused;
		}
		
		if (key == 30) { // If the key pressed is the 'A' key
			player1.setIntent("left", true);
		}
		
		if (key == 32) { // If the key pressed is the 'D' key
			player1.setIntent("right", true);
		}
		
		if (key == 203) { // If the key pressed is the left arrow key
			player2.setIntent("left", true);
		}
		if (key == 205) { // If the key pressed is the right arrow key
			player2.setIntent("right", true);
		}
		
		// For testing purposes - will remove later - used to find our which int is for which key
		System.out.println("Key: " + key);
		System.out.println("Char: " + c);
	}
	
	/**
	 * Basically an actionListener for all mouse presses
	 * 
	 * @param x x coordinate of press
	 * @param y y coordinate of press
	 */
	public void mousePressed(int button, int x, int y) {
		if (gamePaused) {                                   // If the game is paused
			if (295 < x && x < 415 && 195 < y && y < 220) { // If the click was within the "return to main menu" box
				game.enterState(MainMenu.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
			}
		}
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	// This method serves to alert us if the "actualplay" gamestate is left
	// We use this method to reset all the values so that the gamestate is "reset", in essence
	// Note that this can be removed so that users are allowed to leave without a reset,
	// or modified to only reset under certain conditions, which
	// could be useful if we decided to have a different in-game option menu, and thus
	// not reset this gamestate on leaving for that reason
	public void leave(GameContainer container, StateBasedGame game) {
		player1.setPosition(new Vector2f(100, 250));
		player2.setPosition(new Vector2f(750, 250));
		
		foregroundPosition = -500;
		backgroundPosition = -500;
		
		gamePaused = false;
	}
	
	/****************************************************
	 * Basically an actionListener for all key releases *
	 ****************************************************/
	public void keyReleased(int key, char c) {
		
		if (key == 30) { // If the key released is the 'A' key
			player1.setIntent("left", false);
		}
		
		if (key == 32) { // If the key released is the 'D' key
			player1.setIntent("right", false);
		}
		
		if (key == 203) { // If the key released is the left arrow key
			player2.setIntent("left", false);
		}
		
		if (key == 205) { // If the key released is the right arrow key
			player2.setIntent("right", false);
		}
	}
	
	/********************************************
	 * Used to set the color scheme of the game *
	 *                                          *
	 * @param c1 first (filler) color           *
	 * @param c2 second (outline) color         *
	 ********************************************/
	public static void setColors(Color c1, Color c2) {
		color1 = c1;
		color2 = c2;
	}
	
	/*****************
	 * Draws the HUD *
	 *****************/
	public void drawHUD(Graphics g) {
		g.setLineWidth(3);
		
		// Drawing the time box
		g.setColor(color1);
		g.fillRect(475, 10, 50, 50);
		g.setColor(color2);
		g.drawRect(475, 10, 50, 50);
		
		// Drawing the rage boxes
		g.setColor(color1);
		g.fillRect(80, 530, 200, 50);    // Rage box 1
		g.setColor(color2);
		g.drawRect(80, 530, 200, 50);
		
		g.setColor(color1);
		g.fillRect(720, 530, 200, 50);   // Rage box 2
		g.setColor(color2);
		g.drawRect(720, 530, 200, 50);
		
		// Drawing the polygons
		g.setColor(color1);
		g.fill(bigLeft);
		g.setColor(color2);
		g.draw(bigLeft);
		
		g.setColor(color1);
		g.fill(bigRight);
		g.setColor(color2);
		g.draw(bigRight);
		
		g.setColor(color1);
		g.fill(smallLeft);
		g.setColor(color2);
		g.draw(smallLeft);
		
		g.setColor(color1);
		g.fill(smallRight);
		g.setColor(color2);
		g.draw(smallRight);
		
		g.setLineWidth(1);
	}
	
	

}

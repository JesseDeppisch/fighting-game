package game;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class Fighter {
	
	/**
	 * This class holds attributes for the characters (fighters) in the game.
	 * 
	 * @author JesseDeppisch
	 */
	
	// Graphics declarations
	private Image characterImage;            // Image that should be drawn for the fighter
	
	// Positioning and movement declarations
	private float yVelocity;                 // The velocity of the fighter
	private Vector2f position;               // Vector (has an x and y float) for position of the fighter (using java coordinate system)
	private AABB collisionBox;               // Collision box for the fighter 
	
	private boolean intentToMoveLeft;        // Boolean that sees if the player wants to move left
	private boolean intentToMoveRight;       // Boolean that sees if the player wants to move right
	private boolean leftMovementRestricted;  // Boolean that sees if the player is allowed to move left (due to collision boxes)
	private boolean rightMovementRestricted; // Boolean that sees if the player is allowed to move right (due to collision boxes)
	private boolean facingLeft;              // Boolean that checks if the player is facing left
	private boolean facingRight;             // Boolean that checks if the player is facing right
	
	private String currentAction;            // Current action that is being performed by the fighter
	private String lastAction;               // Last action performed by the fighter
	
	// Aspects (of the fighter) declarations
	private float health;                    // Health of the fighter
	private float rage;                      // Amount of rage of the fighter
	
	/**
	 * Construct a new fighter
	 * 
	 * @param characterImage Image of the fighter
	 * @param x x coordinate to spawn at
	 * @param y y coordinate to spawn at
	 */
	public Fighter(Image characterImage, float x, float y) {
		this.characterImage = characterImage;
		position = new Vector2f(x, y);
		
		currentAction = "idle";
		lastAction = "idle";
		
		setCollisionBox(new AABB(new Vector2f(position.getX() + (characterImage.getWidth() / 2), position.getY() - (characterImage.getHeight() / 2)), 
				new Vector2f(characterImage.getWidth() / 2, characterImage.getHeight() / 2)));
		// Creates the collision box, setting it at the center of the image, and setting the size as half, as requested by class AABB
		
	}
	
	public Vector2f getPosition() {
		return position;
	}
	
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	/**
	 * Move the fighter left
	 * 
	 * @param speed speed of the movement
	 */
	public void moveLeft(float speed) {
		position.set(position.getX() - speed, position.getY());
	}
	
	/**
	 * Move the fighter right
	 * 
	 * @param speed speed of the movement
	 */
	public void moveRight(float speed) {
		position.set(position.getX() + speed, position.getY());
	}
	
	public Image getImage() {
		return characterImage;
	}
	
	/**
	 * Set the intent of the fighter to move
	 * 
	 * @param direction direction of intent
	 * @param intent true if the fighter wants to move, false if not
	 */
	public void setIntent(String direction, boolean intent) {
		if (direction.equals("left")) {
			intentToMoveLeft = intent;
		} else if (direction.equals("right")) {
			intentToMoveRight = intent;
		} else {
			System.out.println("ERROR calling the setIntent method!"); // to stop errors from being confusing if we're stupid
		}
	}
	
	/**
	 * Get the movement intent of the fighter
	 * 
	 * @param direction direction of intent
	 * @return true if the fighter wants to move, false if not
	 */
	public boolean getIntent(String direction) {
		if (direction.equals("left")) {
			return intentToMoveLeft;
		} else if (direction.equals("right")) {
			return intentToMoveRight;
		} else {
			System.out.println("ERROR calling the getIntent method!"); // to stop errors from being confusing if we're stupid
			return false;
		}
		
	}
	
	/**
	 * Update the collision box of the fighter
	 */
	public void updateAABB() {
		getCollisionBox().pos.set(position.getX() + (characterImage.getWidth() / 2), position.getY() + (characterImage.getHeight() / 2)); 
		// Note that pos is a Vector2f
		// Also, image is the image of the fighter, which may later be pulled from the spritesheet, thus this logic may HAVE TO change
	}

	public AABB getCollisionBox() {
		return collisionBox;
	}

	public void setCollisionBox(AABB collisionBox) {
		this.collisionBox = collisionBox;
	}

	
	public boolean isLeftMovementRestricted() {
		return leftMovementRestricted;
	}

	public void setLeftMovementRestricted(boolean leftMovementRestricted) {
		this.leftMovementRestricted = leftMovementRestricted;
	}

	public boolean isRightMovementRestricted() {
		return rightMovementRestricted;
	}

	public void setRightMovementRestricted(boolean rightMovementRestricted) {
		this.rightMovementRestricted = rightMovementRestricted;
	}

	public String getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(String currentAction) {
		this.currentAction = currentAction;
	}

	public String getLastAction() {
		return lastAction;
	}

	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}

	public float getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(float yVelocity) {
		this.yVelocity = yVelocity;
	}

	

}

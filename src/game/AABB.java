package game;
import org.newdawn.slick.geom.Vector2f;

public class AABB {
	
	/**
	 * This class is responsible for the collision detection. The method that this class utilizes
	 * is Axis-Aligned Bounding Boxes, and objects of this class are collision boxes, and the class
	 * includes methods to be utilized for collision detection logic.
	 * 
	 * @author JesseDeppisch
	 */
	
	protected Vector2f pos;  // Center of the AABB
    protected Vector2f size; // Half the actual size
   
    /**
 	 * Constructor for the collision box objects
 	 * 
     * @param pos Center of the AABB
     * @param size Half of the actual size of the object that the AABB is for
     */
    public AABB(Vector2f pos, Vector2f size) {
    	this.pos = pos;
    	this.size = size;
    }
   
    /**
     * Checks to see if the AABBs are colliding
     * 
     * @param a first AABB to check
     * @param b second AABB to check
     * @return true if colliding
     */
    public static boolean collides(AABB a, AABB b) {
    	if (Math.abs(a.pos.x - b.pos.x) < a.size.x + b.size.x) {
    		if (Math.abs(a.pos.y - b.pos.y) < a.size.y + b.size.y) {
    			return true;
            }
    	}
      
    	return false;
    }
   
    /**
     * Checks to see if an AABB is inside another
     * 
     * @param a AABB on inside
     * @param b AABB on outside
     * @return true if a is inside b
     */
    public static boolean inside(AABB a, Vector2f b) {
    	if(Math.abs(a.pos.x - b.x) < a.size.x) {
    		if(Math.abs(a.pos.y - b.y) < a.size.y) {
    			return true;
    		}
    	}
    	return false;
    }
   
}
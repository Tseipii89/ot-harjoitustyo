
package domain;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * Interface to handle the moving Sprite objects of the game.
 * Every Sprite object has the methods for moving, rendering and hit detection.
 * 
 * @author juhop
 */
public interface Sprite {
    
    
    /**
    *
    * Method returns the x -position of the Sprite.
    * 
    * @return Integer x -position of the Sprite
    */
    public int getPositionX();
    
    /**
    *
    * Method sets the x -position of the Sprite.
    * 
    * @param positionX Integer sets the Sprite x -position
    */
    
    public void setPositionX(int positionX);
    
    /**
    *
    * Method returns the y -position of the Sprite.
    * 
    * @return Integer y -position of the Sprite
    */
    
    public int getPositionY();
    
    /**
    *
    * Method sets the y -position of the Sprite.
    * 
    * @param positionY Integer sets the Sprite y -position
    */
    
    public void setPositionY(int positionY);

    /**
     *
     * Used to update the position of the Sprite according to the time.
     * 
     * @param time time variable given to the Sprite. 
     * Sprite uses this information to update the Sprite position on the Canvas.
     */
    public void update(int time);

    /**
     *
     * Used to render the Sprite. Uses the current x and y location of the Sprite as well as the width and height.
     * 
     * @param gc GraphicsContext in which to do the rendering
     */
    public void render(GraphicsContext gc);

    /**
     *
     * Returns the 2D dimentions of the Sprite.
     * 
     * @return dimensions of the Sprite.
     */
    public Rectangle2D getBoundary();

    /**
     *
     * Checks if Sprite has hit another Sprite.
     * 
     * @param s another Sprite against which to check the collision.
     * @return true if this Sprite has hit the other Sprite. False otherwise.
     */
    public boolean intersects(Sprite s);
    
}

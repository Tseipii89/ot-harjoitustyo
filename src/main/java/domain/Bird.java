
package domain;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class implements Sprite interface.
 * Class creates a Bird object that bounces on the screen given user input.
 * 
 * @see Sprite
 * @author juhop
 */
public class Bird implements Sprite {


    private final Image flappyBirdImg;

    private int positionX;

    private int positionY;

    private int motionY;

    private double width;

    private double height; 
    
    /**
     *
     * This class controls the x and y position and the movement of the Bird.
     * As a constructor the Bird object is given the image to be used and the height and width of the game screen.
     * The class Starter constructs the Bird object.
     * @see Starter#start(javafx.stage.Stage) 
     * 
     * 
     * @param img the image to be used as a Bird Sprite
     * @param height the height of the game screen
     * @param width the width of the game screen
     */
    public Bird(Image img, int height, int width) {
        this.flappyBirdImg = img;
        this.positionX = width / 2 - 200;
        this.positionY = height / 2 - 15;
        this.motionY = 0;
        this.width = img.getWidth();
        this.width = img.getHeight();
    }
    
    /**
     *
     * This constructor is supposed to be only used for the testing.
     * 
     * @param height the height of the game screen
     * @param width the width of the game screen
     */
    public Bird(int height, int width) {
        this.flappyBirdImg = null;
        this.positionX = width / 2 - 200;
        this.positionY = height / 2 - 15;
        this.motionY = 0;
        this.width = 20;
        this.width = 20;
    }
    
    /**
     *
     * Method returns the x -position of the Bird.
     * 
     * @return Integer x -position of the Bird
     */
    public int getX() {
        return this.positionX;
    }
    
    /**
     *
     * Method sets the x -position of the Bird.
     * 
     * @param x Integer sets the Bird x -position
     */
    public void setX(int x) {
        this.positionX = x;
    }
    
    /**
     *
     * Method returns the y -position of the Bird.
     * 
     * @return Integer x -position of the Bird
     */
    public int getY() {
        return this.positionY;
    }
    
    /**
     *
     * Method sets the y -position of the Bird.
     * 
     * @param y Integer sets the Bird y -position
     */
    public void setY(int y) {
        this.positionY = y;
    }
    
    /**
     *
     * MotionY tells the motion of the Bird in y -axis.
     * 
     * @return Integer the y motion of the Bird.
     */
    public int getMotionY() {
        return this.motionY;
    }
    
    /**
     *
     * MotionY tells the motion of the Bird in y -axis.
     * 
     * @param y sets the y motion of the Bird.
     */
    public void setMotionY(int y) {
        this.motionY = y;
    }
    
    /**
     *
     * Returns the height of the bird.
     * 
     * @return Double the height of the Bird.
     */
    public double getheight() {
        return this.height;
    }
    
    /**
     *
     * Updates the y -position according to the motionY.
     * 
     * @param time every 5 time slot the y dropping motion is increased by 1
     * 
     */
    @Override
    public void update(int time) {
        if (time % 5 == 0) {
            motionY = motionY + 1;
        }
        
        this.positionY += this.motionY;
    }
    
    /**
     *
     * Jumps the Bird 10 y points upwards. 
     * Resets the falling of the Bird to zero.
     * 
     */
    public void birdJump() {
        if (this.motionY > 0) {
            this.motionY = 0;
        }
        
        this.positionY -= 10;
    }

    /**
     *
     * Renders the Bird image to the given graphicsContext.
     * 
     * @param gc renders the Bird image to the GraphicContext gc
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(flappyBirdImg, this.positionX, this.positionY);
    }

    /**
     *
     * Returns the boundary of this Bird Sprite.
     * 
     * @return Rectangle of the Bird object
     */
    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.positionX, this.positionY, width, height);
    }

    /**
     *
     * Checks if the Bird object has hit the given sprite s.
     * 
     * @param s another Sprite. Everything important is implementing the Sprite interface
     * @return true if intersects sprite s, otherwise false
     */
    @Override
    public boolean intersects(Sprite s) {
        return s.getBoundary().intersects(this.getBoundary());
        
    }
    
    
    
}

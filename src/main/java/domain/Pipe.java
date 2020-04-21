package domain;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * Class creates the Pipe objects that move in the screen from right to left.
 * The Bird is supposed to jump through the holes in the Pipes.
 * Class implements Sprite interface.
 * 
 * @author juhop
 */
public class Pipe implements Sprite {
    
 
    private int positionX;

    private int positionY;

    private int height;

    private int width;

    private int motionX;

    private final static Color COLOR = Color.GREEN;

    private boolean scored;

    private boolean topPipe;
    
    /**
     *
     * Class controls the position, rendering and movement of Pipe objects.
     * Class also offers method to check if the Bird object has hit the Pipe.
     * @see domain.Pipe#intersects(domain.Sprite) 
     * 
     * @param positionX The X position of the Pipe on the screen. Can be also outside of the visible screen
     * @param positionY The Y position where the Pipe starts
     * @param height The height of Pipe from PositionY downwards
     * @param width The width of the Pipe object
     * @param motionX How quickly or slowly the Pipe object moves through x -axis
     * @param top There are top and bottom Pipes to create the holes for the Bird. Returns true if Pipe is top Pipe.
     */
    public Pipe(int positionX, int positionY, int height, int width, int motionX, boolean top) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        this.motionX = motionX;
        this.scored = false;
        this.topPipe = top;
    }
    
    /**
     *
     * Returns the position on the x -axis of the Pipe.
     * 
     * @return x -position of Pipe
     */
    @Override
    public int getPositionX() {
        return positionX;
    }

    /**
     *
     * Sets the Pipe's position on x -axis.
     * 
     * @param positionX the value to change the Pipes x -axis value to
     */
    @Override
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     *
     * Returns the position on the y -axis of the Pipe.
     * 
     * @return y -position of Pipe
     */
    @Override
    public int getPositionY() {
        return positionY;
    }

    /**
     *
     * Sets the Pipe's position on y -axis.
     * 
     * @param positionY the value to change the Pipes y -axis value to
     */
    @Override
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    /**
     *
     * The height of the Pipe.
     * 
     * @return height of the Pipe object
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * Set the height of the Pipe object.
     * 
     * @param height set the height of the Pipe object to this
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * The width of the Pipe.
     * 
     * @return width of the Pipe object
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * Set the width of the Pipe object.
     * 
     * @param width set the width of the Pipe object to this
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * Return the movement speed of the Pipe through x -axis.
     * 
     * @return the movement speed.
     */
    public int getMotionX() {
        return motionX;
    }

    /**
     *
     * Set the movement speed of the Pipe through x -axis.
     * 
     * @param motionX new movement speed of the Pipe. 
     */
    public void setMotionX(int motionX) {
        this.motionX = motionX;
    }

    /**
     *
     * Boolean value to check if this Pipe has already been scored.
     * Already scored Pipes are not wanted to score again.
     * 
     * @return true if Pipe is already being score. False otherwise.
     */
    public boolean isScored() {
        return scored;
    }

    /**
     *
     * Sets the isScored value to given boolean value.
     * @see domain.Game#countScore() 
     * 
     * @param scored the value to set the Pipe's isScored value
     * @see domain.Pipe#isScored() 
     */
    public void setScored(boolean scored) {
        this.scored = scored;
    }

    /**
     *
     * Tells if the pipe is top or bottom Pipe. 
     * Used in scoring and updating Pipes.
     * @see domain.Game#countScore() 
     * @see domain.Game#updatePipes() 
     * 
     * @return true if top Pipe, false otherwise
     */
    public boolean isTopPipe() {
        return topPipe;
    }

    /**
     *
     * Updates the Pipe's x -position every tick.
     * 
     * @param time value used as a game timer. Given by Render class.
     */
    @Override
    public void update(int time) {
        if (time % 1 == 0) {
            this.positionX -= motionX;
        }
    }

    /**
     *
     * Renders the Pipe to the GraphicContext given.
     * 
     * @param gc GraphicContext to be rendered to
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(COLOR);
        gc.fillRect(this.positionX, this.positionY, this.width, this.height);
    }

    /**
     *
     * Returns the 2D rectangle of the Pipe's dimensions.
     * 
     * @return Pipe's dimensions as rectangle
     */
    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.positionX, this.positionY, width, height);
    }

    /**
     *
     * Checks if this Pipe object intersects the given Sprite s (in this case the Bird object). 
     * Used in the hit detection.
     * @see domain.Bird
     * @see domain.Game#checkIfGameOn() 
     * 
     * @param s the Sprite value against which to check the possible intersection
     * @return true if hits Sprite s, false otherwise
     */
    @Override
    public boolean intersects(Sprite s) {
     
        return s.getBoundary().intersects(this.getBoundary());
        
    }
    
}

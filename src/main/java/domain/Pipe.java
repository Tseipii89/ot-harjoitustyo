package domain;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Pipe implements Sprite {
    
    public int positionX;
    public int positionY;
    public int height;
    public int width;
    public int motionX;
    public final static Color COLOR = Color.GREEN;
    
    public Pipe(int positionX, int positionY, int height, int width, int motionX) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        this.motionX = motionX;
    }


    @Override
    public void update(int time) {
        if (time % 1 == 0) {
            this.positionX -= motionX;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(COLOR);
        gc.fillRect(this.positionX, this.positionY, this.width, this.height);
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.positionX, this.positionY, width, height);
    }

    @Override
    public boolean intersects(Sprite s) {
     
        return s.getBoundary().intersects(this.getBoundary());
        
    }
    
}

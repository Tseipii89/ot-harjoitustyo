package domain;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Pipe implements Sprite {
    
    public int X;
    public int Y;
    public int height;
    public int width;
    public int motionX;
    public final static Color COLOR = Color.GREEN;
    
    public Pipe(int X, int Y, int height, int width, int motionX) {
        this.X = X;
        this.Y = Y;
        this.width = width;
        this.height = height;
        this.motionX = motionX;
    }


    @Override
    public void update(int time) {
        if( time%1 == 0) {
            this.X -= motionX;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(COLOR);
        gc.fillRect(this.X, this.Y, this.width, this.height);
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.X, this.Y, width, height);
    }

    @Override
    public boolean intersects(Sprite s) {
     
        return s.getBoundary().intersects(this.getBoundary());
        
    }
    
}

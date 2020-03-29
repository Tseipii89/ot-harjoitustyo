
package domain;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bird implements Sprite {
    public Image flappyBirdImg;
    public int X;
    public int Y;
    public int motionY;
    public double width;
    public double height; 
    
    public Bird(Image img, int height, int width) {
        this.flappyBirdImg = img;
        this.X = width/2 -200;
        this.Y = height/2-15;
        this.motionY = 0;
        this.width = img.getWidth();
        this.width = img.getHeight();
    }
    
   

    @Override
    public void update(int time) {
        if( time%10 == 0) {
            motionY = motionY + 2;
        }
        
        this.Y += this.motionY;
    }
    
    public void birdJump() {
        if( this.motionY > 0) {
            this.motionY = 0;
        }
        
        this.Y -= 10;
    }

    @Override
    public void render(GraphicsContext gc) {
      gc.drawImage( flappyBirdImg, this.X, this.Y );
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.X, this.Y, width, height);
    }

    @Override
    public boolean intersects(Sprite s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

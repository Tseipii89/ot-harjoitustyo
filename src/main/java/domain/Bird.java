
package domain;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bird implements Sprite {
    public Image flappyBirdImg;
    public int positionX;
    public int positionY;
    public int motionY;
    public double width;
    public double height; 
    
    public Bird(Image img, int height, int width) {
        this.flappyBirdImg = img;
        this.positionX = width / 2 - 200;
        this.positionY = height / 2 - 15;
        this.motionY = 0;
        this.width = img.getWidth();
        this.width = img.getHeight();
    }
    
    public Bird(int height, int width) {
        this.flappyBirdImg = null;
        this.positionX = width / 2 - 200;
        this.positionY = height / 2 - 15;
        this.motionY = 0;
        this.width = 20;
        this.width = 20;
    }
    
   

    @Override
    public void update(int time) {
        if (time % 5 == 0) {
            motionY = motionY + 1;
        }
        
        this.positionY += this.motionY;
    }
    
    public void birdJump() {
        if (this.motionY > 0) {
            this.motionY = 0;
        }
        
        this.positionY -= 10;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(flappyBirdImg, this.positionX, this.positionY);
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

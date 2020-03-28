
package domain;

import javafx.scene.image.Image;

public class Bird {
    public Image flappyBirdImg;
    public int X;
    public int Y;
    public int motionY;
    
    public Bird(Image img, int height, int width) {
        this.flappyBirdImg = img;
        this.X = width/2 -200;
        this.Y = height/2-15;
        this.motionY = 0;
    }
    
    public void BirdNewPosition(int timeSpentWithoutUpArrow) {
        if( timeSpentWithoutUpArrow%10 == 0) {
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
    
    
    
}

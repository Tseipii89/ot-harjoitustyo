
package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;


public class Game {

    //these will be the final dimensions of the game. Maybe I'll make them modifiable for player.
    public static int HEIGHT;
    public static int WIDTH;
    public Bird GAMEBIRD;
    public boolean isRunning;
    //No need to change the background...I hope.
    public static Image background;
    
    public Game(Bird gameBird, int height, int width ) throws FileNotFoundException {
        this.GAMEBIRD = gameBird;
        Game.HEIGHT = height;
        Game.WIDTH = width;
        isRunning = true;
        Game.background = new Image( "/images/flappybirdtausta.png" );
    }
    

    public void checkIfGameOn() {
        Rectangle2D birdRect = this.GAMEBIRD.getBoundary();
        // check if bird has hit the bottom
        if(birdRect.getMaxY()+15 > Game.HEIGHT) {
            this.isRunning = false;
        }
    }

    public boolean birdNotOutOfField() {
        Rectangle2D birdRect = this.GAMEBIRD.getBoundary();
        if(birdRect.getMaxY()+15 > Game.HEIGHT) {
            return false;
        }
        return birdRect.getMinY()-15 >= 0;
    }

    public void birdJump() {
        if(this.birdNotOutOfField()) {
            this.GAMEBIRD.birdJump();
        }
    }
}

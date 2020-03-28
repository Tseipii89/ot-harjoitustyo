
package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        Game.background = new Image( new FileInputStream("src\\main\\resources\\images\\flappybirdtausta.png") );
    }
    

    
    public void changeGameStatus() {
        isRunning = !isRunning;
    }
}

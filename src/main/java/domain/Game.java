
package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    public ArrayList<Pipe> PIPES;
    
    public Game(Bird gameBird, int height, int width ) throws FileNotFoundException {
        this.GAMEBIRD = gameBird;
        Game.HEIGHT = height;
        Game.WIDTH = width;
        isRunning = true;
        Game.background = new Image( "/images/flappybirdtausta.png" );
        PIPES = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            createPipe(i*300);
        }

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

    private void createPipe(int whereToStart) {
             int Y = (int) (100 + Math.random()*(Game.HEIGHT/2));
             int X = 800 + whereToStart;
             Pipe topPipe = new Pipe(X, 0, Y, 70, 20);
             Pipe bottomPipe = new Pipe(X, Y+175, Game.HEIGHT-(Y+150), 70, 20);
             PIPES.add(topPipe);
             PIPES.add(bottomPipe);

    }
}

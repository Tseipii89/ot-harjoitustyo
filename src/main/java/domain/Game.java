
package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
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
    public int spaceBetweenPipes;
    public int widthOfPipe;
    
    public Game(Bird gameBird, int height, int width ) throws FileNotFoundException {
        this.GAMEBIRD = gameBird;
        Game.HEIGHT = height;
        Game.WIDTH = width;
        isRunning = false;
        Game.background = new Image( "/images/flappybirdtausta.png" );
        spaceBetweenPipes = 300;
        widthOfPipe = 70;
        PIPES = new ArrayList<>();
        this.startGameAddPipes();
    }
    
    private void startGameAddPipes() {
        for (int i = 0; i < 4; i++) {
            createPipe(i*(widthOfPipe+spaceBetweenPipes));
        }
    }

    public void checkIfGameOn() {
        Rectangle2D birdRect = this.GAMEBIRD.getBoundary();
        // check if bird has hit the bottom
        if(birdRect.getMaxY()+15 > Game.HEIGHT) {
            this.isRunning = false;
        }
        for (Pipe pipe : PIPES) {
            if(pipe.intersects(this.GAMEBIRD)) {
                this.isRunning = false;
            }
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

    // Creates the top and bottom half of the pipe
    private void createPipe(int whereToStart) {
             int Y = (int) (100 + Math.random()*(Game.HEIGHT/2));
             int X = 800 + whereToStart;
             Pipe topPipe = new Pipe(X, 0, Y, widthOfPipe, 2);
             Pipe bottomPipe = new Pipe(X, Y+175, Game.HEIGHT-(Y+150), widthOfPipe, 2);
             PIPES.add(topPipe);
             PIPES.add(bottomPipe);

    }


    public void updateBirdPlacement(ArrayList<String> input, int time) {
        if(input.contains("UP")){
           this.birdJump();
        }
        this.GAMEBIRD.update(time);
    }

    public void drawPipes(GraphicsContext graphicsContext, int time) {
    
        for (Pipe pipe : this.PIPES) {
            pipe.update(time);
            pipe.render(graphicsContext); 
        }

    }

    public void updatePipes() {
    
        for (Pipe pipe : this.PIPES) {
            if(pipe.X+pipe.width<0) {
                pipe.X = 4*(widthOfPipe+spaceBetweenPipes)-widthOfPipe;
            }
        }

        
    
    }

    public void reset() {
        this.PIPES.clear();
        this.startGameAddPipes();
        
        this.GAMEBIRD.X = WIDTH /2 -200;
        this.GAMEBIRD.Y = HEIGHT /2 -15;
        
    }
}


package domain;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ui.Render;


public final class Game {

    //these will be the final dimensions of the game. Maybe I'll make them modifiable for player.
    public static int height;
    public static int width;
    public Bird gameBird;
    public boolean isRunning;
    //No need to change the background...I hope.
    public ArrayList<Pipe> pipes;
    public int spaceBetweenPipes;
    public int widthOfPipe;
       
    public Game(Bird gameBird, int height, int width) throws FileNotFoundException {
        this.gameBird = gameBird;
        Game.height = height;
        Game.width = width;
        isRunning = false;
        spaceBetweenPipes = 300;
        widthOfPipe = 70;
        pipes = new ArrayList<>();
        this.startGameAddPipes();
    }
    
    public Game(Bird gameBird) {
        this.gameBird = gameBird;
        Game.height = 600;
        Game.width = 800;
        isRunning = false;
        spaceBetweenPipes = 300;
        widthOfPipe = 70;
        pipes = new ArrayList<>();
        this.startGameAddPipes();
    }
    
    public void startGameAddPipes() {
        for (int i = 0; i < 4; i++) {
            createPipe(i * (widthOfPipe + spaceBetweenPipes));
        }
    }

    public void checkIfGameOn() {
        Rectangle2D birdRect = this.gameBird.getBoundary();
        // check if bird has hit the bottom
        if (birdRect.getMaxY() + 15 > Game.height) {
            this.isRunning = false;
        }
        pipes.stream().filter((pipe) -> (pipe.intersects(this.gameBird))).forEachOrdered((item) -> {
            this.isRunning = false;
        });
    }

    public boolean birdNotOutOfField() {
        Rectangle2D birdRect = this.gameBird.getBoundary();
        if (birdRect.getMaxY() + 15 > Game.height) {
            return false;
        }
        return birdRect.getMinY() - 15 >= 0;
    }

    public void birdJump() {
        if (this.birdNotOutOfField()) {
            this.gameBird.birdJump();
        }
    }

    // Creates the top and bottom half of the pipe
    private void createPipe(int whereToStart) {
        int positionY = (int) (100 + Math.random() * (Game.height / 2));
        int positionX = 800 + whereToStart;
        Pipe topPipe = new Pipe(positionX, 0, positionY, widthOfPipe, 2);
        Pipe bottomPipe = new Pipe(positionX, positionY + 175, Game.height - (positionY + 150), widthOfPipe, 2);
        pipes.add(topPipe);
        pipes.add(bottomPipe);

    }


    public void updateBirdPlacement(ArrayList<String> input, int time) {
        if (input.contains("UP")) {
            this.birdJump();
        }
        this.gameBird.update(time);
    }

    public void drawPipes(GraphicsContext graphicsContext, int time) {
    
        for (Pipe pipe : this.pipes) {
            pipe.update(time);
            pipe.render(graphicsContext); 
        }

    }

    public void updatePipes() {
    
        for (Pipe pipe : this.pipes) {
            if (pipe.positionX + pipe.width < 0) {
                pipe.positionX = 4 * (widthOfPipe + spaceBetweenPipes) - widthOfPipe;
            }
        }

        
    
    }

    public void reset() {
        this.pipes.clear();
        this.startGameAddPipes();
        
        this.gameBird.positionX = width / 2 - 200;
        this.gameBird.positionY = height / 2 - 15;
        
    }
}

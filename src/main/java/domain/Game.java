
package domain;

import dao.HighscoreDao;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * Class works as a motor for the game. It handles all the logic behind the game.
 * 
 * @author juhop
 */
public final class Game {

 
    private static int height;

    private static int width;

    private final Bird gameBird;

    private boolean isRunning;

    private final ArrayList<Pipe> pipes;

    private int spaceBetweenPipes;

    private int widthOfPipe;
    
    private int sizeOfHole;
    
    private int howManyPipesBeginning;

    private int score;

    private String username;

    private HighscoreDao highscore; 
       
    /**
     *
     * Game class handles all the logic behind the game.
     * It creates the pipes, controls the movement calls for the bird, 
     * checks if the game is on, updates and saves scores and nickname, updates the bird and pipes 
     * and reset the game.
     * 
     * 
     * @param gameBird this is the Bird object to be used in the game
     * @param height the height of the gamescreen. This is given by the Starter class
     * @param width the width of the game. This is given by the Starter class
     * @param highscore the data access object to be used to save the highscore and nickname. Given by the Starter class.
     * 
     * @see Starter#start(javafx.stage.Stage) 
     * 
     * @throws FileNotFoundException if the DAO is not applicable. The DAO issue should be handled by the DAO implementation.
     */
    public Game(Bird gameBird, int height, int width, HighscoreDao highscore) throws FileNotFoundException {
        this.gameBird = gameBird;
        this.height = height;
        this.width = width;
        this.isRunning = false;
        this.spaceBetweenPipes = 300;
        this.widthOfPipe = 70;
        this.sizeOfHole = 175;
        this.howManyPipesBeginning = 4;
        this.pipes = new ArrayList<>();
        this.startGameAddPipes();
        this.score = 0;
        this.username = "";
        this.highscore = highscore;
    }
    
    
    /**
    *
    * Method returns the nickname of the current player.
    * 
    * @return String name of the current player
    */
    public String getUsername() {
        return this.username;
    }
    
    /**
    *
    * Sets the player name.
    * 
    * @param username the name to be set to current player
    * @see ui.Render#starterScene(javafx.stage.Stage) 
    */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
    *
    * Method returns the width of the game screen.
    * 
    * @return int the width of the game screen
    */
    public int getWidth() {
        return this.width;
    }
    
    /**
    *
    * Method returns the height of the game screen.
    * 
    * @return int the height of the game screen
    */
    public int getHeight() {
        return this.height;
    }
    
    /**
    *
    * Returns the value if the game is running.
    * 
    * @return boolean true if game is running, false otherwise
    */
    public boolean getIsTheGameRunning() {
        return this.isRunning;
    }
    
    /**
    *
    * Sets the game is running value.
    * 
    * @param running boolean value is the game running or not
    */
    public void setTheGameRunning(boolean running) {
        this.isRunning = running;
    }
    
    /**
    *
    * Returns the game Bird used.
    * 
    * @return the Game Bird being used
    */
    public Bird getTheGameBird() {
        return this.gameBird;
    }
    
    /**
    *
    * Returns the current game points.
    * 
    * @return the Points of current game
    */
    public int getPoints() {
        return this.score;
    }
    
    /**
    *
    * Returns the all time highscore.
    * 
    * @return the all time highscore
    */
    public int getHighscore() {
        return this.highscore.readHighscore();
    }
    
    /**
    *
    * Returns the object that knows the name of the highscore player.
    * 
    * @return the DAO object that is used to score the highscore information. Used in this context to get the nickname of the player.
    */
    public Object getAllTimePlayer() {
        return this.highscore.readNickname();
    }
    
    /**
    *
    * This is only used for testing purposes.
    * 
    * @return the pipes arraylist
    */
    public ArrayList<Pipe> getThePipes() {
        return this.pipes;
    }
    
    
    /**
    *
    * This is only used for testing purposes.
    * 
     * @param pipeToAdd used as a testing parameter
    */
    public void setThePipes(Pipe pipeToAdd) {
        this.pipes.add(pipeToAdd);
      
    }
    
    /**
     * In the beginning of the game four pipes are added to the Pipes arrayList. 
     * These same pipes are used throughout the game.
     */
    public void startGameAddPipes() {
        for (int i = 0; i < this.howManyPipesBeginning; i++) {
            createPipe(i * (widthOfPipe + spaceBetweenPipes));
        }
    }

    /**
     * Checks if the game is on. The game on/off results in different scene that is showed to the player.
     * Updates the game.isRunning to false if Bird object hits the bottom of the screen or the Bird object hits any of the pipes.
     */
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


    /**
     * Jumps the bird if birdNotOutOfField.
     * @see Game#birdNotOutOfField() 
     * @see Bird#birdJump() 
     */
    public void birdJump() {
        if (this.birdNotOutOfField()) {
            this.gameBird.birdJump();
        }
    }
    
     /**
     * Used by the birdJump method to check if the Bird can jump.
     * If bird is on the top of the screen it is not allowed to jump until it is under 15 y points under the top.
     * 
     * @return true if bird can jump, false if bird has hit the bottom or top of the screen
     */
    private boolean birdNotOutOfField() {
        Rectangle2D birdRect = this.gameBird.getBoundary();
        if (birdRect.getMaxY() + 15 > Game.height) {
            return false;
        }
        return birdRect.getMinY() - 15 >= 0;
    }


    /**
     * Creates the top and bottom half of the pipe and adds them to the pipes list.
     * The hole of the pipes is at random location between at game.height/2 + 100 y -points.
     * The height of the hole is 5 times the height of the Bird
     * 
     * @param whereToStart the X -position of where to add the top and bottom pipe
     */
    private void createPipe(int whereToStart) {
        int pipeHeight = (int) (100 + Math.random() * (Game.height / 2));
        int positionX = this.width + whereToStart;
        Pipe topPipe = new Pipe(positionX, 0, pipeHeight, widthOfPipe, 2, true);
        Pipe bottomPipe = new Pipe(
                positionX, 
                pipeHeight + this.sizeOfHole, // Bottom Pipe starts sizeOfHole points lower than the top Pipe ends, so the hole in the Pipes is sizeOfHole points
                Game.height - (pipeHeight + this.sizeOfHole), // Bottom Pipe height is the height of the screen minus the height of the top Pipe and the wanted hole
                widthOfPipe, 
                2, 
                false);
        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    /**
     * Checks if the player has pressed UP -arrow and jumps the Bird object accordingly.
     * Calls the Bird update method to update the Bird y -position
     * 
     * @param input the pressed keys input arrayList
     * @param time the time the game has passed
     */
    public void updateBirdPlacement(ArrayList<String> input, int time) {
        if (input.contains("UP")) {
            this.birdJump();
        }
        this.gameBird.update(time);
    }

    /**
     *
     * Draws the Pipes to the given graphicContext.
     * 
     * @param graphicsContext the graphicContext where the pipes are drawn
     * @param time the pipes move in the X -axel everytime by given motionX
     */
    public void drawPipes(GraphicsContext graphicsContext, int time) {
    
        for (Pipe pipe : this.pipes) {
            pipe.update(time);
            pipe.render(graphicsContext); 
        }

    }

    /**
     * Updates the pipe X position to the right side of the screen if the Pipe has moved out of the left side of the screen.
     * Sets the pipes score value to true, so that the pipe can be scored again.
     * Updates the placement of the hole.
     * @see domain.Pipe#setHeight(int) 
     * @see domain.Pipe#setPositionX(int) 
     */
    public void updatePipes() {
        int pipeHeight = (int) (100 + Math.random() * (Game.height / 2));
        for (Pipe pipe : this.pipes) {
            if (pipe.getPositionX() + pipe.getWidth() < 0) { // Checks if Pipe has gone out of sight from the left edge
                pipe.setPositionX(this.howManyPipesBeginning * (widthOfPipe + spaceBetweenPipes) - widthOfPipe); // Only one top and bottom Pipe will be updated at a time, so the others are on their way and this Pipes x -position should be updated accordinly
                if (pipe.isTopPipe() == true) {
                    pipe.setHeight(pipeHeight);
                } else { // Bottom Pipe needs a new y -position and height
                    pipe.setPositionY(pipeHeight + this.sizeOfHole);
                    pipe.setHeight(Game.height - (pipeHeight + this.sizeOfHole));
                }
                pipe.setScored(false); // The Pipe should be able to be scored again
            }
        }

        
    
    }

    /**
     * Reset the game. Sets the Bird in the beginning position, updates the Highscore DAO if needed and sets the score of the new game to 0.
     */
    public void reset() {
        // New Pipes
        this.pipes.clear();
        this.startGameAddPipes();
        
        // "New" Bird
        this.gameBird.setPositionX(width / 2 - 200);
        this.gameBird.setPositionY(height / 2 - 15);
        
        // Check if player got new highscore
        if (this.score > this.highscore.readHighscore()) {
            Nickname newChampion = new Nickname(this.username, this.score);
            this.highscore.update(newChampion);
        }
        this.score = 0;
        
    }

    /**
     * Updates the score of the current game, if the Bird has gone through the pipe hole. 
     * Sets the pipe score value to false, so that the same pipe isn't scored again.
     */
    public void countScore() {
        for (Pipe pipe : this.pipes) {
            if (pipe.getPositionX() + pipe.getWidth() < this.gameBird.getPositionX()
                    && pipe.isScored() == false
                    && pipe.isTopPipe() == true) // Every Pipe comes as a pair of top and bottom Pipe. Check if Pipe is top so that the pair is scored only once.
            {
                this.score++;
                pipe.setScored(true);
            }
        }
    }
}

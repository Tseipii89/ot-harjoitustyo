package domain;

import dao.FileHighscoreDao;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ui.Render;

/**
 *
 * Starter class initializes and starts the game.
 * 
 * @author juhop
 */
public class Starter extends Application {
    
    private static Image birdie; 

    /**
    *
    * Method initializes the game.
    * The game -object is given Bird -object, HighscoreDao -object and width and height of the game screen.
    * Finally Game -object is given to the Render object which renders the game.
    * 
    * 
    * @param primaryStage Stage to be used for different scenes
    * @throws java.io.FileNotFoundException this problem should be handled in the HighscoreDao -object which creates new file if it doesn't already exists
    */
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
        birdie = new Image("/images/flappybird.png");
        int height = 600;
        int width = 800;
        Bird flappyBirdie = new Bird(birdie, height, width);
        
        File fileName = new File("highscore.txt");
        
        FileHighscoreDao fileHighscoreDao = new FileHighscoreDao(fileName);
        
        Game peli = new Game(flappyBirdie, height, width, fileHighscoreDao);
        Render peliRender = new Render(peli);

        peliRender.start(primaryStage);
    }
    
    /**
     *
     * This method is called from main.Main class.
     * It starts the game.
     * @see main.Main
     * 
     * @param args same args that is given to Main class
     */
    public static void main(String[] args)  {
        launch(args);
    }
    
}

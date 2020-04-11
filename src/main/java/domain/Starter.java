package domain;

import dao.FileHighscoreDao;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ui.Render;


public class Starter extends Application {
    
    private static Image birdie; 

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
    
    public static void main(String[] args)  {
        launch(args);
    }
    
}

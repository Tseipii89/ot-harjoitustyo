package domain;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ui.Render;


public class Starter extends Application {
    
    private static Image birdie; 

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        birdie = new Image("/images/flappybird.png");
        int height = 600;
        int width = 800;
        Bird flappyBirdie = new Bird(birdie, height, width);
        
        Game peli = new Game(flappyBirdie, height, width);
        Render peliRender = new Render(peli);

        peliRender.start(primaryStage);
    }
    
     public static void main(String[] args)  {
        launch(args);
    }
    
}

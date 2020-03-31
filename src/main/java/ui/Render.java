
package ui;


import domain.Game;
import domain.Pipe;
import static domain.Pipe.COLOR;
import domain.Sprite;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Render extends Application {
    

    private static Game gameMotor;
    
    public Render(Game gameMotor) {
        Render.gameMotor = gameMotor;
    }

    @Override
    public void start(Stage gameWindow)  {
        
        gameWindow.setTitle("Flappy Bird- flapity flap: UP-arrow to bounce the birdie!");
        
        
        // We add the root node for the possibility to start adding different elements
        Group root = new Group();
        Scene theScene = new Scene(root);
        gameWindow.setScene(theScene);
                       
        Canvas gameCanvas = new Canvas(gameMotor.WIDTH, gameMotor.HEIGHT);
        root.getChildren().add(gameCanvas);
        
        GraphicsContext graphicsContext = gameCanvas.getGraphicsContext2D();
       
        
        ArrayList<String> input = new ArrayList<>();
        
        theScene.setOnKeyPressed((KeyEvent e) -> {
            String code = e.getCode().toString();
            // only add once... prevent duplicates
            if ( !input.contains(code) )
                input.add( code );
        });
        theScene.setOnKeyReleased((KeyEvent e) -> {
            String code = e.getCode().toString();
            input.remove( code );
        });

        
        
            final long startNanoTime = System.nanoTime();
            new AnimationTimer()
            {
            @Override
            public void handle(long currentNanoTime)
                {
                    if(gameMotor.isRunning) {
                        int time = (int) ((currentNanoTime - startNanoTime) / 20000000); 

                        

                        
                        if(input.contains("UP")){
                            gameMotor.birdJump();
                        }
                        gameMotor.GAMEBIRD.update(time);

                        // update bird location
                        graphicsContext.drawImage( gameMotor.background, 0, 0 );
                        
                        
                        for (Pipe pipe : gameMotor.PIPES) {
                            pipe.update(time);
                            graphicsContext.setFill(Color.GREEN);
                            pipe.render(graphicsContext); 
                        }

                        
                        gameMotor.GAMEBIRD.render(graphicsContext);
                        gameMotor.checkIfGameOn();
                   } else {
                        gameWindow.setTitle("Try again");
                   }
                   
                }
            }.start();
            gameWindow.show();
        
        
        

        
        
        
        

        
    }
    
}

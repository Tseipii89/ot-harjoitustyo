
package ui;


import domain.Game;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Render extends Application {
    private final Group root;
    private GraphicsContext graphicsContext;
    private final ArrayList<String> input;
    private static Game gameMotor;
    
    public Render(Game gameMotor) {
        this.root = new Group();
        this.input = new ArrayList<>();
        Render.gameMotor = gameMotor;
    }

    @Override
    public void start(Stage gameWindow)  {
        
        gameWindow.setTitle("Flappy Bird- flapity flap: UP-arrow to bounce the birdie!");
        // We add the root node for the possibility to start adding different elements
        Scene theScene = new Scene(root);
        gameWindow.setScene(theScene);
                       
        Canvas gameCanvas = new Canvas(gameMotor.WIDTH, gameMotor.HEIGHT);
        root.getChildren().add(gameCanvas);
        
        this.graphicsContext = gameCanvas.getGraphicsContext2D();

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
        
        this.newWaitForUpArrowScreen(gameWindow);                       
    }
    
    
    private void newWaitForUpArrowScreen(Stage gameWindow) {
        
        
        final long startNanoTime = System.nanoTime(); 
        new AnimationTimer()
        {
        @Override
        public void handle(long startNanoTime) {
      
            if(gameMotor.isRunning){
                this.newGameRun( startNanoTime);
            } else {
                this.newStartScreen();
            }
        }
        private void newGameRun(long currentNanoTime) {
            if(gameMotor.isRunning) {
                int time = (int) ((currentNanoTime - startNanoTime) / 20000000); 
                // updates the birds position on the screen
                gameMotor.updateBirdPlacement(input, time);
                // render the background again so there isn't any "shadows" for the bird
                graphicsContext.drawImage( gameMotor.background, 0, 0 );
                gameMotor.drawPipes(graphicsContext, time);
                gameMotor.GAMEBIRD.render(graphicsContext);
                gameMotor.checkIfGameOn();
                gameMotor.updatePipes();
            } 
        }
        private void newStartScreen() {
            if(!gameMotor.isRunning) {
                graphicsContext.setFill( Color.RED );
                graphicsContext.setStroke( Color.BLACK );
                graphicsContext.setLineWidth(2);
                Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
                graphicsContext.setFont( theFont );
                graphicsContext.fillText( "Start a new game by \n"
                        + "pressing UP -arrow", 200, 100 );
                graphicsContext.strokeText( "Start a new game by \n"
                        + "pressing UP -arrow", 200, 100 );
                if(input.contains("UP")){
                   gameMotor.reset();
                   gameMotor.isRunning = true;
                }
            }
        }
        }.start();
        gameWindow.show();
    }

    
}

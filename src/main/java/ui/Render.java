
package ui;


import domain.Game;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Render extends Application {
    private final Group root;
    private GraphicsContext graphicsContext;
    private final ArrayList<String> input;
    private static Game gameMotor;
    public static Image background;
    public Scene theGameScene;
    public Canvas gameCanvas;
    
    public Render(Game gameMotor) {
        this.root = new Group();
        this.input = new ArrayList<>();
        Render.gameMotor = gameMotor;
        Render.background = new Image( "/images/flappybirdtausta.png" );
    }

    @Override
    public void start(Stage gameWindow)  {
        
        gameWindow.setTitle("Flappy Bird- flapity flap: UP-arrow to bounce the birdie!");
        // We add the root node for the possibility to start adding different elements
        this.starterScene(gameWindow);
        this.initGameScene();
        gameWindow.show();
        this.newWaitForUpArrowScreen(gameWindow);
    }
    
    private void starterScene(Stage gameWindow) {
        Label label= new Label("Write your nickname to start");
        Button button1= new Button("Start the Game!");
        TextField nickName = new TextField();
        button1.setOnAction((ActionEvent e) -> {
            if (nickName.getText().length() < 3 ) {
                label.setText("Nickname has to be at least 3 chracters long");
            } else {
                gameMotor.setUsername(nickName.getText()); 
                gameWindow.setScene(theGameScene);
            }
        });  
        BorderPane startScreenLayout = new BorderPane();
        VBox middleSet = new VBox();
        middleSet.setSpacing(10);
        middleSet.getChildren().addAll(label, nickName, button1);
        startScreenLayout.setCenter(middleSet);
        BackgroundImage startBackgroundImage = new BackgroundImage(Render.background, NO_REPEAT, NO_REPEAT, null, null);
        startScreenLayout.setBackground(new Background(startBackgroundImage));
        Scene startScene = new Scene(startScreenLayout, gameMotor.getWidth(), gameMotor.getHeight());
        gameWindow.setScene(startScene);
        
    }
    
    private void initGameScene() {
        theGameScene = new Scene(root);
                       
        gameCanvas = new Canvas(gameMotor.getWidth(), gameMotor.getHeight());
        root.getChildren().add(gameCanvas);
        
        this.graphicsContext = gameCanvas.getGraphicsContext2D();
        graphicsContext.drawImage( Render.background, 0, 0 );
        theGameScene.setOnKeyPressed((KeyEvent e) -> {
            String code = e.getCode().toString();
            // only add once... prevent duplicates
            if ( !input.contains(code) )
                input.add( code );
        });
        theGameScene.setOnKeyReleased((KeyEvent e) -> {
            String code = e.getCode().toString();
            input.remove( code );
        });   
    }
    
    
    private void newWaitForUpArrowScreen(Stage gameWindow) {
        
        
        final long startNanoTime = System.nanoTime(); 
        new AnimationTimer()
        {
        @Override
        public void handle(long startNanoTime) {
      
            if(gameMotor.getIsTheGameRunning()){
                this.newGameRun( startNanoTime);
            } else {
                this.newStartScreen();
            }
        }
        private void newGameRun(long currentNanoTime) {
            if(gameMotor.getIsTheGameRunning()) {
                int time = (int) ((currentNanoTime - startNanoTime) / 20000000); 
                // updates the birds position on the screen
                gameMotor.updateBirdPlacement(input, time);
                // render the background again so there isn't any "shadows" for the bird
                graphicsContext.drawImage( Render.background, 0, 0 );
                gameMotor.drawPipes(graphicsContext, time);
                gameMotor.getTheGameBird().render(graphicsContext);
                gameMotor.checkIfGameOn();
                gameMotor.updatePipes();
                gameMotor.countScore();
                this.setText();
            } 
        }
        private void newStartScreen() {
            if(!gameMotor.getIsTheGameRunning()) {
                this.setText();
                if(input.contains("UP")){
                   gameMotor.reset();
                   gameMotor.setTheGameRunning(true);
                }
            }
        }
        private void setText() {
            graphicsContext.setFill( Color.RED );
            Font ScoreFont = Font.font( "Times New Roman", FontWeight.BOLD, 24 );
            graphicsContext.setFont( ScoreFont );            
            graphicsContext.fillText( "Points: "+ gameMotor.getPoints(), 50, 50 );
            graphicsContext.fillText( "User: "+ gameMotor.getUsername(), 50, 20 );
            graphicsContext.fillText( "Highscore: "+ gameMotor.getHighscore(), 600, 50 );
            graphicsContext.fillText( "User: "+ gameMotor.getAllTimePlayer(), 600, 20 );
            if(!gameMotor.getIsTheGameRunning()) {
            graphicsContext.setStroke( Color.BLACK );
            graphicsContext.setLineWidth(2);
            Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
            graphicsContext.setFont( theFont );
                graphicsContext.fillText( "Start a new game by \n"
                        + "pressing UP -arrow", 200, 100 );
                graphicsContext.strokeText( "Start a new game by \n"
                        + "pressing UP -arrow", 200, 100 );
            }
        }
        }.start();
        gameWindow.show();
    }

    
}

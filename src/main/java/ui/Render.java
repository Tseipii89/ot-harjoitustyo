
package ui;


import domain.Game;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
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
    private final Font h1Font =  Font.font( "Times New Roman", FontWeight.BOLD, 48 );
    private final Font h2Font =  Font.font( "Times New Roman", FontWeight.BOLD, 24 );
    
    public Render(Game gameMotor) {
        this.root = new Group();
        this.input = new ArrayList<>();
        Render.gameMotor = gameMotor;
        Render.background = new Image( "/images/flappybirdtausta.png" );
    }

    @Override
    public void start(Stage gameWindow)  {
        gameWindow.setTitle("Flappy Bird- flapity flap: UP-arrow to bounce the birdie!");
        this.starterScene(gameWindow); // Game inits with the startscene where the user is asked to give nickname input
        this.initGameScene(); 
        gameWindow.show();
        this.newWaitForUpArrowScreen(gameWindow);
    }
    
    private void starterScene(Stage gameWindow) {
        Scene startScene = new Scene(this.setStarterUIElementsPositions(gameWindow), gameMotor.getWidth(), gameMotor.getHeight());
        gameWindow.setScene(startScene);
    }
    
    private BorderPane setStarterUIElementsPositions(Stage gameWindow) {
        BorderPane startScreenLayout = new BorderPane();
        startScreenLayout.setCenter(this.getStarterUIElements(gameWindow));
        BackgroundImage startBackgroundImage = new BackgroundImage(Render.background, NO_REPEAT, NO_REPEAT, null, null);
        startScreenLayout.setBackground(new Background(startBackgroundImage));
        return startScreenLayout;
    }
    
    private VBox getStarterUIElements(Stage gameWindow) {
        Label label= new Label("Write your nickname to start");
        label.setTextFill( Color.RED );
        label.setFont(this.h2Font);
        TextField nicknameTextfield = new TextField("Nickname has to be between 3 and 8 characters");
        nicknameTextfield.setMaxWidth(300); 
        Button button1= new Button("Start the Game!");
        button1.setOnAction((ActionEvent e) -> { // listener to see if nickname is too short or long
            if (nicknameTextfield.getText().length() < 3 || 
                nicknameTextfield.getText().length() > 8) 
            {
                label.setText("Nickname has to be between 3 and 8 characters");
            } else {
                gameMotor.setUsername(nicknameTextfield.getText()); 
                gameWindow.setScene(theGameScene); // this changes the game scene on
            }
        }); 
        VBox middleSet = new VBox();
        middleSet.setSpacing(10);
        middleSet.getChildren().addAll(label, nicknameTextfield, button1);
        middleSet.setAlignment(Pos.CENTER);
        return middleSet;
    }
    
    private void initGameScene() {
        theGameScene = new Scene(root);
                       
        gameCanvas = new Canvas(gameMotor.getWidth(), gameMotor.getHeight());
        root.getChildren().add(gameCanvas);
        
        this.graphicsContext = gameCanvas.getGraphicsContext2D();
        graphicsContext.drawImage( Render.background, 0, 0 );
        theGameScene.setOnKeyPressed((KeyEvent e) -> {
            String code = e.getCode().toString();
            if ( !input.contains(code) ) // the key press should be added only once
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
                this.newStartScreen(); // this is the screen shown to player if he/she hits any Pipes or bottom and the game stops
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
            graphicsContext.setFont( h2Font );            
            graphicsContext.fillText( "Points: "+ gameMotor.getPoints(), 50, 50 );
            graphicsContext.fillText( "User: "+ gameMotor.getUsername(), 50, 20 );
            graphicsContext.fillText( "Highscore: "+ gameMotor.getHighscore(), gameMotor.getWidth()-200, 50 );
            graphicsContext.fillText( "User: "+ gameMotor.getAllTimePlayer(), gameMotor.getWidth()-200, 20 );
            
            if(!gameMotor.getIsTheGameRunning()) {
            graphicsContext.setStroke( Color.BLACK );
            graphicsContext.setLineWidth(2);
            graphicsContext.setFont( h1Font );
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

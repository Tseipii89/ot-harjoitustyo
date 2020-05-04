
package ui;


import domain.Game;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Render extends Application {
    private final Group root;
    private final GraphicsContext graphicsContext;
    private final ArrayList<String> input;
    private static Game gameMotor;
    public static Image background;
    private Scene startScene;
    private final Scene highscoreScene; 
    private final Scene theGameScene;
    public Canvas gameCanvas;
    Button startGameButton;
    private final ToggleGroup levelGroup;
    private HBox levelSelection;
    private final VBox highscoreVBox;
    private final Stage popup;
    private final Font h1Font =  Font.font( "Times New Roman", FontWeight.BOLD, 42 );
    private final Font h2Font =  Font.font( "Times New Roman", FontWeight.BOLD, 32 );
    private final Font h3Font =  Font.font( "Times New Roman", FontWeight.BOLD, 24 );
    
    public Render(Game gameMotor) {
        this.root = new Group();
        this.input = new ArrayList<>();
        Render.gameMotor = gameMotor;
        Render.background = new Image( "/images/flappybirdtausta.png" );
        levelGroup = new ToggleGroup();
        theGameScene = new Scene(root);
        this.getLevels();
        gameCanvas = new Canvas(gameMotor.getWidth(), gameMotor.getHeight());
        root.getChildren().add(gameCanvas);
        this.graphicsContext = gameCanvas.getGraphicsContext2D();
        highscoreVBox = new VBox();
        highscoreScene = new Scene(highscoreVBox,300,200); 
        popup  = new Stage();
        this.initHighscorecene();
    }

    @Override
    public void start(Stage gameWindow)  {
        gameWindow.setTitle("Flappy Bird- flapity flap: UP-arrow to bounce the birdie!");
        this.starterScene(gameWindow); // Game inits with the startscene where the user is asked to give nickname input
        initGameScene();
        this.newWaitForUpArrowScreen(gameWindow);
    }
    
    private void starterScene(Stage gameWindow) {
        startScene = new Scene(this.setStarterUIElementsPositions(gameWindow), gameMotor.getWidth(), gameMotor.getHeight());
        gameWindow.setScene(startScene);
        gameWindow.show();
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
        label.setFont(this.h3Font);
        TextField nicknameTextfield = new TextField();
        if(gameMotor.getUsername().isEmpty()) {
            nicknameTextfield.setText("Nickname has to be between 3 and 8 characters");
        } else {
            nicknameTextfield.setText(gameMotor.getUsername());
        }
        nicknameTextfield.setMaxWidth(300); 
        startGameButton= new Button("Start the Game!");

        VBox middleSet = new VBox();
        middleSet.setSpacing(10);
        middleSet.getChildren().addAll(label, nicknameTextfield, levelSelection, startGameButton);
        middleSet.setAlignment(Pos.CENTER);
        
        startGameButton.setOnAction((ActionEvent e) -> { // listener to see if nickname is too short or long
            if (nicknameTextfield.getText().length() < 3 || 
                nicknameTextfield.getText().length() > 8) 
            {
                label.setText("Nickname has to be between 3 and 8 characters");
            } else {
                this.setLevel();
                gameMotor.setUsername(nicknameTextfield.getText()); 
                input.remove("SPACE");
                gameWindow.setScene(theGameScene); // this changes the game scene on
                gameWindow.show();
            }
        }); 
        return middleSet;
    }
    
    private void getLevels() {
        
        HBox levelButtons = new HBox();
        
        RadioButton rbEasy = new RadioButton("Easy");
        rbEasy.setToggleGroup(levelGroup);
        rbEasy.setSelected(true);

        RadioButton rbMedium = new RadioButton("Medium");
        rbMedium.setToggleGroup(levelGroup);

        RadioButton rbHard = new RadioButton("Hard");
        rbHard.setToggleGroup(levelGroup);
        
        levelButtons.setSpacing(10);
        levelButtons.getChildren().addAll(rbEasy, rbMedium, rbHard);
        levelButtons.setAlignment(Pos.CENTER);
        
        this.levelSelection = levelButtons;
    }
    
    private void setLevel() {
        RadioButton rb = (RadioButton)levelGroup.getSelectedToggle();
        try {
            gameMotor.setLevel(rb.getText());
        } catch (IOException ex) {
            // no worries the game just plays with easy difficulty
            Logger.getLogger(Render.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initGameScene() {


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
    
    private void initHighscorecene() {
        Text newHighscoreText = new Text("You made a new highscore!!!");
        Text whatALadText = new Text("What a lad!");

        highscoreVBox.getChildren().addAll(newHighscoreText, whatALadText); 
        highscoreVBox.setSpacing(10);
        highscoreVBox.setStyle("-fx-padding: 10;" +
        "-fx-border-style: solid inside;" +
        "-fx-border-width: 2;" +
        "-fx-border-insets: 5;" +
        "-fx-border-radius: 5;" +
        "-fx-border-color: blue;");  
        
        // configure UI for popup etc...
        popup.setScene(highscoreScene);
        // Set the title of the Stage
        popup.setTitle("A new highscore!");
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
        private void newStartScreen() {
            this.setText();
            
            if(input.contains("UP")){
               gameMotor.reset();
               gameMotor.setTheGameRunning(true);
            }
            
            if(input.contains("SPACE")){
               gameWindow.setScene(startScene);
            }

        }
        
        private void setText() {
            graphicsContext.setFill( Color.RED );
            graphicsContext.setFont(h3Font );            
            graphicsContext.fillText( "Points: "+ gameMotor.getPoints(), 50, 50 );
            graphicsContext.fillText( "User: "+ gameMotor.getUsername(), 50, 20 );
            graphicsContext.fillText( "Highscore: "+ gameMotor.getHighscore(), gameMotor.getWidth()-200, 50 );
            graphicsContext.fillText( "User: "+ gameMotor.getAllTimePlayer(), gameMotor.getWidth()-200, 20 );
            
            if(!gameMotor.getIsTheGameRunning() && gameMotor.getWasThisNewhighscore()) {
                stop();
                // Display the Stage
                // hide popup after 3 seconds:
                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished((ActionEvent e) -> {
                    popup.close(); start(); gameMotor.setWasThisNewhighscore(false);
                });
                popup.show();
                delay.play(); 
                
            }
            
            if(!gameMotor.getIsTheGameRunning()) {
            graphicsContext.setStroke( Color.BLACK );
            graphicsContext.setLineWidth(2);
            graphicsContext.setFont( h1Font );
                graphicsContext.fillText( "Start a new game by \n"
                        + "pressing UP -arrow \n"
                        + "Select new difficuylty or name by \n"
                        + "pressing SPACE", 150, 200 );
                graphicsContext.strokeText( "Start a new game by \n"
                        + "pressing UP -arrow \n"
                        + "Select new difficuylty or name by \n"
                        + "pressing SPACE", 150, 200 );
            }
            
        }
        }.start();
        gameWindow.show();
    }

    
}

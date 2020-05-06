
package domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.canvas.GraphicsContext;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GameTest {
    
    private final Bird testBirdie;
    private final Game testGame;
    private final Nickname easyTestNickname;
    private final Nickname mediumTestNickname;
    private final Nickname hardTestNickname;
    private final FakeHighscoreDAO testHighscoreDAO;
    
    public GameTest() throws FileNotFoundException {
        testBirdie = new Bird(null, 600, 800);
        easyTestNickname = new Nickname("Easy", 10);
        mediumTestNickname = new Nickname("Medium", 100);
        hardTestNickname = new Nickname("Hard", 1000);
        testHighscoreDAO = new FakeHighscoreDAO(easyTestNickname, mediumTestNickname, hardTestNickname);
        testGame = new Game(testBirdie, 600, 800, testHighscoreDAO);
    }
    
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void startGameAddsRightAmountOfPipes() {
        //There should be 4 top pipes and 4 lower pipes at the beginning
        assertThat(testGame.getThePipes().size(), is(8));
    }
    
    
    /*
    * GAME PIPE TESTS
    */
    
    // The Pipe hole should only be updated when Pipe has move out of the screen on the left side
    @Test
    public void gameUpdatesTopPipeHeightRandomlyWhenPipeHasMoveOutScreen() {
        // First we remove the 8 Pipes created at the beginning
        testGame.getThePipes().clear();
        
        // int positionX, int positionY, int height, int width, int motionX, boolean top
        Pipe testPipe = new Pipe(-60, 0, 50, 50, 10, true);
        testGame.setThePipes(testPipe);
        
        // Now testing set-up is ready and testing can start
        testGame.updatePipes();
        assertThat(testPipe.getHeight(), not(50));
    }
    
    @Test
    public void gameUpdatesBottomPipeHeightRandomlyWhenPipeHasMoveOutScreen() {
        // First we remove the 8 Pipes created at the beginning
        testGame.getThePipes().clear();
        
        // int positionX, int positionY, int height, int width, int motionX, boolean top
        Pipe testPipe = new Pipe(-60, 0, 50, 50, 10, false);
        testGame.setThePipes(testPipe);
        
        // Now testing set-up is ready and testing can start
        testGame.updatePipes();
        
        
        assertTrue(testPipe.getPositionY() > testGame.getHeight()/2);
    }
    
    @Test
    public void resetAddsRigthAmountOfNewPipes() {

        testGame.reset();
        
        assertThat(testGame.getThePipes().size(), is(8));
    }
    
    @Test
    public void PipesAreRendered() {
        // First we remove the 8 Pipes created at the beginning
        testGame.getThePipes().clear();
        
        // int positionX, int positionY, int height, int width, int motionX, boolean top
        Pipe testPipe = new Pipe(200, 0, 50, 50, 10, true);
        testGame.setThePipes(testPipe);
        GraphicsContext graphicsContext = null;
        
        testGame.drawPipes(graphicsContext, 1);
        
        assertThat(testGame.getThePipes().get(0).getPositionX(), is(190));
    }
    
    /*
    * GAME SCORING TESTS
    */
    
    @Test
    public void gameDoesntScoreifPipeIsntPastBird() {
        // First we remove the 8 Pipes created at the beginning
        testGame.getThePipes().clear();
        
        // int positionX, int positionY, int height, int width, int motionX, boolean top
        Pipe testPipe = new Pipe(300, 0, 50, 50, 10, true);
        testGame.setThePipes(testPipe);
        
        testGame.countScore();
        
        assertThat(testGame.getPoints(), is(0));
    }
    
    @Test
    public void gameScoresifTopPipePastBird() {
        // First we remove the 8 Pipes created at the beginning
        testGame.getThePipes().clear();
        
        // int positionX, int positionY, int height, int width, int motionX, boolean top
        Pipe testPipe = new Pipe(100, 0, 50, 50, 10, true);
        testGame.setThePipes(testPipe);
        
        testGame.countScore();
        
        assertThat(testGame.getPoints(), is(1));
    }
    
    @Test
    public void gameSavesNewHighscore() {
        testGame.setScore(11);
        
        testGame.countScore();
        
        assertThat(testGame.getHighscore(), is(11));
    }
    
    /*
    * GAME LEVEL TESTS
    */
    
    @Test
    public void gameSetsEasySpeedLevelUserChosen() throws IOException {

        testGame.setPipeSpeed(0);
        
        testGame.setLevel("Easy");
        
        assertThat(testGame.getPipeSpeed(), is(2));
    }
    
    @Test
    public void gameSetsMediumSpeedLevelUserChosen() throws IOException {

        testGame.setPipeSpeed(0);
        
        testGame.setLevel("Medium");
        
        assertThat(testGame.getPipeSpeed(), is(3));
    }
    
    @Test
    public void gameSetsHardSpeedLevelUserChosen() throws IOException {

        testGame.setPipeSpeed(0);
        
        testGame.setLevel("Hard");
        
        assertThat(testGame.getPipeSpeed(), is(4));
    }
    
    /*
    * GAME BIRD TESTS
    */
    
    @Test
    public void gameOffIfBirdHitsBottom() {

        testGame.getTheGameBird().setPositionY(-16);
        testGame.checkIfGameOn();
        
        assertThat(testGame.getIsTheGameRunning(), is(false));
    }
    
    @Test
    public void BirdCantGoThroughTheRoof() {

        testGame.getTheGameBird().setPositionY(0);
        
        assertThat(testGame.birdNotOutOfField(), is(false));
    }

}

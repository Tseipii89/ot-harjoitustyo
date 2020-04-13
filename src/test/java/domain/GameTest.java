
package domain;

import java.io.FileNotFoundException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GameTest {
    
    private final Bird testBirdie;
    private final Game testGame;
    private final Nickname testNickname;
    private final FakeHighscoreDAO testHighscoreDAO;
    
    public GameTest() throws FileNotFoundException {
        testBirdie = new Bird(600, 800);
        testNickname = new Nickname("Tester", 10);
        testHighscoreDAO = new FakeHighscoreDAO(testNickname);
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
    
    
    // The Pipe hole should only be updated when Pipe has move out of the screen on the left side
    @Test
    public void gameUpdatesPipeHeightRandomlyWhenPipeHasMoveOutScreen() {
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
    public void resetAddsRigthAmountOfNewPipes() {

        testGame.reset();
        
        assertThat(testGame.getThePipes().size(), is(8));
    }

}

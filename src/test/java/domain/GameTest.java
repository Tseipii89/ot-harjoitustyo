
package domain;

import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GameTest {
    
    private final Bird testBirdie;
    private final Game testGame;
    
    public GameTest() {
        testBirdie = new Bird(600, 800);
        testGame = new Game(testBirdie);
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

}

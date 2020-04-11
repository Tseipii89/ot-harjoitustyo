package domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhop
 */
public class BirdTest {
    
    private Bird testBirdie;
    
    public BirdTest() {
    }
    
    
    @Before
    public void setUp() {
        testBirdie = new Bird(600, 800);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void birdJumpMethodNullifiesMotion() {
        testBirdie.setMotionY(40);
        testBirdie.birdJump();
        assertThat(0, is(testBirdie.getMotionY()));
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhop
 */
public class FileHighscoreDaoTest {
    
    private FileHighscoreDao testFilehighscoreDao;
    
    public FileHighscoreDaoTest() throws IOException {
        File testEmptyHighscore = new File("tesInitHighscore.txt");
        try (FileWriter writer = new FileWriter(testEmptyHighscore)) {
            writer.write("easy;1;medium;2;hard;3");
        }
        testFilehighscoreDao = new FileHighscoreDao(testEmptyHighscore);
    }
    
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {

    }

    /*** SETUP TESTS
     * @throws java.io.IOException ***/
    
    @Test
    public void setupWithoutFile() throws IOException {
        
        File testEmptyHighscore = new File("testEmptyHighscore.txt");
        testEmptyHighscore.deleteOnExit();
        FileHighscoreDao testWithoutfileDAO = new FileHighscoreDao(testEmptyHighscore);

        assertThat(testWithoutfileDAO.readNickname(1), is("none"));
        assertThat(testWithoutfileDAO.readHighscore(1), is(0));

    }
    
    @Test
    public void setupWithFile() throws IOException {
        

        assertThat(testFilehighscoreDao.readNickname(1), is("easy"));
        assertThat(testFilehighscoreDao.readHighscore(1), is(1));
        assertThat(testFilehighscoreDao.readNickname(2), is("medium"));
        assertThat(testFilehighscoreDao.readHighscore(2), is(2));
        assertThat(testFilehighscoreDao.readNickname(3), is("hard"));
        assertThat(testFilehighscoreDao.readHighscore(3), is(3));

    }
    
    
}

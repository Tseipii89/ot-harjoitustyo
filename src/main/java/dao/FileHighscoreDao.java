
package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import domain.Nickname;
import java.io.FileNotFoundException;

/**
 * This class implements Highscore Data access object. 
 * This class provides an easy way to save highscore and nickname into a file that is provided to this class 
 * 
 * @see HighscoreDao
 * @author juhop
 */
public class FileHighscoreDao implements HighscoreDao {


    private File file;

 
    private Nickname nickname;
    
    /**
     *
     * This class reads and updates a file with the infromation about the highscore and nickname that got it.
     * The file that is used is provided in the constructor. 
     * 
     * @see domain.Starter#start(javafx.stage.Stage) 
     * 
     * @param file the file used to score the highscore and nickname that got it
     * 
     * @throws IOException If there are no lines (or there is no such file) in the provided constructor, 
     * this class will create the file with information "highscore = 0" and "nickname = none"
     */
    public FileHighscoreDao(File file) throws IOException {
        this.file = file;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                this.nickname = new Nickname(parts[0], Integer.parseInt(parts[1]));
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            FileWriter writer = new FileWriter(file);
            writer.write("none;0"); 
            writer.close();
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                this.nickname = new Nickname(parts[0], Integer.parseInt(parts[1]));
            }
        }
    }
    
    /**
     *
     * Method provides the current highscore.
     * 
     * @return the integer value of highscore that is written in the file provided to this constructor
     */
    @Override
    public Integer readHighscore() {
        return this.nickname.highscore;
    }

    /**
     *
     * Method provides the nickname of the highscore holder.
     * 
     * @return the string value of nickname that is written in the file provided to this constructor
     */
    @Override
    public Object readNickname() {
        return this.nickname.name;
    }
    
    /**
     *
     * Method updates the highscore and nickname with the object given in the constructor.
     * Class Game handles the update method call.
     * 
     * @see    domain.Game#reset() 
     * 
     * @param object the object to be updated as highscore holder and new highscore
     * 
     * @return true if update succeed or false if it didn't
     */
    @Override
    public boolean update(Object object) {
        this.nickname = (Nickname) object;
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(nickname.name + ";" + Integer.toString(nickname.highscore)); 
            return true;
        } catch (IOException ex) {
            return false;
        } 
    }
    
}

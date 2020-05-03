
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

 
    private Nickname easyNickname;
    private Nickname mediumNickname;
    private Nickname hardNickname;
    
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
                this.easyNickname = new Nickname(parts[0], Integer.parseInt(parts[1]));
                this.mediumNickname = new Nickname(parts[2], Integer.parseInt(parts[3]));
                this.hardNickname = new Nickname(parts[4], Integer.parseInt(parts[5]));
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            FileWriter writer = new FileWriter(file);
            writer.write("none;0;none;0;none;0"); 
            writer.close();
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                this.easyNickname = new Nickname(parts[0], Integer.parseInt(parts[1]));
                this.mediumNickname = new Nickname(parts[2], Integer.parseInt(parts[3]));
                this.hardNickname = new Nickname(parts[4], Integer.parseInt(parts[5]));
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
    public Integer readHighscore(int level) {
        switch (level) {
            case 1:
              return this.easyNickname.getHighscore();
            case 2:
              return this.mediumNickname.getHighscore();
            default:
              return this.hardNickname.getHighscore();
        }  
    }

    /**
     *
     * Method provides the nickname of the highscore holder.
     * 
     * @return the string value of nickname that is written in the file provided to this constructor
     */
    @Override
    public Object readNickname(int level) {
        switch (level) {
            case 1:
              return this.easyNickname.getName();
            case 2:
              return this.mediumNickname.getName();
            default:
              return this.hardNickname.getName();
        }  
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
    public boolean update(Object object, int level) {
         switch (level) {
            case 1:
                this.easyNickname = (Nickname) object;
                return this.writeUpdate(easyNickname);
            case 2:
                this.mediumNickname = (Nickname) object;
                return this.writeUpdate(mediumNickname);
            default:
                this.hardNickname = (Nickname) object;
                return this.writeUpdate(hardNickname);
        }
 
    }
    
    private boolean writeUpdate(Nickname nickname) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(nickname.getName() + ";" + Integer.toString(nickname.getHighscore())); 
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
}

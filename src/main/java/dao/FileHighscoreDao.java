
package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import domain.Nickname;
import java.io.FileNotFoundException;


public class FileHighscoreDao implements HighscoreDao {

    private File file;
    private Nickname nickname;
    
    public FileHighscoreDao(File file) throws IOException {
        this.file = file;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                this.nickname = new Nickname(parts[0],Integer.parseInt(parts[1]));
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            FileWriter writer = new FileWriter(file);
            writer.write("none;0"); 
            writer.close();
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                this.nickname = new Nickname(parts[0],Integer.parseInt(parts[1]));
            }
        }
    }
    
    @Override
    public Integer ReadHighscore() {
        return this.nickname.highscore;
    }

    @Override
    public Object ReadNickname() {
        return this.nickname.name;
    }
    
    @Override
    public boolean Update(Object object) {
        this.nickname = (Nickname) object;
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(nickname.name + ";" + Integer.toString(nickname.highscore)); 
            return true;
        } catch (IOException ex) {
            return false;
        } 
    }
    
}

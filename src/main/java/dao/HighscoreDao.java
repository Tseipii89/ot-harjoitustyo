
package dao;

import domain.Nickname;


public interface HighscoreDao<Highscore> {
    
    
    // Returns current highscore and the highscore keeper
    Integer ReadHighscore();
    Highscore ReadNickname();
    
    // Update highscore and the highscore holder
    boolean Update(Highscore object);
    
}

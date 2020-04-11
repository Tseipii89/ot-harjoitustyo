
package dao;

/**
 * Interface to handle the saving, reading and updating of current highscore and the highscore keeper.
 * 
 * @author juhop
 * 
 * @param <Highscore> Abstract object that is supposed to contain the info about highscore and nickname
 */
public interface HighscoreDao<Highscore> {
    

    /**
     *
     * Method returns the current highscore.
     * 
     * @return highscore as an integer value
     */
    Integer readHighscore();

    /**
     *
     * Method returns the nickname of the score keeper.
     * 
     * @return abstract class highscore
     */
    Highscore readNickname();
    

    /**
     *
     * update highscore and the highscore holder.
     * 
     * @param object abstract object highscore
     * @return boolean value if the update succeeded or not
     */
    boolean update(Highscore object);
    
}

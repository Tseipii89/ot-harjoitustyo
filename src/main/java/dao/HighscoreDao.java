
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
     * @param level is the difficulty setting for the game. Each difficulty has it's own highscore and player.
     * @return highscore as an integer value
     */
    Integer readHighscore(int level);

    /**
     *
     * Method returns the nickname of the score keeper.
     * 
     * @param level is the difficulty setting for the game. Each difficulty has it's own highscore and player.
     * @return abstract class highscore
     */
    Highscore readNickname(int level);
    

    /**
     *
     * update highscore and the highscore holder.
     * 
     * @param object abstract object highscore
     * @param level is the difficulty setting for the game. Each difficulty has it's own highscore and player.
     * @return boolean value if the update succeeded or not
     */
    boolean update(Highscore object, int level);
    
}

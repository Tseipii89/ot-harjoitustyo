
package domain;

/**
 *
 * Class is used to score easyHighscore and the name of easyHighscore holder.
 * Class is used by the DAO object.
 * @see dao.FileHighscoreDao
 * 
 * @author juhop
 */
public class Nickname {
    

    private final String name;

    private final int highscore;
    
    /**
     *
     * Sets the name and easyHighscore for this specific Nickname object.
     * 
     * @param name username of the user with given score
     * @param score score this player got
     */
    public Nickname(String name, int score) {
        this.name = name;
        this.highscore = score;
    }
    
    /**
     *
     * Returns the name of the Nickname object.
     * 
     * @return the name affiliated to this Nickname object
     */
    public String getName() {
        return this.name;
    }
    
    /**
     *
     * Returns the easyHighscore of the Nickname object.
     * 
     * @return the easyHighscore affiliated to this Nickname object
     */
    public int getHighscore() {
        return this.highscore;
    }
    
}

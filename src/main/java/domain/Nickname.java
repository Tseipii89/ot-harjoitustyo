
package domain;

/**
 *
 * Class is used to score highscore and the name of highscore holder.
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
     * Sets the name and highscore for this specific Nickname object.
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
     * Returns the highscore of the Nickname object.
     * 
     * @return the highscore affiliated to this Nickname object
     */
    public int getHighscore() {
        return this.highscore;
    }
    
}

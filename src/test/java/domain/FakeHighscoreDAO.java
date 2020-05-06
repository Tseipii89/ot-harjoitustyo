
package domain;

import dao.HighscoreDao;

public class FakeHighscoreDAO implements HighscoreDao {
    
    private Nickname easyNickname;
    private Nickname mediumNickname;
    private Nickname hardNickname;
    
    public FakeHighscoreDAO (Nickname easynickname, Nickname mediumNickname, Nickname hardNickname) {
        this.easyNickname = easynickname;
        this.mediumNickname = mediumNickname;
        this.hardNickname = hardNickname;
    }

    @Override
    public Integer readHighscore(int level) {
        switch (level) {
                case 1:
                    return this.easyNickname.getHighscore();
                case 2:
                    return this.mediumNickname.getHighscore();
                case 3:
                    return this.hardNickname.getHighscore();
                default:
                    return this.easyNickname.getHighscore();
        }            
    }

    @Override
    public Object readNickname(int level) {
        switch (level) {
                case 1:
                    return this.easyNickname.getName();
                case 2:
                    return this.mediumNickname.getName();
                case 3:
                    return this.hardNickname.getName();
                default:
                    return this.easyNickname.getName();
        }
    }

    @Override
    public boolean update(Object object, int level) {
        switch (level) {
                case 1:
                    this.easyNickname = (Nickname) object;
                case 2:
                    this.mediumNickname = (Nickname) object;
                case 3:
                    this.hardNickname = (Nickname) object;
                default:
                    this.easyNickname = (Nickname) object;
        }
        return true;
    }
    
}

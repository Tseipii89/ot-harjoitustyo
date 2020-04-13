
package domain;

import dao.HighscoreDao;

public class FakeHighscoreDAO implements HighscoreDao {
    
    private Nickname nickname;
    
    public FakeHighscoreDAO (Nickname nickname) {
        this.nickname = nickname;
    }

    @Override
    public Integer readHighscore() {
        return this.nickname.getHighscore();
    }

    @Override
    public Object readNickname() {
        return this.nickname.getName();
    }

    @Override
    public boolean update(Object object) {
        this.nickname = (Nickname) object;
        return true;
    }
    
}

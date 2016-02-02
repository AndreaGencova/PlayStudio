package GameStudio.score;

import java.util.List;

public interface HallOfFameService {

	public abstract void addScore(String name, int time) throws Exception;

	public abstract List<UserScore> loadScore() throws Exception;

}

package GameStudio.score;

import java.util.ArrayList;
import java.util.List;

public abstract class HallOfFameAbs implements HallOfFameService {
	private String game;

	public HallOfFameAbs(String game) {
		this.game = game;
	}

	@Override
	public String toString() {
		List<UserScore> scores;
		try {
			scores = loadScore();
		} catch (Exception e) {
			scores = new ArrayList<>();
		}

		StringBuilder sb = new StringBuilder();
		int index = 1;
		for (UserScore score : scores) {
			sb.append(index + ". " + score.getName() + " " + score.getTime() + "\n");
			index++;
		}
		return sb.toString();
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}
}
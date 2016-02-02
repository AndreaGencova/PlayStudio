package GameStudio.console;

import GameStudio.comment.CommentService;
import GameStudio.rating.RatingService;
import GameStudio.score.HallOfFameService;

public abstract class MainConsole {

	private HallOfFameService hallOfFame;
	private RatingService rating;
	private CommentService comment;

	public HallOfFameService getHallOfFame() {
		return hallOfFame;
	}

	public void setHallOfFame(HallOfFameService hallOfFame) {
		this.hallOfFame = hallOfFame;
	}

	public RatingService getRating() {
		return rating;
	}

	public void setRating(RatingService rating) {
		this.rating = rating;
	}

	public CommentService getComment() {
		return comment;
	}

	public void setComment(CommentService comment) {
		this.comment = comment;
	}

	public double showRating() {
		try {
			return rating.averageByAgragationFunction(getGameName());
		} catch (Exception e) {
			System.err.println("Average rating is not available");
		}
		return 0;
	}

	public Long showVoters() {
		try {
			return rating.countOfVoters(getGameName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public abstract String getGameName();

}

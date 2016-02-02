package GameStudio.rating;

public interface RatingService {
	public abstract void setRating(String name, String game, int rating) throws Exception;

	public abstract double averageByAgragationFunction(String game) throws Exception;

	public abstract Long countOfVoters(String game) throws Exception;
}

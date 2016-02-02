package GameStudio.rating;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "game")
	private String game;

	@Column(name = "user")
	private String user;

	@Column(name = "rating")
	private int rating;

	public Rating() {

	}

	public Rating(String game, String user, int rating) {
		super();
		this.game = game;
		this.user = user;

		if (rating > 10 && rating < 0) {
			System.err.println("Rating must be in range 0-10");
		} else {
			this.rating = rating;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Rating [game=" + game + ", user=" + user + ", rating=" + rating + "]";
	}

}

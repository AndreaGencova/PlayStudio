package GameStudio.comment;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "user")
	private String user;

	@Column(name = "game")
	private String game;

	@Column(name = "comment")
	private String comment;

	public Comment() {
	}

	public Comment(String user, String game, String comment) {
		super();
		this.user = user;
		this.game = game;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Comment: " + user + ", " + game + ", " + comment;
	}

	public String toStringArray(List<Comment> com) {
		StringBuilder sb = new StringBuilder();

		for (Comment c : com){
			sb.append(c.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}

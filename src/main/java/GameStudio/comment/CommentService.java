package GameStudio.comment;

import java.util.List;

public interface CommentService {
	public abstract void addComment(String name, String game, String commentar) throws Exception;

	public abstract List<Comment> loadComment() throws Exception;
}

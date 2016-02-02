package GameStudio.comment;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import GameStudio.score.UserScore;

public class CommentServiceImpl implements CommentService {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public void addComment(String name, String game, String commentar) throws Exception {
		em.persist(new Comment(name, game, commentar));
		System.out.println("Comment was added successfully");
	}

	@Override
	public List<Comment> loadComment() throws Exception {
		return em.createQuery("select c from Comment c").getResultList();

	}

}

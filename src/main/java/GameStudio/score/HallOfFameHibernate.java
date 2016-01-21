package GameStudio.score;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import GameStudio.score.details.Comment;
import GameStudio.score.details.Rating;

//@Component
public class HallOfFameHibernate extends HallOfFame {

	public HallOfFameHibernate(String game) {
		super(game);
	}

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public void addScore(String name, int time) throws Exception {
		em.persist(new UserScore(name, time, getGame()));

	}

	@Transactional
	@Override
	public List<UserScore> loadScore() throws Exception {
		return em.createQuery("select s from UserScore s where s.game = :name order by time", UserScore.class)
				.setParameter("name", getGame()).getResultList();
	}

	@Transactional
	@Override
	public void loadComment(String name, String game, String commentar) throws Exception {
		em.persist(new Comment(name, game, commentar));
		System.out.println("Comment was added successfully");
	}

	@Transactional
	@Override
	public void setRating(String name, String game, int rating) throws Exception {
		List<Rating> list = em.createQuery("select r from Rating r where game = :game and user = :name", Rating.class)
				.setParameter("game", game).setParameter("name", name).getResultList();

		if (list.isEmpty())
			em.persist(new Rating(game, name, rating));
		else
			list.get(0).setRating(rating);
	}

	@Transactional
	@Override
	public double averageByAgragationFunction(String game) throws Exception {
		Query q = em.createQuery("select avg(r.rating) from Rating r where game = :game").setParameter("game", game);
		Double result = (Double) q.getSingleResult();
		return result;
	}

	@Transactional
	@Override
	public Long countOfVoters(String game) throws Exception {
		return (Long) em.createQuery("select count(r) from Rating r where game = :game").setParameter("game", game)
				.getSingleResult();
	}

}

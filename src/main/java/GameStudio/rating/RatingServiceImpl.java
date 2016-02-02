package GameStudio.rating;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

public class RatingServiceImpl implements RatingService {

	@PersistenceContext
	private EntityManager em;

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

		try {
			Double result = (Double) q.getSingleResult();
			if (result != null)
				return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0;

	}

	@Transactional
	@Override
	public Long countOfVoters(String game) throws Exception {
		return (Long) em.createQuery("select count(r) from Rating r where game = :game").setParameter("game", game)
				.getSingleResult();

	}
}

package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   private static final String GET_USER_BY_CAR = "from User user where user.car.model = :model and user.car.series = :series";


   @Override
   public void createUser(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> retrieveALLUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User findUserByCar(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(GET_USER_BY_CAR);
      query.setParameter("model", model).setParameter("series", series);
      return query.setMaxResults(1).getSingleResult();
   }

}

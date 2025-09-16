package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void createUser(User user);
   List<User> retrieveALLUsers();
   User findUserByCar(String model, int series);
}

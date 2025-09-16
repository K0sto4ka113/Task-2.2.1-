package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    List<User> retrieveALLUsers();
    User findUserByCar(String model, int series);
}

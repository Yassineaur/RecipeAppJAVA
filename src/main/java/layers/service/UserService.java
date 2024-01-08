package layers.service;

import layers.entity.Recipe;
import layers.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int theId);

    User findByUsername(String username);

    User login(String username, String password);

    void save(User theEmployee);

    void update(User user);
    void updateUser(int userId, String username, String password);

    void deleteById(int theId);
}

package layers.service;

import layers.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int theId);

    void save(User theEmployee);

    User update(User theEmployee);

    void deleteById(int theId);
}

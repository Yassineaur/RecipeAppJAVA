package layers.service;

import layers.dao.UserRepository;
import layers.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository TheUserRepository) {
        userRepository = TheUserRepository;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int theId) {
        return null;
    }

    @Override
    public void save(User theEmployee) {

    }

    @Override
    public User update(User theEmployee) {
        return null;
    }

    @Override
    public void deleteById(int theId) {

    }
}

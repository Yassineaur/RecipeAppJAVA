package layers.service;

import layers.dao.UserRepository;
import layers.entity.Recipe;
import layers.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<User> result = userRepository.findById(theId);
        User user = null;
        if(result.isPresent()){
            user = result.get();
            return user;
        }else {
            return user;
        }
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> result = userRepository.findByUsername(username);
        User user = null;
        if(result.isPresent()){
            user = result.get();
            return user;
        }else {
            return user;
        }
    }

    @Override
    public User login(String username, String password) {
        Optional<User> result = userRepository.login(username, password);
        User user = null;
        if(result.isPresent()){
            user = result.get();
            return user;
        }else {
            return user;
        }

    }

    @Override
    public void save(User theUser) {
         userRepository.save(theUser);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }


    @Override
    public void updateUser(int userId, String username, String password) {
        userRepository.updateUserCredentials(userId, username, password);
    }

    @Override
    public void deleteById(int theId) {
   userRepository.deleteById(theId);
    }
}

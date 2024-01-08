package layers.dao;

import layers.entity.Recipe;
import layers.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :pass")
    Optional<User> login(@Param("username") String username, @Param("pass") String pass);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);


    @Modifying
    @Query("UPDATE User u SET u.username = :newUsername, u.password = :newPassword WHERE u.id = :userId")
    void updateUserCredentials(
            @Param("userId") int userId,
            @Param("newUsername") String newUsername,
            @Param("newPassword") String newPassword
    );

}

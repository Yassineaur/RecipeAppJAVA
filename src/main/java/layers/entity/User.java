package layers.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // Example: Specifying column name as 'user_id'
    private Long id;

    @Column(name = "user_username", nullable = false, unique = true) // Example: Specifying column name as 'user_username', making it non-nullable and unique
    private String username;

    @Column(name = "user_password", nullable = false) // Example: Specifying column name as 'user_password' and making it non-nullable
    private String password;

    @ManyToMany(mappedBy = "favoritedByUsers")
    private List<Recipe> favoriteRecipes;

    public User() {
    }

    public User(Long id, String username, String password, List<Recipe> favoriteRecipes) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.favoriteRecipes = favoriteRecipes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", favoriteRecipes=" + favoriteRecipes +
                '}';
    }
}


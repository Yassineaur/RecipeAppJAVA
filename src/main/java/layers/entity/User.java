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

    // Other properties like name, email, etc.

    // Constructors, getters, setters, and other methods...

}


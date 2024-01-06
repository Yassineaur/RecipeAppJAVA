package layers.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id") // Example: Specifying column name as 'recipe_id'
    private Long id;

    @Column(name = "recipe_title", nullable = false) // Example: Specifying column name as 'recipe_title' and making it non-nullable
    private String title;

    @Column(name = "recipe_description", length = 1000) // Example: Specifying column name as 'recipe_description' with a maximum length of 1000 characters
    private String description;

    // Other properties like ingredients, steps, etc.

    @OneToMany(mappedBy = "recipe")
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_recipes",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> favoritedByUsers;

    // Constructors, getters, setters, and other methods...

}

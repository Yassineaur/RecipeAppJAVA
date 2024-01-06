package layers.entity;

import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id") // Example: Specifying column name as 'comment_id'
    private Long id;

    @Column(name = "comment_content", nullable = false) // Example: Specifying column name as 'comment_content' and making it non-nullable
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id") // Example: Specifying the join column name as 'user_id'
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id") // Example: Specifying the join column name as 'recipe_id'
    private Recipe recipe;

    // Constructors, getters, setters, and other methods...

}

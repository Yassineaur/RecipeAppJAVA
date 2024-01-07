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

    public Comment() {
    }

    public Comment(Long id, String content, User user, Recipe recipe) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.recipe = recipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", recipe=" + recipe +
                '}';
    }
}

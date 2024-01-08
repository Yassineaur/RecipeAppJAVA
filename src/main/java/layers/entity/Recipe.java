package layers.entity;

import jakarta.persistence.*;

import javax.swing.text.html.HTML;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id") // Example: Specifying column name as 'recipe_id'
    private int id;

    @Column(name = "recipe_title", nullable = false) // Example: Specifying column name as 'recipe_title' and making it non-nullable
    private String title;

    @Column(name = "recipe_description", length = 1000) // Example: Specifying column name as 'recipe_description' with a maximum length of 1000 characters
    private String description;

    // Other properties like ingredients, steps, etc.
    @Column(name = "recipe_publisher", nullable = false) // Example: Specifying column name as 'recipe_title' and making it non-nullable
    private int publisher;

    @OneToMany(mappedBy = "recipe")
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "recipes_tags",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tags> recipeTags;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_recipes",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> favoritedByUsers;

    public Recipe() {
    }

    public Recipe(int id, String title, String description, List<Comment> comments, List<User> favoritedByUsers, List<Tags> tags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.comments = comments;
        this.favoritedByUsers = favoritedByUsers;
        this.recipeTags = tags;
    }

    public List<Tags> getTags() {
        return recipeTags;
    }

    public void setTags(List<Tags> tags) {
        this.recipeTags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public int getPublisher() {
        return publisher;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(int publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getFavoritedByUsers() {
        return favoritedByUsers;
    }

    public void setFavoritedByUsers(List<User> favoritedByUsers) {
        this.favoritedByUsers = favoritedByUsers;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publisher=" + publisher +
                ", comments=" + comments +
                ", favoritedByUsers=" + favoritedByUsers +
                ", tags=" + recipeTags +
                '}';
    }

}

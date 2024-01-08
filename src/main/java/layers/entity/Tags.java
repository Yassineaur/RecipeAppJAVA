package layers.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int id;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToMany(mappedBy = "recipeTags")
    private List<Recipe> recipes;

    public Tags() {
    }

    public Tags(int id, String tagName, List<Recipe> recipes) {
        this.id = id;
        this.tagName = tagName;
        this.recipes = recipes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> favoriteRecipes) {
        this.recipes = favoriteRecipes;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", favoriteRecipes=" + recipes +
                '}';
    }

}

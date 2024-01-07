package layers.service;

import layers.entity.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> findAll();

    Recipe findById(int theId);

    Recipe findByTitle(String title);

    void save(Recipe theEmployee);

    void update(Recipe theEmployee);

    void deleteById(int theId);



}

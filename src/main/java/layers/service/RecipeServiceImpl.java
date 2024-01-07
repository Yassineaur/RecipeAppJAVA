package layers.service;

import layers.dao.RecipeRepository;
import layers.entity.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepositoy;

    @Autowired
    public RecipeServiceImpl(RecipeRepository theRecipeRepository) {
        recipeRepositoy = theRecipeRepository;
    }

    @Override
    public List<Recipe> findAll() {
        return recipeRepositoy.findAll();
    }

    @Override
    public Recipe findById(int theId) {
        Optional<Recipe> result = recipeRepositoy.findById(theId);
        Recipe recipe = null;
        if(result.isPresent()){
            recipe = result.get();
        }else{
            throw  new RuntimeException("Did not find the recipe");
        }
        return recipe;
    }

    @Override
    public void save(Recipe theRecipe) {
        recipeRepositoy.save(theRecipe);
    }

    @Override
    public Recipe findByTitle(String title) {
        Optional<Recipe> result = recipeRepositoy.findByTitle(title);
        Recipe recipe = null;
        if(result.isPresent()){
            recipe = result.get();
        }else{
            throw  new RuntimeException("Did not find the recipe");
        }
        return recipe;
    }

    @Override
    public void update(Recipe theRecipe) {
        recipeRepositoy.save(theRecipe);
    }

    @Override
    public void deleteById(int theId) {
        recipeRepositoy.deleteById(theId);

    }
}

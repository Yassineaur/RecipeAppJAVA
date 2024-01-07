package layers.controller;

import jakarta.servlet.http.HttpSession;
import layers.entity.Recipe;
import layers.entity.Tags;
import layers.entity.User;
import layers.service.RecipeService;
import layers.service.TagsService;
import layers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/Recipe")
public class RecipeController {

    private UserService userServ;
    private RecipeService recipeServ;

    private TagsService tagsService;

    @Autowired
    public RecipeController(UserService userServ, RecipeService recipeServ) {
        this.userServ = userServ;
        this.recipeServ = recipeServ;
    }

    @PostMapping("/publier")
    public String publier(Model theModel, @ModelAttribute("recipe") Recipe theRecipe) {
        recipeServ.save(theRecipe);
     return "home";
    }


    //Modifier les informations d'une recette
    @PostMapping("/modifier")
    public String modifier(Model theModel, @ModelAttribute("recipe") Recipe theRecipe) {
        recipeServ.update(theRecipe);
        return "list-employees";
    }

    //Supprimer ma recette
    @GetMapping("/supprimer")
    public String supprimer(Model theModel,  @RequestParam("recipeId") int recId, HttpSession session) {

        //session check then delete
        Object userId = (HttpSession) session.getAttribute("userId");
        Recipe recipe = recipeServ.findById(recId);
        if(recipe.getPublisher() == (int)userId){
            recipeServ.deleteById(recId);
        }
        return null;
    }

    //Recherche d'une recette par mot clé
    @GetMapping("/rechercher")
    public String rechercher(Model theModel, @RequestParam("recipeInput") String recipeSearchWords, HttpSession session) {
            Recipe recipeToSend = null;
            List<Recipe> recipesToSend = new ArrayList<>();
            if(recipeSearchWords == null){
                return null;
            } else if (recipeSearchWords.contains(" ")) {
                // so search is done by tags / name
                recipeToSend = recipeServ.findByTitle(recipeSearchWords);
                if(recipeToSend == null){
                    // Tags
                    // Iterate over the array and print each word
                    List<String> searchTags = new ArrayList<>(Arrays.asList( recipeSearchWords.split(" ")));

                    for (String word : searchTags) {
                        Tags tag = tagsService.findByName(word);
                        for(Recipe rc : tag.getRecipes()){
                            recipesToSend.add(rc);
                        };

                    }

                    theModel.addAttribute(recipesToSend);
                    return "searchResult";
                }else{
                    // Name (title)
                    theModel.addAttribute(recipeToSend);
                    return "searchResult";
                }

            }else{
                // Search by tag
                if(recipeToSend == null){
                    theModel.addAttribute(recipeToSend);
                    return "searchResult";
                }
                else{
                    return "notFound";
                }
            }
    }


    //Consulter une recette
    @GetMapping("/consulter")
    public String consulter(Model theModel,  @RequestParam("recipeId") int recId) {
        Recipe recipe = recipeServ.findById(recId);
        theModel.addAttribute(recipe);
        return "recipe";
    }

    @GetMapping("/favoris")
    public String favoris(Model theModel, @RequestParam("recipeId") int recId,  HttpSession session) {
        Object userId = (HttpSession) session.getAttribute("userId");
        Recipe recipe = recipeServ.findById(recId);
        User user = userServ.findById((int) userId);
        if (user != null && recipe != null) {
            recipe.getFavoritedByUsers().add(user);
            recipeServ.save(recipe);
        }


        return null;
    }

}
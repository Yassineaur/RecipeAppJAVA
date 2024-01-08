package layers.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import layers.entity.Comment;
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

import java.util.*;

@Controller
@RequestMapping("/recipe")
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
        if(theRecipe.getTitle() != null && theRecipe.getDescription() != null){
            recipeServ.save(theRecipe);
            return "redirect:/recipe/pagedesrecettes";
        }
        else {
            Recipe recipe = new Recipe();
            theModel.addAttribute("recipe", recipe);
            return "publier";
        }

    }

    @GetMapping("/pagepublier")
    public String pageConnexion(Model theModel, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Recipe recipe = new Recipe();
            theModel.addAttribute("recipe", recipe);
            return "publier";
        } else {

            return "redirect:/user/pageconnexion";
        }
    }


    @GetMapping("/formModifier")
    public String formmodifier(Model theModel, @RequestParam("recipeId") int recId) {
        Recipe recipe = recipeServ.findById(recId);
        theModel.addAttribute(recipe);
        return "updateForm";
    }
    //Modifier les informations d'une recette
    @PostMapping("/modifier")
    public String modifier(Model theModel, @ModelAttribute("recipe") Recipe theRecipe, HttpServletRequest request) {
        //session check then update
        HttpSession session = request.getSession(false);
        Object userId = session.getAttribute("userId");
        Recipe recipe = recipeServ.findById(theRecipe.getId());
        if(recipe.getPublisher() == (int)userId){
            recipeServ.update(theRecipe);
            return "accueil";
        }
        else {
            return null;
        }
    }

    //Supprimer ma recette
    @GetMapping("/supprimer")
    public String supprimer(Model theModel, HttpSession session,  @RequestParam("recipeId") int recId) {

        //session check then delete
        Object userId = (HttpSession) session.getAttribute("userId");
        Recipe recipe = recipeServ.findById(recId);
        if(recipe.getPublisher() == (int)userId){
            recipeServ.deleteById(recId);
        }
        return null;
    }



    //Recherche d'une recette par mot clé
    @PostMapping("/rechercher")
    public String rechercher(Model theModel, @RequestParam("recipeId") String recipeS, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        boolean authenticated = session != null;
        System.out.println(recipeS);
        Recipe recipe = recipeServ.findByTitle(recipeS);
        if(recipe != null){
            theModel.addAttribute("recipe", recipe);
            theModel.addAttribute("auth", authenticated);
            return "recette";
        }else {
            return "redirect:/recipe/pagedesrecettes";

        }

    }


    //Consulter une recette
    @GetMapping("/consulter")
    public String consulter(Model theModel,  @RequestParam("recipeId") int recId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        boolean authenticated = session != null;
        Recipe recipe = recipeServ.findById(recId);
        if(recipe != null){
            List<List<String>> commentsDictionary = new ArrayList<>();
            List<String> comment;
            for (Comment comm : recipe.getComments()) {
                comment = new ArrayList<>();
                comment.add(comm.getUser().getUsername());
                comment.add(comm.getContent());
                commentsDictionary.add(comment);
            }

            theModel.addAttribute("comments", commentsDictionary);
            theModel.addAttribute(recipe);
            theModel.addAttribute("auth", authenticated);
            System.out.println(commentsDictionary);
            return "recette";
        }
        return null;

    }

    @GetMapping("/favoris")
    public String favoris(Model theModel, @RequestParam("recipeId") int recId, HttpServletRequest request) {

        int userId = (Integer)request.getSession().getAttribute("userId");
        Recipe recipe = recipeServ.findById(recId);

        User user = userServ.findById(userId);
        if (user != null && recipe != null) {
            recipe.getFavoritedByUsers().add(user);
            recipeServ.save(recipe);
        }

        int idRec = recipe.getId();
        return "redirect:/recipe/consulter?recipeId=" + idRec;
    }

    @GetMapping("/pagedesrecettes")
    public String pagedesrecettes(Model theModel) {

        theModel.addAttribute("allRecipes", recipeServ.findAll());
        return "pagedesrecettes";
    }
}
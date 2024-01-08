package layers.controller;

import jakarta.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import layers.entity.Comment;
import layers.entity.Recipe;
import layers.entity.Tags;
import layers.entity.User;
import layers.service.CommentService;
import layers.service.RecipeService;
import layers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

        private UserService userServ;
        private RecipeService recipeServ;

        private CommentService commentServ;

        @Autowired
        public UserController(UserService userServ, RecipeService recipeServ, CommentService commentServ) {
            this.userServ = userServ;
            this.recipeServ = recipeServ;
            this.commentServ = commentServ;
        }

        @GetMapping("/pageconnexion")
        public String pageConnexion(Model theModel, HttpServletRequest request) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                theModel.addAttribute("username", session.getAttribute("username"));
                return "accueil";
            } else {
                theModel.addAttribute("user", new User()); // Add an instance of the User class to the model
                return "login";  // Make sure "login" corresponds to the template name
            }
        }


        @PostMapping("/connexion")
        public String connexion(Model theModel, HttpServletRequest request, @ModelAttribute("user") User theUser){
            User user = userServ.login(theUser.getUsername(), theUser.getPassword());
            boolean auth = false;

            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("userId", user.getId());
                session.setAttribute("username", user.getUsername());

                theModel.addAttribute("username", session.getAttribute("username"));
                theModel.addAttribute("auth", true);
                return "accueil";
            } else {
                theModel.addAttribute("user", new User());
                return "login";
            }
        }

    @GetMapping("/deconnexion")
    public String deconnexion(HttpServletRequest request, Model theModel) {
        HttpSession session = request.getSession(false);
        System.out.println("tooooooo");
        if (session != null) {
            session.invalidate(); // This will delete the session
        }

        theModel.addAttribute("auth", false);
        return "accueil";
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request, Model theModel) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            User user = userServ.findById(((Integer) session.getAttribute("userId")));
            System.out.println(session.getAttribute("userId"));
            theModel.addAttribute("user", user);
            List<Recipe> recipes = recipeServ.findAll();
            List<Recipe> userRecipes = new ArrayList<>();
            for (Recipe recipe : recipes) {
               if(recipe.getPublisher() == user.getId()){
                   userRecipes.add((recipe));
               }
            }
            // user published recipes
            theModel.addAttribute("recipes", userRecipes);
            // user favorite recipes
            List<Recipe> favoriteRecipes = user.getFavoriteRecipes();
            theModel.addAttribute("favrecipes", favoriteRecipes);



            return "profil";
        }
        return null;

    }



    @GetMapping("/pageregister")
    public String pageRegister(Model theModel, @ModelAttribute("user") User theUser) {

            theModel.addAttribute("user", new User()); // Add an instance of the User class to the model
            return "register";  // Make sure "login" corresponds to the template name

    }

    @PostMapping("/inscription")
        public String inscription(Model theModel, @ModelAttribute("user") User theUser){
        if(theUser.getUsername() != "" && theUser.getPassword() != ""){
         userServ.save(theUser);
        }else{
            theModel.addAttribute("user", new User());
            return "register";
        }
            User user = new User();
            user.setUsername(theUser.getUsername());
            theModel.addAttribute("user", user);
            return "login";
        }


    @PostMapping("/modifier")
    public String modifier( @ModelAttribute("user") User theUser){
        User user = userServ.findByUsername(theUser.getUsername());
        user.setUsername(theUser.getUsername());
        user.setPassword(theUser.getPassword());
        userServ.update(user);
        return "redirect:/user/profile";

    }


    @PostMapping("/commenter")
    public String commenter(HttpServletRequest request, Model theModel, @RequestParam("idRecipe") int recipeId, @RequestParam("comment") String theComment) {
        HttpSession session = request.getSession(false);
        Map<String, String> commentsDictionary = new HashMap<>();
        Recipe recipe = recipeServ.findById(recipeId);
        if (session != null) {
            Comment comment = new Comment();
            User user = userServ.findById(((Integer) session.getAttribute("userId")));
            if (recipe != null && user != null) {
                comment.setUser(user);
                comment.setRecipe(recipe);
                comment.setContent(theComment);
                commentServ.save(comment);

                return "redirect:/recipe/consulter?recipeId=" + recipeId;

            }
        }
        theModel.addAttribute("recipe", recipe);
        theModel.addAttribute("comments", commentsDictionary);
        return "recette";
    }



}









package layers.controller;

import layers.service.RecipeService;
import layers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/User")
public class UserController {

        private UserService userServ;
        private RecipeService recipeServ;

        @Autowired
        public UserController(UserService userServ, RecipeService recipeServ) {
            this.userServ = userServ;
            this.recipeServ = recipeServ;
        }

        @GetMapping("/connexion")
        public String connexion(Model theModel){


            return "list-employees";
        }

        @GetMapping("/inscription")
        public String inscription(Model theModel){

            return "list-employees";
        }


    @GetMapping("/modifierCompte")
    public String modifierCompte(Model theModel){

        return "list-employees";
    }







}

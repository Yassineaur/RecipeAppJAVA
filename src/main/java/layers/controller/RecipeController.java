package layers.controller;

import layers.service.RecipeService;
import layers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Recipe")
public class RecipeController {

    private UserService userServ;
    private RecipeService recipeServ;

    @Autowired
    public RecipeController(UserService userServ, RecipeService recipeServ) {
        this.userServ = userServ;
        this.recipeServ = recipeServ;
    }

    @GetMapping("/publier")
    public String publier(Model theModel){

        return "list-employees";
    }

    //Modifier les informations d'une recette
    @GetMapping("/modifier")
    public String modifier(Model theModel){

        return "list-employees";
    }

    //Supprimer ma recette
    @GetMapping("/supprimer")
    public String supprimer(Model theModel){

        return "list-employees";
    }

    //Recherche d'une recette par mot clé
    @GetMapping("/rechercher")
    public String rechercher(Model theModel){



        return "list-employees";
    }


    //Consulter une recette
    @GetMapping("/consulter")
    public String consulter(Model theModel){




        return "list-employees";
    }

    @GetMapping("/favoris")
    public String favoris(Model theModel){




        return "list-employees";
    }
    @GetMapping("/commenter")
    public String commenter(Model theModel){




        return "list-employees";
    }


}

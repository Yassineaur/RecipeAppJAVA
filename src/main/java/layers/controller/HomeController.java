package layers.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import layers.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model theModel) {

        HttpSession session = request.getSession(false);
        boolean auth = false;
        if (session != null) {
            auth = true;
            theModel.addAttribute("auth", auth);
        }
        else {
            theModel.addAttribute("auth", auth);
        }

        return "accueil";
    }
}

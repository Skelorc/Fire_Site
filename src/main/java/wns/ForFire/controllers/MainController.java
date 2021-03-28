package wns.ForFire.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wns.ForFire.dao.ArticleDAO;

@Controller
public class MainController {

    @Autowired
    private ArticleDAO articleDAO;

    @GetMapping("/")
    public String greetings(
            @RequestParam (name="hello", required = false, defaultValue = "Рыжая")
                    String name, Model model)
    {
        model.addAttribute("hello",name);
        model.addAttribute("last_article",articleDAO.findLast());
        return"index";
    }


}

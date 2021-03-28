package wns.ForFire.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wns.ForFire.entity.Article;
import wns.ForFire.repo.ArticleRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@Controller
@RequestMapping("/research")
public class ResearchController {

    @Autowired
    private ArticleRepo articleRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String research(Model model)
    {
        ArrayList<Article> allArticles = (ArrayList<Article>) articleRepo.findAll();
        ArrayList<Article> reverseArticle = new ArrayList<>();
        for(int i = allArticles.size() - 1; i>=0; i--)
        {
            reverseArticle.add(allArticles.get(i));
        }
        model.addAttribute("articles", reverseArticle);
        return "research";
    }



}

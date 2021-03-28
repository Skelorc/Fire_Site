package wns.ForFire.controllers.admin_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wns.ForFire.dao.ArticleDAO;
import wns.ForFire.dao.BookDAO;
import wns.ForFire.dao.TermsDAO;
import wns.ForFire.entity.Article;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("panel_admin")
public class ArticleController {

    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private TermsDAO termsDAO;

    @Value("${upload.path}")
    private String uploadPath;



    @GetMapping
    public String show(@RequestParam(required = false, defaultValue = "not") String edit_article,
                       Model model)
    {
            model.addAttribute("articles", articleDAO.findByWords(edit_article));
            return "panel_admin";
    }

    @PostMapping
    public String addArticle(@RequestParam String title,
                             @RequestParam String text,
                             @RequestParam(value = "file",required = false) MultipartFile file) throws IOException {


        articleDAO.addArticleToDB(title, text, file);
        return "panel_admin"
        /*return "redirect:/research"*/;
    }



    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id)
    {
        Article article = articleDAO.findArticleById(id);
        model.addAttribute("article", article);
        return "edit";
    }

    @PatchMapping("/{id}")
    public String editArticle(@ModelAttribute("article") Article article,
                              @RequestParam(value = "file",required = false) MultipartFile file,
                              @PathVariable("id") long id) throws IOException {
        if(!file.isEmpty())
        {
            String uuidFile = UUID.randomUUID().toString();
            String resultName = uuidFile + "." + file.getOriginalFilename();
            Article articleForDel = articleDAO.findArticleById(id);
            File fileForDelete = new File(uploadPath + "/" + articleForDel.getUrl_image());
            fileForDelete.delete();
            file.transferTo(new File(uploadPath + "/" + resultName));
            article.setUrl_image(resultName);
        }
        else
        {
            Article articleForGetURL = articleDAO.findArticleById(id);
            article.setUrl_image(articleForGetURL.getUrl_image());
        }
        articleDAO.editArticle(id,article);
        return "redirect:/research";
    }

    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable("id") long id)
    {
        Article articleForDel = articleDAO.findArticleById(id);
        File fileForDelete = new File(uploadPath + "/" + articleForDel.getUrl_image());
        fileForDelete.delete();
        articleDAO.deleteArticleFromDB(id);
        return "redirect:/panel_admin";
    }



}

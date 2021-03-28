package wns.ForFire.dao;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wns.ForFire.entity.Article;
import wns.ForFire.repo.ArticleRepo;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ArticleDAO {

    @Autowired
    private ArticleRepo articleRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public void saveArticleToDB(Article article)
    {
        articleRepo.save(article);
    }

    public void addArticleToDB(String title, String text, MultipartFile file) throws IOException {
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists())
        {
            uploadDir.mkdirs();
        }
        String uuidFile = UUID.randomUUID().toString();
        String resultName =  uuidFile + "." + file.getOriginalFilename();

        file.transferTo(new File(uploadPath + "/" + resultName));
        if(!title.equals("")||!text.equals("")) {
            Article article = new Article(title, text, resultName);
            saveArticleToDB(article);
        }
    }

    public Article findArticleById(Long id)
    {
        Optional<Article> byId = articleRepo.findById(id);
        return byId.stream().filter(article -> article.getId() == id).findAny().orElse(null);
    }

    public void deleteArticleFromDB(Long id)
    {
        articleRepo.deleteById(id);
    }

    public List<Article> findByWords(@NotNull String words)
    {
        List<Article> all = (List<Article>)articleRepo.findAll();
        List<Article> findedArticles = new ArrayList<>();
        for(Article article: all)
        {
            if(article.getTitle().toLowerCase().contains(words.toLowerCase())&&!words.equals("not"))
            {
                findedArticles.add(article);
            }
        }
        return findedArticles;
    }

    public void editArticle(long id, Article updatedArticle) {
        Article articleToBeUpdated = findArticleById(id);

        articleToBeUpdated.setTitle(updatedArticle.getTitle());
        articleToBeUpdated.setText(updatedArticle.getText());
        if(updatedArticle.getUrl_image().isEmpty())
        {
            articleToBeUpdated.setUrl_image(updatedArticle.getUrl_image());
        }
        articleToBeUpdated.setUrl_image(updatedArticle.getUrl_image());

        saveArticleToDB(articleToBeUpdated);
    }

    public Article findLast()
    {
        ArrayList<Article> all = (ArrayList<Article>) articleRepo.findAll();
        if(all.size()>0) {
            return all.get(all.size() - 1);
        }
        else
        {
            return null;
        }
    }
}

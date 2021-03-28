package wns.ForFire.repo;

import org.springframework.data.repository.CrudRepository;
import wns.ForFire.entity.Article;


public interface ArticleRepo extends CrudRepository<Article,Long> {

}

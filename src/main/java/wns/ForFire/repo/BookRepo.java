package wns.ForFire.repo;

import org.springframework.data.repository.CrudRepository;
import wns.ForFire.entity.Book;

public interface BookRepo extends CrudRepository<Book,Integer> {
}

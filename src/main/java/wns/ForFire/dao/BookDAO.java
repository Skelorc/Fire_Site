package wns.ForFire.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wns.ForFire.entity.Book;
import wns.ForFire.entity.Terms;
import wns.ForFire.repo.BookRepo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookDAO {

    @Autowired
    BookRepo bookrepo;

    @Value("${upload.path.book}")
    private String uploadPathBook;

    public void add(String author, String name_book,String text_about_books, String url_book)
    {
        if(!author.equals("")||!name_book.equals("")||!text_about_books.equals("")) {
            Book book = new Book(author, name_book, text_about_books, url_book);
            bookrepo.save(book);
        }
    }

    public void add(Book book)
    {
        bookrepo.save(book);
    }


    public Book findBookById(int id)
    {
        Optional<Book> byId = bookrepo.findById(id);
        return  byId.stream().filter(book -> book.getId() == id).findAny().orElse(null);
    }

    public void editBook(int id, Book bookNew) throws IOException {
        Book bookForEdit = findBookById(id);
        if(!bookForEdit.getName_book().equals(bookNew.getName_book()))
        {
            bookForEdit.setName_book(bookNew.getName_book());
        }
        if(!bookForEdit.getAuthor().equals(bookNew.getAuthor()))
        {
            bookForEdit.setAuthor(bookNew.getAuthor());
        }
        if(!bookForEdit.getText_about_books().equals(bookNew.getText_about_books()))
        {
            bookForEdit.setText_about_books(bookNew.getText_about_books());
        }
        bookrepo.save(bookForEdit);
    }


    public List<Book> findBookByWord(String word) {
        List<Book> books = (List<Book>) bookrepo.findAll();
        return books.stream().filter(book-> book.getAuthor().toLowerCase().contains(word.toLowerCase())||book.getName_book().toLowerCase().contains(word.toLowerCase())&&!word.equals("not"))
                .collect(Collectors.toList());
    }

    public void delete(int id)
    {
        bookrepo.deleteById(id);;
    }

    public List<Book> show()
    {
        return (List<Book>) bookrepo.findAll();
    }

    public BookDAO() {
    }
}

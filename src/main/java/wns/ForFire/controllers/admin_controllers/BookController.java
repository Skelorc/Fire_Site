package wns.ForFire.controllers.admin_controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wns.ForFire.dao.BookDAO;
import wns.ForFire.entity.Book;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("panel_admin/book")
/*@PreAuthorize("hasAuthority('ADMIN')")*/
public class BookController {

    @Autowired
    BookDAO bookDAO;

    @Value("${upload.path.book}")
    private String uploadPathBook;

    @GetMapping
    public String show(@RequestParam(required = false, defaultValue = "not") String edit_book,
                       Model model)
    {
        model.addAttribute("books", bookDAO.findBookByWord(edit_book));
        return "book";
    }

    @PostMapping("/send_book")
    public String addBook(@RequestParam String author,
                          @RequestParam String text_about_books,
                          @RequestParam String name_book,
                          @RequestParam(value = "file") MultipartFile file) throws IOException
    {
        File uploadDir = new File(uploadPathBook);
        if(!uploadDir.exists())
        {
            uploadDir.mkdirs();
        }
        if(file!=null) {
            file.transferTo(new File(uploadPathBook + "/" + file.getOriginalFilename()));
            bookDAO.add(author, name_book, text_about_books, file.getOriginalFilename());
        }
        return"redirect:/panel_admin/book";
    }

    @GetMapping("/{id}/edit_book")
    public String editBook(Model model, @PathVariable("id") int id)
    {
        Book bookById = bookDAO.findBookById(id);
        model.addAttribute("book", bookById);
        return "edit_book";
    }


    @PatchMapping("/{id}/book")
    public String editBook(@ModelAttribute("book") Book book,
                              @PathVariable("id") int id) throws IOException {
        bookDAO.editBook(id,book);
        return "redirect:/literature";
    }

    @DeleteMapping("/{id}/book")
    public String deleteBook(@PathVariable ("id") int id)
    {
        Book bookforDele = bookDAO.findBookById(id);
        File fileForDelete = new File(uploadPathBook + "/" + bookforDele.getUrl_book());
        fileForDelete.delete();
        bookDAO.delete(id);
        return "redirect:/panel_admin/book";
    }
}

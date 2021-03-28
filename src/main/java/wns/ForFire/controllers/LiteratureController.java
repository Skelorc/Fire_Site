package wns.ForFire.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wns.ForFire.dao.BookDAO;
import wns.ForFire.entity.Book;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("literature")
public class LiteratureController {

    @Autowired
    private BookDAO bookDAO;

    @Value("${upload.path.book}")
    private String uploadPathBook;

    @GetMapping
    public String showLiterature(Model model)
    {
        model.addAttribute("books",bookDAO.show());
        return "literature";
    }

    @GetMapping("/find")
    public String findBook(@RequestParam String word, Model model)
    {
        List<Book> bookByWord = bookDAO.findBookByWord(word);
        model.addAttribute("books_by_word", bookByWord);
        return "literature";
    }

    @GetMapping("/download")
    public void downloadFile(@Param(value= "id") int id, HttpServletResponse response) throws IOException {
        Book book = bookDAO.findBookById(id);
        String fileName = uploadPathBook + "/" + book.getUrl_book();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",book.getUrl_book());
        response.setHeader(headerKey, headerValue);
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(fileName);
            try {
                int c;
                while ((c = inputStream.read()) != -1) {
                    response.getWriter().write(c);
                }
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                response.getWriter().close();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

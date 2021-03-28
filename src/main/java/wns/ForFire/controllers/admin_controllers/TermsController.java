package wns.ForFire.controllers.admin_controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wns.ForFire.dao.TermsDAO;
import wns.ForFire.entity.Terms;


@Controller
@RequestMapping("panel_admin/terms")
public class TermsController {

    @Autowired
    TermsDAO termsDAO;

    @GetMapping
    public String show(@RequestParam(required = false, defaultValue = "not") String word,
                       Model model) {
        model.addAttribute("allterms", termsDAO.findByWordForEdit(word));
        return "terms";
    }

    @PostMapping("add")
    public String addTermin(@RequestParam String term,
                            @RequestParam String description) {
        termsDAO.saveTerms(term, description);
        return "redirect:/panel_admin/terms";
    }

    @GetMapping("/{id}/edit_term")
    public String editBook(Model model, @PathVariable("id") long id) {
        Terms termsFromDB = termsDAO.findTermFromDBById(id);
        model.addAttribute("terms", termsFromDB);
        return "edit_term";
    }


    @PatchMapping("/{id}/terms")
    public String editArticle(@ModelAttribute("terms") Terms terms,
                              @PathVariable("id") long id) {
        termsDAO.editTermsInDB(id, terms.getTerm(), terms.getDescription());
        return "redirect:/terminology";
    }

    @DeleteMapping("/{id}/terms")
    public String deleteBook(@PathVariable("id") long id) {
        termsDAO.deleteTermsFromDB(id);
        return "redirect:/panel_admin/terms";
    }

}

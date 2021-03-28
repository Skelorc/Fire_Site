package wns.ForFire.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wns.ForFire.dao.TermsDAO;
import wns.ForFire.entity.Terms;

import java.util.List;

@Controller
@RequestMapping("terminology")
public class TerminologyController {

    @Autowired
    TermsDAO termsDAO;

    @GetMapping
    public String showAll(@RequestParam(required = false, defaultValue = "not") String word, Model model)
    {
        List<Terms> byWordFromTerms = termsDAO.findByWordFromTerms(word);
        model.addAttribute("allterms",byWordFromTerms);
        return "terminology";
    }

    @GetMapping("{/literal}")
    public String findByFirstChar(@PathVariable ("literal") char literal, Model model)
    {
        List<Terms> byFirstChar = termsDAO.findByFirstChar(literal);
        model.addAttribute("terms_first_char", byFirstChar);
        return "terminology";
    }


}

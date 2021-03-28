package wns.ForFire.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wns.ForFire.dao.SociologistsDAO;

@Controller
@RequestMapping("sociologists")
public class SociologistsController {

    @Autowired
    private SociologistsDAO socDAO;

    @GetMapping
    public String show(Model model)
    {
        model.addAttribute("allsoc", socDAO.findAllFromDB());
        return "sociologists";
    }
}

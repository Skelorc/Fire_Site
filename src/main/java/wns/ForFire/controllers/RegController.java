package wns.ForFire.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class RegController {

    @GetMapping
    public String show()
    {
        return "reg";
    }

    /*@PostMapping
    public String regIn(@RequestParam String login,
                        @RequestParam String password,
                        Model model)
    {
        System.out.println(login + "  " + password);
        return "/panel_admin";
    }*/
}

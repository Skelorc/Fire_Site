package wns.ForFire.controllers.admin_controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wns.ForFire.dao.SociologistsDAO;
import wns.ForFire.entity.Book;
import wns.ForFire.entity.Sociologists;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("panel_admin/soc")
/*@PreAuthorize("hasAuthority('ADMIN')")*/
public class SocController {

    @Autowired
    private SociologistsDAO socDAO;

    @Value("${upload.path.soc}")
    private String uploadPathSoc;

    @GetMapping
    public String show(@RequestParam(required = false, defaultValue = "not") String name,
                       Model model)
    {
        model.addAttribute("soc", socDAO.findByName(name));
        return "soc";
    }

    @PostMapping("/send_soc")
    public String addSoc(@RequestParam String name,
                          @RequestParam String biografy,
                          @RequestParam String work,
                          @RequestParam(value = "file") MultipartFile file) throws IOException
    {
        socDAO.save(name, biografy,work,file);
        return"redirect:/panel_admin/soc";
    }

    @GetMapping("/{id}/edit_soc")
    public String editSoc(Model model, @PathVariable("id") int id)
    {
        Sociologists soc = socDAO.findSociologistsById(id);
        model.addAttribute("soc", soc);
        return "edit_soc";
    }


    @PatchMapping("/{id}/soc")
    public String editSoc(@ModelAttribute("soc") Sociologists soc,
                          @RequestParam(value = "file",required = false) MultipartFile file,
                              @PathVariable("id") int id) throws IOException {
        socDAO.editSociologists(id,soc.getName(), soc.getBiografy(), soc.getWork(),file);
        return "redirect:/sociologists";
    }

    @DeleteMapping("/{id}/soc")
    public String deleteSoc(@PathVariable ("id") int id)
    {
        Sociologists socforDele = socDAO.findSociologistsById(id);
        File fileForDelete = new File(uploadPathSoc + "/" + socforDele.getUrl_image());
        fileForDelete.delete();
        socDAO.deleteSocFormDB(id);
        return "redirect:/panel_admin/soc";
    }

}

package wns.ForFire.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wns.ForFire.entity.Terms;
import wns.ForFire.repo.TermsRepo;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class TermsDAO {

    @Autowired
    private TermsRepo termsRepo;




    public void saveTerms(String term, String description)
    {
        if(!term.equals("")||!description.equals(""))
        {
            Terms terms = new Terms(term, description);
            termsRepo.save(terms);
        }
    }

    public Terms findTermFromDBById(long id)
    {
        Optional<Terms> byId = termsRepo.findById(id);
        return byId.stream().filter(terms -> terms.getId() == id).findAny().orElse(null);
    }

    public List<Terms> findByFirstChar(char literal)
    {
        List<Terms> allTerms = (List<Terms>) termsRepo.findAll();
        return allTerms.stream().filter(terms -> terms.getTerm().charAt(0)==literal).collect(Collectors.toList());
    }

    public List<Terms> findAllFromDB()
    {
        List<Terms> all = (List<Terms>)termsRepo.findAll();
        all.sort(Comparator.comparing(Terms::getTerm));
        return all;
    }

    public List<Terms> findByWordFromTerms(String text)
    {
        if(!text.equalsIgnoreCase("not"))
        {
            List<Terms> allTerms = (List<Terms>) termsRepo.findAll();
            List<Terms> collect = allTerms.stream().filter(terms -> text.toLowerCase().contains(terms.getTerm().toLowerCase()) || text.toLowerCase().contains(terms.getDescription().toLowerCase())).sorted(Comparator.comparing(Terms::getTerm)).collect(Collectors.toList());
            return collect;
        }
        else
        {
            return findAllFromDB();
        }
    }

    public List<Terms> findByWordForEdit(String text)
    {
            List<Terms> allTerms = (List<Terms>) termsRepo.findAll();
            List<Terms> collect = allTerms.stream().filter(terms -> text.toLowerCase().contains(terms.getTerm().toLowerCase()) || text.toLowerCase().contains(terms.getDescription().toLowerCase())).sorted(Comparator.comparing(Terms::getTerm)).collect(Collectors.toList());
            return collect;
    }

    public void editTermsInDB( long id, String term, String description)
    {
        Terms newTerm = new Terms(id, term,description);
        termsRepo.save(newTerm);
    }

    public void deleteTermsFromDB(long id)
    {
        termsRepo.deleteById(id);
    }

    public TermsDAO() {
    }
}

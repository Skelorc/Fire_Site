package wns.ForFire.dao;


import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wns.ForFire.entity.Sociologists;
import wns.ForFire.repo.SocRepo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SociologistsDAO {

    @Autowired
    SocRepo socrepo;

    @Value("${upload.path.soc}")
    private String uploadPathSoc;


    public void save(@NotNull String name, @NotNull String biografu, @NotNull String work, @NotNull MultipartFile file) throws IOException {
        File uploadDir = new File(uploadPathSoc);
        String uuidFile = UUID.randomUUID().toString();
        String resultName = uuidFile + "." + file.getOriginalFilename();
        if(!uploadDir.exists())
        {
            uploadDir.mkdirs();
        }
        file.transferTo(new File(uploadPathSoc + "/" + resultName));
        Sociologists soc = new Sociologists(name, biografu, work, resultName);
        socrepo.save(soc);
    }

    public void save(Sociologists soc)
    {
        socrepo.save(soc);
    }

    public Sociologists findSociologistsById(int id)
    {
        Optional<Sociologists> byId = socrepo.findById(id);
        return  byId.stream().filter(sociologists -> sociologists.getId() == id).findAny().orElse(null);
    }

    public List<Sociologists> findByName(@NotNull String name)
    {
        List<Sociologists> allFromDB = (List<Sociologists>) socrepo.findAll();
        return allFromDB.stream().filter(sociologists-> sociologists.getName().toLowerCase().contains(name.toLowerCase())&&!name.equals("not"))
                .collect(Collectors.toList());
    }


    public List<Sociologists> findAllFromDB()
    {
        return (List<Sociologists>) socrepo.findAll();
    }

    public void editSociologists(@NotNull int id, @NotNull String name, @NotNull String biografy, @NotNull String work, @NotNull MultipartFile file) throws IOException {
        Sociologists socForEdit = findSociologistsById(id);
        socForEdit.setName(name);
        socForEdit.setBiografy(biografy);
        socForEdit.setWork(work);
        if(!socForEdit.getUrl_image().equals(file.getOriginalFilename())) {
            File uploadDir = new File(uploadPathSoc);
            String uuidFile = UUID.randomUUID().toString();
            String resultName = uuidFile + "." + file.getOriginalFilename();
            if (!uploadDir.exists())
            {
                uploadDir.mkdirs();
            }
            File fileForDelete = new File(uploadPathSoc + "/" + socForEdit.getUrl_image());
            fileForDelete.delete();
            file.transferTo(new File(uploadPathSoc + "/" + resultName));
            socForEdit.setUrl_image(resultName);
        }
        save(socForEdit);

    }

    public void deleteSocFormDB(int id)
    {
        socrepo.deleteById(id);
    }

}

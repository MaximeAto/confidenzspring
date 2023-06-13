package com.isj.confidenz.Iservices;

import com.isj.confidenz.dtos.EntrepriseDto;
import com.isj.confidenz.dtos.FichierDto;
import com.isj.confidenz.models.Fichier;
import com.isj.confidenz.objets.FichierForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFichier {
    void upload_fichier(FichierForm file, MultipartFile multipartFile, String nomuser) throws IOException;
    void supprimer_fichier(FichierDto fichierDto);
    void modifier_droits_fichier(FichierDto fichierD);

}

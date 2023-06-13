package com.isj.confidenz.services;

import com.isj.confidenz.Iservices.IFichier;
import com.isj.confidenz.dtos.EntrepriseDto;
import com.isj.confidenz.dtos.FichierDto;
import com.isj.confidenz.models.Entreprise;
import com.isj.confidenz.models.Fichier;
import com.isj.confidenz.objets.FichierForm;
import com.isj.confidenz.repositories.EntrepriseRepository;
import com.isj.confidenz.repositories.FichierRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.transaction.Transactional;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class FichierService implements IFichier {

    @Autowired
    FichierRepository fichierRepository;

    @Override
    public void upload_fichier(FichierForm file, MultipartFile multipartFile, String nomuser) throws IOException {

    }

    @Override
    public void supprimer_fichier(FichierDto fichierDto) {

    }

    @Override
    public void modifier_droits_fichier(FichierDto fichierD) {

    }
}

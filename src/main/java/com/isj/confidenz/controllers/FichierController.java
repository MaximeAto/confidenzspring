package com.isj.confidenz.controllers;

import com.isj.confidenz.dtos.EntrepriseDto;
import com.isj.confidenz.models.Entreprise;
import com.isj.confidenz.models.Fichier;
import com.isj.confidenz.objets.FichierForm;
import com.isj.confidenz.repositories.FichierRepository;
import com.isj.confidenz.services.FichierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RequestMapping("/fichier")
@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class FichierController {

    @Autowired
    private FichierRepository fichierRepository;

    @Autowired
    FichierService fichierService;
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerCompteAd(@PathVariable("id") Long id) {
        Optional<Fichier> fichierOpt = fichierRepository.findById(id);
        if (fichierOpt.isPresent()) {
            fichierRepository.delete(fichierOpt.get());
            return ResponseEntity.ok("fichier supprimé avec succès");
        } else {
            return ResponseEntity.badRequest().body("fichier inexistant");
        }
    }

}

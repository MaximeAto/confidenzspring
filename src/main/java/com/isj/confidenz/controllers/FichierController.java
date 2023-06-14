package com.isj.confidenz.controllers;

import com.isj.confidenz.models.Fichier;
import com.isj.confidenz.repositories.FichierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/fichier")
@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class FichierController {

    @Autowired
    private FichierRepository fichierRepository;

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

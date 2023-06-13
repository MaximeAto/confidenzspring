package com.isj.confidenz.controllers;

import com.isj.confidenz.dtos.EmployeDto;
import com.isj.confidenz.dtos.EntrepriseDto;
import com.isj.confidenz.dtos.Notification_adminDto;
import com.isj.confidenz.models.Employe;
import com.isj.confidenz.models.Entreprise;
import com.isj.confidenz.models.Fichier;
import com.isj.confidenz.models.Notification_admin;
import com.isj.confidenz.objets.FichierResult;
import com.isj.confidenz.repositories.EmployeRepository;
import com.isj.confidenz.repositories.EntrepriseRepository;
import com.isj.confidenz.repositories.FichierRepository;
import com.isj.confidenz.repositories.NotificationRepository;
import com.isj.confidenz.services.EntrepriseService;
import com.isj.confidenz.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/entreprise")
@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class EntrepriseController {
    @Autowired
    private EntrepriseService entrepriseService;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FichierRepository fichierRepository;
    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    NotificationService notificationService;

    //créer un compte utilisateur
    @PostMapping("/creerCompteAdmin")
    public ResponseEntity<?> creerCompteAdmin(@RequestPart("nom") String nom, @RequestPart("nomuser") String nomuser, @RequestPart("email") String email, @RequestPart("tel") String tel, @RequestPart("mdp") String mdp){
        entrepriseService.creerCompteAdmin(nom, nomuser, email, tel, mdp );
        return ResponseEntity.ok().build();
    }

    //ajouter un compte employé
    @PostMapping("/ajouterCompteEmploye")
    public ResponseEntity<?> ajouterCompteEmploye(@RequestBody EmployeDto employeDto) throws IOException {
        entrepriseService.ajouterCompteEmploye(employeDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/updateCompteEmploye")
    public ResponseEntity<?> updateCompteEmploye(@RequestBody EmployeDto employeDto) throws IOException {
        entrepriseService.updateCompteEmploye(employeDto);
        return ResponseEntity.ok().build();
    }

    //supprimer le compte d'une entreprise
    @DeleteMapping("/supprimerAdmin/{nom}")
    public ResponseEntity<String> supprimerCompteAd(@PathVariable("nom") String nom) {
        Optional<Entreprise> entrepriseOpt = entrepriseRepository.findByNomuser(nom);
        if (entrepriseOpt.isPresent()) {
            entrepriseRepository.delete(entrepriseOpt.get());
            return ResponseEntity.ok("Compte supprimé avec succès");
        } else {
            return ResponseEntity.badRequest().body("Compte inexistant");
        }
    }

    // suprimer un compte employé
    @DeleteMapping("/supprimerEmploye/{id}")
    public ResponseEntity<String> supprimerCompteEmp(@PathVariable("id") String id) {
        Optional<Employe> employeOpt = employeRepository.findById(id);
        if (employeOpt.isPresent()) {
            employeRepository.delete(employeOpt.get());
            return ResponseEntity.ok("Compte supprimé avec succès");
        } else {
            return ResponseEntity.badRequest().body("Compte inexistant");
        }
    }

    // lister tous les employés d'une entreprise
   @GetMapping("/employes/{nomuser}")
    public ResponseEntity<List<Employe>> listEmp(@PathVariable("nomuser") String nomuser){
       Optional<Entreprise> entrepriseOpt = entrepriseRepository.findByNomuser(nomuser);
        List<Employe> listemp = employeRepository.findByEntreprise(entrepriseOpt.get());
        return ResponseEntity.ok().body(listemp);
    }
    @GetMapping("/fichiers/{nomuser}")
    public ResponseEntity<List<Fichier>> listFich(@PathVariable("nomuser") String nomuser){

        try {
            Optional<Entreprise> entrepriseOpt = entrepriseRepository.findByNomuser(nomuser);
            if (!entrepriseOpt.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            List<Fichier> listfich = fichierRepository.findByEntreprise(entrepriseOpt.get());
            return ResponseEntity.ok().body(listfich);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //rechercher un employe par son nomuser
    @GetMapping("/findBynomuser/{nomuser}")
    public ResponseEntity<Entreprise> findBynomuser(@PathVariable("nomuser") String nomuser){
        Optional<Entreprise> entrepriseOpt = entrepriseRepository.findByNomuser(nomuser);
        Entreprise entreprise = entrepriseOpt.get();
        return ResponseEntity.ok().body(entreprise);
    }

    @PostMapping("/ajouterFichier")
    public ResponseEntity<?> ajouterFichier(@RequestPart("nomuser") String nomuser, @RequestPart("fichForm") String fichForm) throws IOException {
        entrepriseService.upload_fichier(nomuser, fichForm);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/notifications_admin/{nomuser}")
    public ResponseEntity<List<Notification_admin>> listnotifAdmin(@PathVariable("nomuser") String nomuser){
        List<Notification_admin> listnotif =  notificationService.liseNotifsNonVue(nomuser);
        return ResponseEntity.ok().body(listnotif);
    }
    @PostMapping("/changevueadmin")
    public ResponseEntity<?> changerStatusVueAdmin(@RequestBody Notification_adminDto notification_adminDto) {
        notificationService.changerStatusNotifAdmin(notification_adminDto);
        return ResponseEntity.ok().build();
    }
}


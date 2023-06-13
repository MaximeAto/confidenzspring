package com.isj.confidenz.controllers;


import com.isj.confidenz.dtos.EmployeDto;
import com.isj.confidenz.dtos.NotificationDto;
import com.isj.confidenz.dtos.Notification_adminDto;
import com.isj.confidenz.models.Employe;
import com.isj.confidenz.models.Notification;
import com.isj.confidenz.models.Notification_admin;
import com.isj.confidenz.repositories.EmployeRepository;
import com.isj.confidenz.repositories.EntrepriseRepository;
import com.isj.confidenz.repositories.FichierRepository;
import com.isj.confidenz.repositories.NotificationRepository;
import com.isj.confidenz.services.EmployeService;
import com.isj.confidenz.services.EntrepriseService;
import com.isj.confidenz.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/employe")
@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class EmployeController {

    @Autowired
    private EntrepriseService entrepriseService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FichierRepository fichierRepository;
    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EmployeService employeService;

    @Autowired
    private NotificationRepository notificationRepository;
    @GetMapping("/notifications/{nomuser}")
    public ResponseEntity<List<Notification>> listnotif(@PathVariable("nomuser") String nomuser){
        List<Notification> listnotif = notificationService.notificationParEmploye(nomuser);
        return ResponseEntity.ok().body(listnotif);
    }

    @PostMapping("/changevue")
    public ResponseEntity<?> changerStatusVue(@RequestBody NotificationDto notificationDto) {
        notificationService.changerStatus(notificationDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/envoyernotifcation")
    public ResponseEntity<?> envoyernotifcation(@RequestPart("employe") String employe, @RequestPart("contenu") String contenu, @RequestPart("datecreate") String nomuser) {
       notificationService.envoyerNotifAdmin(employe, contenu, nomuser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findemploye/{id}")
    public ResponseEntity<?> recehercheEmploye(@PathVariable("id") String id){
       Employe employe = employeService.rechercheEmploye(id);
        return  ResponseEntity.ok().body(employe);
    }


}

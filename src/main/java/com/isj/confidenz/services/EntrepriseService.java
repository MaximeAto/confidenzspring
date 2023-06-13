package com.isj.confidenz.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isj.confidenz.Iservices.IEntreprise;
import com.isj.confidenz.dtos.*;
import com.isj.confidenz.models.*;
import com.isj.confidenz.objets.FichierForm;
import com.isj.confidenz.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class EntrepriseService implements IEntreprise {

    @Autowired
    private EntrepriseRepository entrepriseRepositoy;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private FichierRepository fichierRepository;
    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private  NotificationService notificationService;


    @Override
    public void creerCompteAdmin(String nom, String nomuser, String email, String tel, String mdp ) {
        Entreprise entreprise = new Entreprise();

        //remplir les données de l'entité
        entreprise.setNom(nom);
        entreprise.setEmail(email);
        entreprise.setTelephone(Long.valueOf(tel));
        entreprise.setNomuser(nomuser);
        entreprise.setRole(1);
        entreprise.setMdp(mdp);

        //enregistrer dans la bd
        entrepriseRepositoy.save(entreprise);
    }

    @Override
    public void ajouterCompteEmploye(EmployeDto employeDto) throws IOException {
        System.out.println(employeDto);
        employeDto.setRole(0);
        Employe employe = new Employe();
        Optional<Entreprise> entr = entrepriseRepositoy.findByNomuser(employeDto.getEntreprise().getNomuser());

        //remplir les données de l'entité
        employe.setNom(employeDto.getNom());
        employe.setPrenom(employeDto.getPrenom());
        employe.setEmail(employeDto.getEmail());
        employe.setRole(employeDto.getRole());
        employe.setMdp("147852369");
        employe.setEntreprise(entr.get());
        employe.setTelephone(employeDto.getTelephone());
        employe.setPoste(employeDto.getPoste());
        employe.setId(employeDto.getId());
        // Check if file already exists
        boolean empExists = employeRepository.existsByIdAndEntreprise(employe.getId(), employe.getEntreprise());
        if (empExists) {
            throw new IOException("L'employé existe déjà");
        }

        //enregistrer dans la bd
        employeRepository.save(employe);
    }


    public void updateCompteEmploye(EmployeDto employeDto) throws IOException {
        System.out.println(employeDto);
        employeDto.setRole(0);
        Employe employe = new Employe();
        Optional<Entreprise> entr = entrepriseRepositoy.findByNomuser(employeDto.getEntreprise().getNomuser());

        //remplir les données de l'entité
        employe.setNom(employeDto.getNom());
        employe.setPrenom(employeDto.getPrenom());
        employe.setEmail(employeDto.getEmail());
        employe.setRole(employeDto.getRole());
        employe.setMdp("147852369");
        employe.setEntreprise(entr.get());
        employe.setTelephone(employeDto.getTelephone());
        employe.setPoste(employeDto.getPoste());
        employe.setId(employeDto.getId());
        // Check if file already exists

        //enregistrer dans la bd
        employeRepository.save(employe);
    }



    @Override
    public void supprimerCompteAd(EntrepriseDto entrepriseDto) {
        Optional<Entreprise> entr = entrepriseRepositoy.findByNomuser(entrepriseDto.getNomuser());
        String id = Integer.toString(entr.get().getId());
        entrepriseRepositoy.deleteById(id);
    }

    @Override
    public void supprimerCompteEm(EmployeDto employeDto) {
        Optional<Employe> emp = employeRepository.findByEmail(employeDto.getEmail());
        String id = emp.get().getId();
        entrepriseRepositoy.deleteById(id);
    }

    @Override
    public List<Employe> listeEmp(EntrepriseDto entrepriseDto) {
        Optional<Entreprise> entr = entrepriseRepositoy.findByNomuser(entrepriseDto.getNomuser());
        List<Employe> lisE = employeRepository.findByEntreprise(entr.get());
        return lisE;
    }

    @Override
    public List<Fichier> listeFich(EntrepriseDto entrepriseDto) {
        Optional<Entreprise> entr = entrepriseRepositoy.findByNomuser(entrepriseDto.getNomuser());
        List<Fichier> lisF = fichierRepository.findByEntreprise(entr.get());
        return lisF;
    }

    @Override
    public void upload_fichier(String nomuser, String fichForm) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        FichierForm fichierForm = objectMapper.readValue(fichForm, FichierForm.class);
        System.out.println("votre fichier est : " + fichierForm);

        Fichier fichi = new Fichier();

        Optional<Entreprise> entr = entrepriseRepositoy.findByNomuser(nomuser);
        fichi.setNom(fichierForm.getNom());
        fichi.setDescription(fichierForm.getDescription());
        fichi.setType(fichierForm.getType());
        fichi.setPath(fichierForm.getPath());
        fichi.setHours(fichierForm.getHours());
        fichi.setDate(fichierForm.getDate());
        fichi.setType(fichierForm.getType());
        fichi.setTaille(fichierForm.getTaille());
        fichi.setDroits(1);
        fichi.setEntreprise(entr.get());
        fichi.setInformation(fichierForm.getInformation());

        // Check if file already exists
        boolean fileExists = fichierRepository.existsByNomAndEntreprise(fichi.getNom(), fichi.getEntreprise());
        if (fileExists) {
            throw new IOException("Le fichier existe déjà");
        }

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setVue(false);
        notificationDto.setEntreprise(entr.get());
        notificationDto.setContenu(fichierForm.getDescription());
        notificationDto.setCreatedate(fichierForm.getDate());

        notificationService.ajouterNotif(notificationDto);

        fichierRepository.save(fichi);


        }
    }

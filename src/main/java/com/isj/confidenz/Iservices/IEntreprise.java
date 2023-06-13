package com.isj.confidenz.Iservices;

import com.isj.confidenz.dtos.EmployeDto;
import com.isj.confidenz.dtos.EntrepriseDto;
import com.isj.confidenz.models.Employe;
import com.isj.confidenz.models.Fichier;

import java.io.IOException;
import java.util.List;

public interface IEntreprise {

    void creerCompteAdmin(String nom, String nomuser, String email, String tel, String mdp );
    void ajouterCompteEmploye(EmployeDto employeDto) throws IOException;
    void updateCompteEmploye(EmployeDto employeDto) throws IOException;
    void supprimerCompteAd(EntrepriseDto entrepriseDto);
    void supprimerCompteEm(EmployeDto employeDto);
    void upload_fichier(String nomuser,String fichForm) throws IOException;
    public List<Employe> listeEmp(EntrepriseDto entrepriseDto);
    public List<Fichier> listeFich(EntrepriseDto entrepriseDto);
}
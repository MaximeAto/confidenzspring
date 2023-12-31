package com.isj.confidenz.Iservices;

import com.isj.confidenz.models.Employe;
import com.isj.confidenz.models.Fichier;

import java.io.IOException;

public interface IEmploye {

    public void modiferMdp();
    public void consulternotif(Employe employe);
    public void consulterInfo(Fichier fichier);
    public void modifierInfo();
    public Employe rechercheEmploye(String id)throws IOException;

}
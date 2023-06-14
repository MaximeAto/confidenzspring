package com.isj.confidenz.services;

import com.isj.confidenz.Iservices.IEmploye;
import com.isj.confidenz.models.Employe;
import com.isj.confidenz.models.Fichier;
import com.isj.confidenz.repositories.EmployeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Service
@Transactional
public class EmployeService implements IEmploye {
    @Autowired
    private EmployeRepository employeRepository;


    @Override
    public void modiferMdp() {

    }

    @Override
    public void consulternotif(Employe employe) {

    }

    @Override
    public void consulterInfo(Fichier fichier) {

    }

    @Override
    public void modifierInfo() {

    }

    @Override
    public Employe rechercheEmploye(String id) throws IOException {
        boolean empExist = employeRepository.existsById(id);
        if (empExist){
            Employe employe = employeRepository.findById(id).get();
            return  employe;
        }
        throw new IOException("employe inexistant");


    }
}
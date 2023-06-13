package com.isj.confidenz.dtos;

import lombok.Data;

@Data
public class EmployeDto {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String poste;
    private long telephone;
    private String mdp;
    private int role;
    private EntrepriseDto entreprise;
}

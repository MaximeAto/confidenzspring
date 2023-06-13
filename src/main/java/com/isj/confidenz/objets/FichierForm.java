package com.isj.confidenz.objets;

import com.isj.confidenz.models.Entreprise;
import lombok.Data;



@Data
public class FichierForm {
    private Long id;
    private String nom;
    private String description;
    private  String type;
    private int taille;
    private Entreprise entreprise;
    private String date;
    private String path;
    private String hours;
    private String information;
}

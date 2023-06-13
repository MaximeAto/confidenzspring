package com.isj.confidenz.dtos;

import lombok.Data;

import javax.persistence.Column;

@Data
public class FichierDto {
    private Long id;
    private String nom;
    private String type;
    private String description;
    private String date;
    private String hours;
    private String content;
    private int droits;
    private EntrepriseDto entreprise;
    private String information;
}

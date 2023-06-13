package com.isj.confidenz.dtos;

import lombok.Data;

@Data
public class EntrepriseDto {
    private int id;
    private String nom;
    private String email;
    private String mdp;
    private int role;
    private String nomuser;
    private long telephone;
}

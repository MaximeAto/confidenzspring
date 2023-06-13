package com.isj.confidenz.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity // indique qu'il s'agit d'une entité
@Table(name = "entreprise") // nom de la table dans la basede données
@Data
public class Entreprise implements Serializable {
    @Id
    @Column(name = "id_entreprise", nullable = false)
    private int id;
    @Column(name = "nom", nullable = false)
    private String nom;
    @Column(name = "nomuser", nullable = false)
    private String nomuser;
    @Column(name = "telephone", nullable = false)
    private long telephone;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "mot_de_passe", nullable = false)
    private String mdp;
    @Column(name = "role", nullable = false)
    private int role;
}


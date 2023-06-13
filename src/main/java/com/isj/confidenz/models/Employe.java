package com.isj.confidenz.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;

@Entity // indique qu'il s'agit d'une entité
@Table(name = "employe") // nom de la table dans la basede données
@Data //génère les getters et les setters
public class Employe implements Serializable {
    @Id
    @Column(name = "id_employe", nullable = false)
    private String id;
    @Column(name = "nom", nullable = false)
    private String nom;
    @Column(name = "prenom", nullable = false)
    private String prenom;
    @Column(name = "telephone", nullable = false, unique = true)
    private long telephone;
    @Column(name = "poste", nullable = false)
    private String poste;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "mdp", nullable = false)
    private String mdp;
    @Column(name = "role", nullable = false)
    private int role;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_entreprise", nullable = false)
    private Entreprise entreprise;

}
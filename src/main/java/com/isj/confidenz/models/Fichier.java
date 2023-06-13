package com.isj.confidenz.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity // indique qu'il s'agit d'une entité
@Table(name = "fichier") // nom de la table dans la basede données
@Data
public class Fichier  implements Serializable {
    @Id
    @Column(name = "id_fichier", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "date", nullable = false)
    private String date;
    @Column(name = "taille", nullable = false)
    private int taille;
    @Column(name = "chemin", nullable = false)
    private String path;
    @Column(name = "hours", nullable = false)
    private String hours;
    @Column(name = "droits", nullable = false)
    private int droits;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_entreprise", nullable = false)
    private Entreprise entreprise;
    @Column(name = "information", nullable = false)
    private String information;
    public boolean isPersisted() {
        return id != null;
    }

}

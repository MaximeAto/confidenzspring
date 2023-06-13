package com.isj.confidenz.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity // indique qu'il s'agit d'une entité
@Table(name = "notification_admin") // nom de la table dans la basede données
@Data
public class Notification_admin implements Serializable {

    @Id
    @Column(name = "id_notification", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createdate", nullable = false)
    private String createdate;

    @Column(name = "vue", nullable = false)
    private  boolean vue;

    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_entreprise", nullable = false)
    private Entreprise entreprise;

    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_employe",nullable = false)
    private Employe employe;

    @Column(name = "contenu", nullable = false)
    private String contenu;
}

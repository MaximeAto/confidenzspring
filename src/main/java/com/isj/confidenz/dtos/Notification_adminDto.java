package com.isj.confidenz.dtos;

import com.isj.confidenz.models.Employe;
import com.isj.confidenz.models.Entreprise;
import lombok.Data;

@Data
public class Notification_adminDto {
    private Long id;
    private String contenu;
    private String createdate;
    private Boolean vue;
    private Entreprise Entreprise;
    private Employe Employe;
}

package com.isj.confidenz.dtos;

import com.isj.confidenz.models.Employe;
import com.isj.confidenz.models.Entreprise;
import lombok.Data;

@Data
public class NotificationDto {
    private Long id;
    private String contenu;
    private String createdate;
    private Boolean vue;
    private Entreprise entreprise;
    private Employe employe;
}

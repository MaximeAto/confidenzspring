package com.isj.confidenz.Iservices;

import com.isj.confidenz.dtos.NotificationDto;
import com.isj.confidenz.dtos.Notification_adminDto;
import com.isj.confidenz.models.Notification;
import com.isj.confidenz.models.Notification_admin;

import java.util.List;

public interface INotification {
    void ajouterNotif(NotificationDto notificationDto);
    List<Notification> notificationParEmploye(String employe);
    void changerStatus(NotificationDto notificationDto);
    void changerStatusNotifAdmin(Notification_adminDto notification_adminDto);
    void envoyerNotifAdmin(String idemploye, String contenu, String datecreate);
    List<Notification_admin> liseNotifsNonVue(String idEntreprise);
}

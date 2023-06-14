package com.isj.confidenz.services;

import com.isj.confidenz.Iservices.INotification;
import com.isj.confidenz.dtos.NotificationDto;
import com.isj.confidenz.dtos.Notification_adminDto;
import com.isj.confidenz.models.Employe;
import com.isj.confidenz.models.Entreprise;
import com.isj.confidenz.models.Notification;
import com.isj.confidenz.models.Notification_admin;
import com.isj.confidenz.repositories.EmployeRepository;
import com.isj.confidenz.repositories.EntrepriseRepository;
import com.isj.confidenz.repositories.NotificationRepository;
import com.isj.confidenz.repositories.Notification_adminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class NotificationService implements INotification {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepositoy;

    @Autowired
    private Notification_adminRepository notification_adminRepository;
    @Override
    public void ajouterNotif(NotificationDto notificationDto) {

        List<Employe> employes = employeRepository.findByEntreprise(notificationDto.getEntreprise());

        for (Employe employe: employes){
            Notification notification = new Notification();
            notification.setContenu(notificationDto.getContenu());
            notification.setCreatedate(notificationDto.getCreatedate());
            notification.setEmploye(employe);
            notification.setEntreprise(notificationDto.getEntreprise());
            notification.setVue(notificationDto.getVue());
            notificationRepository.save(notification);
        }
    }

    @Override
    public List<Notification> notificationParEmploye(String employe) {
        List<Notification> list = notificationRepository.findByVueAndEmploye(false, employe);
        return list;
    }




    @Override
    public void changerStatus(NotificationDto notificationDto) {

        Notification notification = new  Notification();
        notification.setId(notificationDto.getId());
        notification.setCreatedate(notificationDto.getCreatedate());
        notification.setEntreprise(notificationDto.getEntreprise());
        notification.setContenu(notificationDto.getContenu());
        notification.setEmploye(notificationDto.getEmploye());
        notification.setVue(true);

        System.out.println("\n\n\n\n" + notification);
        System.out.println("\n\n\n\n" + notificationDto);
        notificationRepository.save(notification);
    }

    @Override
    public void changerStatusNotifAdmin(Notification_adminDto notification_adminDto) {
        Notification_admin notification_admin = new Notification_admin();
        notification_admin.setId( notification_adminDto.getId());
        notification_admin.setCreatedate( notification_adminDto.getCreatedate());
        notification_admin.setEntreprise( notification_adminDto.getEntreprise());
        notification_admin.setContenu( notification_adminDto.getContenu());
        notification_admin.setEmploye( notification_adminDto.getEmploye());
        notification_admin.setVue(true);

        System.out.println("\n\n\n\n" +  notification_admin);
        System.out.println("\n\n\n\n" +  notification_adminDto);
        notification_adminRepository.save(notification_admin);
    }

    @Override
    public void envoyerNotifAdmin(String idemploye, String contenu, String datecreate) {
        Employe employe = employeRepository.findById(idemploye).get();
        Entreprise entreprise = entrepriseRepositoy.findByNomuser(employe.getEntreprise().getNomuser()).get();
        Notification_admin notificationAdmin = new Notification_admin();
        notificationAdmin.setContenu(contenu);
        notificationAdmin.setEmploye(employe);
        notificationAdmin.setEntreprise(entreprise);
        notificationAdmin.setCreatedate(datecreate);
        notificationAdmin.setVue(false);

        notification_adminRepository.save(notificationAdmin);
    }

    @Override
    public List<Notification_admin> liseNotifsNonVue(String nomuser) {
        Entreprise entreprise = entrepriseRepositoy.findByNomuser(nomuser).get();

        return notification_adminRepository.findByVueAndEntreprise(false, entreprise.getId());
    }
}

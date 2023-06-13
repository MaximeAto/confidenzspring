package com.isj.confidenz.repositories;

import com.isj.confidenz.models.Notification;
import com.isj.confidenz.models.Notification_admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Notification_adminRepository extends JpaRepository<Notification_admin, String> {
    @Query(value = "SELECT * FROM Notification_admin n WHERE n.vue = ?1 AND n.id_entreprise = ?2", nativeQuery = true)
    List<Notification_admin> findByVueAndEntreprise(boolean vue, int entreprise);
}

package com.isj.confidenz.repositories;
import com.isj.confidenz.models.Employe;
import com.isj.confidenz.models.Notification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {

    @Query(value = "SELECT * FROM notification n WHERE n.vue = ?1 AND n.id_employe = ?2", nativeQuery = true)
    List<Notification> findByVueAndEmploye(boolean vue, String employe);
    List<Notification> findByEmploye(Employe employe);
    Notification findById(Long id);
}

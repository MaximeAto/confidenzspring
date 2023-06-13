package com.isj.confidenz.repositories;
import com.isj.confidenz.models.Employe;
import com.isj.confidenz.models.Entreprise;
import com.isj.confidenz.models.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface FichierRepository extends JpaRepository<Fichier, String> {
    public List<Fichier> findByEntreprise(Entreprise entreprise);
    public Optional<Fichier> findById(Long id);

    public Optional<Fichier> findByNom(String nom);
    boolean existsByNomAndEntreprise(String nom, Entreprise entreprise);
}

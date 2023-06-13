package com.isj.confidenz.repositories;
import com.isj.confidenz.models.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, String >{
    public Optional<Entreprise> findByNomuser(String nomuser);

}

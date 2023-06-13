package com.isj.confidenz.repositories;

import com.isj.confidenz.dtos.EntrepriseDto;
import com.isj.confidenz.models.Employe;
import com.isj.confidenz.models.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, String> {
    public Optional<Employe> findByEmail(String email);
    public  Optional<Employe> findById(String id);
    public List<Employe> findByEntreprise(Entreprise entreprise);
    boolean existsByIdAndEntreprise(String id, Entreprise entreprise);
}

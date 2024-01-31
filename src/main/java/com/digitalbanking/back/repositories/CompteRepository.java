package com.digitalbanking.back.repositories;

import com.digitalbanking.back.models.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {
    // Méthodes personnalisées pour le Compte, comme trouver par numéro de compte, etc.
}
package com.digitalbanking.back.repositories;

import com.digitalbanking.back.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    // Vous pouvez ajouter des méthodes personnalisées ici, par exemple pour trouver un utilisateur par email.
}

package com.digitalbanking.back.service;

import com.digitalbanking.back.models.Role;
import com.digitalbanking.back.models.Utilisateur;
import com.digitalbanking.back.repositories.RoleRepository;
import com.digitalbanking.back.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
    }

    public Utilisateur createOrUpdateUtilisateur(Utilisateur utilisateur, List<String> roleNames) {
        Set<Role> roles = utilisateur.getRoles();
        if (roleNames != null && !roleNames.isEmpty()) {
            roleNames.forEach(roleName -> {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                roles.add(role);
            });
        }
        utilisateur.setRoles(roles);
        return utilisateurRepository.save(utilisateur);
    }

    public void addRoleToUser(Utilisateur utilisateur, String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
        utilisateur.getRoles().add(role);
        utilisateurRepository.save(utilisateur);
    }


    public Optional<Utilisateur> getUtilisateurById(Integer id) {
        return utilisateurRepository.findById(id);
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public void deleteUtilisateur(Integer id) {
        utilisateurRepository.deleteById(id);
    }

    // ... Autres méthodes ...
}

package com.digitalbanking.back.controller;

import com.digitalbanking.back.models.Utilisateur;
import com.digitalbanking.back.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur, @RequestParam List<String> roleNames) {
        Utilisateur created = utilisateurService.createOrUpdateUtilisateur(utilisateur, roleNames);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Integer id) {
        return utilisateurService.getUtilisateurById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Integer id, @RequestBody Utilisateur utilisateur, @RequestParam List<String> roleNames) {
        return utilisateurService.getUtilisateurById(id)
                .map(existingUser -> {
                    utilisateur.setUserId(existingUser.getUserId());
                    Utilisateur updated = utilisateurService.createOrUpdateUtilisateur(utilisateur, roleNames);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Integer id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/roles")
    public ResponseEntity<?> addRolesToUser(@PathVariable Integer id, @RequestBody List<String> roles) {
        try {
            Utilisateur utilisateur = utilisateurService.getUtilisateurById(id)
                    .orElseThrow(() -> new RuntimeException("User not found: " + id));

            for (String roleName : roles) {
                utilisateurService.addRoleToUser(utilisateur, roleName);
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}

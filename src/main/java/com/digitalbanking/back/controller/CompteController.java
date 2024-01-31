package com.digitalbanking.back.controller;

import com.digitalbanking.back.models.Compte;
import com.digitalbanking.back.models.Utilisateur;
import com.digitalbanking.back.service.CompteService;
import com.digitalbanking.back.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    private final CompteService compteService;
    private final UtilisateurService utilisateurService;

    @Autowired
    public CompteController(CompteService compteService, UtilisateurService utilisateurService) {
        this.compteService = compteService;
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    public ResponseEntity<?> createCompte(@RequestBody Compte compte) {
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(compte.getUtilisateur().getUserId());
        if (utilisateur.isPresent()) {
            compte.setUtilisateur(utilisateur.get());
            Compte created = compteService.createOrUpdateCompte(compte);
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.badRequest().body("Utilisateur not found with ID: " + compte.getUtilisateur().getUserId());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompteById(@PathVariable Integer id) {
        return compteService.getCompteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Compte> getAllComptes() {
        return compteService.getAllComptes();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompte(@PathVariable Integer id, @RequestBody Compte compte) {
        return compteService.getCompteById(id)
                .map(existingAccount -> {
                    Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(compte.getUtilisateur().getUserId());
                    if (utilisateur.isPresent()) {
                        compte.setUtilisateur(utilisateur.get());
                        compte.setCompteId(existingAccount.getCompteId());
                        Compte updated = compteService.createOrUpdateCompte(compte);
                        return ResponseEntity.ok(updated);
                    } else {
                        return ResponseEntity.badRequest().body("Utilisateur not found with ID: " + compte.getUtilisateur().getUserId());
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompte(@PathVariable Integer id) {
        if (compteService.getCompteById(id).isPresent()) {
            compteService.deleteCompte(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

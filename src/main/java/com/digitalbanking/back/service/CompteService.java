package com.digitalbanking.back.service;

import com.digitalbanking.back.models.Compte;
import com.digitalbanking.back.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteService {

    private final CompteRepository compteRepository;

    @Autowired
    public CompteService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    public Compte createOrUpdateCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    public Optional<Compte> getCompteById(Integer id) {
        return compteRepository.findById(id);
    }

    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    public void deleteCompte(Integer id) {
        compteRepository.deleteById(id);
    }
}

package com.digitalbanking.back.repositories;

import com.digitalbanking.back.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    // Requêtes personnalisées pour les transactions, par exemple par date ou entre comptes.
}
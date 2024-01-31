package com.digitalbanking.back.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    private BigDecimal montant;

    @Column(name = "date_de_transaction", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp dateDeTransaction;

    private String description;

    @OneToOne
    @JoinColumn(name = "compte_source_id", referencedColumnName = "compte_id")
    private Compte compteSource;

    @OneToOne
    @JoinColumn(name = "compte_dest_id", referencedColumnName = "compte_id")
    private Compte compteDestination;



    // Ajoutez les getters et setters pour chaque champ

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Compte getCompteSource() {
        return compteSource;
    }

    public void setCompteSource(Compte compteSource) {
        this.compteSource = compteSource;
    }

    public Compte getCompteDestination() {
        return compteDestination;
    }

    public void setCompteDestination(Compte compteDestination) {
        this.compteDestination = compteDestination;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Timestamp getDateDeTransaction() {
        return dateDeTransaction;
    }

    public void setDateDeTransaction(Timestamp dateDeTransaction) {
        this.dateDeTransaction = dateDeTransaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

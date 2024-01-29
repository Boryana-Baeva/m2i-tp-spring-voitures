package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "voitures")
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "marque")
    private String marque;
    @Column(name = "modele")
    private String modele;

    @Column(name = "annee")
    private Integer annee;

    @Column(name = "couleur")
    private String couleur;

    @Column(name = "date_immatriculation")
    private LocalDate dateImmatriculation;

    @Column(name = "numero_immatriculation")
    private String numeroImmatriculation;

    public String getMarque() {
        return marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Voiture() {
    }

    public Voiture(String marque, String modele, Integer annee, String couleur, LocalDate dateImmatriculation, String numeroImmatriculation) {
        this.marque = marque;
        this.modele = modele;
        this.annee = annee;
        this.couleur = couleur;
        this.dateImmatriculation = dateImmatriculation;
        this.numeroImmatriculation = numeroImmatriculation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroImmatriculation() {
        return numeroImmatriculation;
    }

    public void setNumeroImmatriculation(String numeroImmatriculation) {
        this.numeroImmatriculation = numeroImmatriculation;
    }

    public LocalDate getDateImmatriculation() {
        return dateImmatriculation;
    }

    public void setDateImmatriculation(LocalDate dateImmatriculation) {
        this.dateImmatriculation = dateImmatriculation;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public String getModele() {
        return modele;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int calculateAge() {
        int age = (int) ChronoUnit.YEARS.between(LocalDate.now(), this.getDateImmatriculation());
        return age;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", annee=" + annee +
                ", couleur='" + couleur + '\'' +
                ", dateImmatriculation=" + dateImmatriculation +
                ", numeroImmatriculation='" + numeroImmatriculation + '\'' +
                '}';
    }
}

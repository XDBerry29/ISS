package com.example.bibliotecaiss.model;

import javax.persistence.*;
import javax.persistence.Entity;


@Entity
@Table(name = "exemplare")
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ex_id")
    private Long cod_unic;
    @Column(name = "disponibilitate")
    private boolean disponibilitate;
    @Column(name = "mentiuni")
    private String mentiuni;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Carte carte;

    public Exemplar(Long cod_unic, boolean disponibilitate, String mentiuni, Carte carte) {
        this.cod_unic = cod_unic;
        this.disponibilitate = disponibilitate;
        this.mentiuni = mentiuni;
        this.carte = carte;
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public Exemplar() {

    }

    public Long getCod_unic() {
        return cod_unic;
    }

    public void setCod_unic(Long cod_unic) {
        this.cod_unic = cod_unic;
    }

    public boolean isDisponibilitate() {
        return disponibilitate;
    }

    public void setDisponibilitate(boolean disponibilitate) {
        this.disponibilitate = disponibilitate;
    }

    public String getMentiuni() {
        return mentiuni;
    }

    public void setMentiuni(String mentiuni) {
        this.mentiuni = mentiuni;
    }
}

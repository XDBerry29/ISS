package com.example.bibliotecaiss.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "carti")
public class Carte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titlu")
    private String titlu;

    @Column(name = "autor")
    private String autor;
    @Column(name = "gen")
    private String gen;


    public Carte(Long id, String titlu, String autor, String gen) {
        this.id = id;
        this.titlu = titlu;
        this.autor = autor;
        this.gen = gen;
    }

    public Carte() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }
}

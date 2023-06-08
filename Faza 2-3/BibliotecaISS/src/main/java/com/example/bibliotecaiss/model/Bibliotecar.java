package com.example.bibliotecaiss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bibliotecari")
public class Bibliotecar {
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "nume")
    private String nume;
    @Column(name = "telefon")
    private String telefon;
    @Column(name = "parola")
    private String parola;

    public Bibliotecar(String username, String nume, String telefon, String parola) {
        this.username = username;
        this.nume = nume;
        this.telefon = telefon;
        this.parola = parola;
    }

    public Bibliotecar() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }
}

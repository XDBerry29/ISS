package com.example.bibliotecaiss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "abonati")
public class Abonat {

    @Id
    @Column(name = "cnp")
    private String cnp;
    @Column(name = "nume")
    private String nume;
    @Column(name = "email")
    private String email; //adresa
    @Column(name = "telefon")
    private String telefon;
    @Column(name = "parola")
    private String parola;

    public Abonat(String cnp, String nume, String email, String telefon, String parola) {
        this.cnp = cnp;
        this.nume = nume;
        this.email = email;
        this.telefon = telefon;
        this.parola = parola;
    }

    public Abonat() {

    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

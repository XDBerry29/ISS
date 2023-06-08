package com.example.bibliotecaiss.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Service {
    CarteDbRepo repoCarte;
    AbonatDbRepo repoAbonat;
    BibliotecarDbRepo repoBibliotecar;

    ExemplarDbRepo repoExemplar;

    ImprumutDbRepo repoImprumut;

    public Service(CarteDbRepo repoCarte, AbonatDbRepo repoAbonat, BibliotecarDbRepo repoBibliotecar, ExemplarDbRepo repoExemplar, ImprumutDbRepo repoImprumut) {
        this.repoCarte = repoCarte;
        this.repoAbonat = repoAbonat;
        this.repoBibliotecar = repoBibliotecar;
        this.repoExemplar = repoExemplar;
        this.repoImprumut = repoImprumut;
    }

    public List<Carte> getAllCarti(){
        return repoCarte.findAll();
    }

    public boolean loginAbonat(String email, String parola){
        Abonat abonat = repoAbonat.findByEmail(email);

        if(abonat != null)
        {
            return Objects.equals(abonat.getParola(), parola);
        }

        return false;
    }

    public boolean loginBibliotecar(String username, String parola){
        Bibliotecar bibliotecar = repoBibliotecar.findByUsername(username);

        if(bibliotecar != null)
        {
            return Objects.equals(bibliotecar.getParola(), parola);
        }
        return false;
    }

    public void addAbonat(String cnp, String email, String nume,String telefon, String parola)
    {
        repoAbonat.save(new Abonat(cnp, nume, email, telefon, parola));
    }



    public int checkFields(String email, String password) {


          boolean isAbonat = loginAbonat(email, password);

          if (isAbonat)
            return 1;

        boolean isBibliotecar = loginBibliotecar(email, password);

        if(isBibliotecar)
            return 2;


        return 0;

    }


    public List<Exemplar> getAllAvailableExemplare() {
        return repoExemplar.findAll().stream().filter(Exemplar::isDisponibilitate).toList();
    }

    public Abonat findAbonatByCNP(String cnp) {
        return repoAbonat.findByCNP(cnp);
    }
    public Abonat findAbonat(String email) {
        return repoAbonat.findByEmail(email);
    }

    public void imprumutaExemplar(Abonat abonat, Exemplar exemplar) {
        exemplar.setDisponibilitate(false);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime returnDate = now.plusDays(7);
        Imprumut imprumut = new Imprumut(null, exemplar, abonat, now, returnDate);
        repoImprumut.save(imprumut);
        repoExemplar.save(exemplar);
    }

    public void returnExemplar(Long codExemplar) {
        Exemplar exemplar = repoExemplar.findByID(codExemplar);
        exemplar.setDisponibilitate(true);
        repoExemplar.save(exemplar);
        Imprumut imprumut = repoImprumut.findByCodEx(codExemplar);
        repoImprumut.delete(imprumut.getId());
    }

    public void extendLoan(Long codExemplar, int nrZile) {
        Imprumut imprumut = repoImprumut.findByCodEx(codExemplar);
        LocalDateTime returnDate = imprumut.getDataRetur();
        imprumut.setDataRetur(returnDate.plusDays(nrZile));
        repoImprumut.save(imprumut);
    }

    public void addBook(String titlu, String autor, String gen, String mentiuni) {

        Carte carte = repoCarte.findCarteByTitluAndAutor(titlu,autor);
        if (carte == null){
            carte = new Carte(null,titlu,autor,gen);
            repoCarte.save(carte);
            carte= repoCarte.findCarteByTitluAndAutor(titlu,autor);
            Exemplar exemplar = new Exemplar(null,true,mentiuni,carte);
            repoExemplar.save(exemplar);
        }else{
            Exemplar exemplar = new Exemplar(null,true,mentiuni,carte);
            repoExemplar.save(exemplar);
        }
    }
}

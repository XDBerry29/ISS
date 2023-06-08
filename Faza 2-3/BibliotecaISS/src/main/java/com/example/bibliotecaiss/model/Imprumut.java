package com.example.bibliotecaiss.model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "imprumuturi")
public class Imprumut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imprumut_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cod_ex", nullable = false)
    private Exemplar exemplar;

    @ManyToOne
    @JoinColumn(name = "abonat_id", nullable = false)
    private Abonat abonat;

    @Column(name = "data_imprumut", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataImprumut;

    @Column(name = "data_retur", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataRetur;

    public Imprumut(Long id, Exemplar exemplar, Abonat abonat, LocalDateTime dataImprumut, LocalDateTime dataRetur) {
        this.id = id;
        this.exemplar = exemplar;
        this.abonat = abonat;
        this.dataImprumut = dataImprumut;
        this.dataRetur = dataRetur;
    }

    public Imprumut() {

    }

    public Long getId() {
        return id;
    }


    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public Abonat getAbonat() {
        return abonat;
    }

    public void setAbonat(Abonat abonat) {
        this.abonat = abonat;
    }

    public LocalDateTime getDataImprumut() {
        return dataImprumut;
    }

    public void setDataImprumut(LocalDateTime dataImprumut) {
        this.dataImprumut = dataImprumut;
    }

    public LocalDateTime getDataRetur() {
        return dataRetur;
    }

    public void setDataRetur(LocalDateTime dataRetur) {
        this.dataRetur = dataRetur;
    }
}
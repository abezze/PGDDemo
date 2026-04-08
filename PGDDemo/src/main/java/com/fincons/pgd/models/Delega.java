package com.fincons.pgd.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "delega")
public class Delega {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "codiceUnivocoDelega", unique = true)
    private String codiceUnivocoDelega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUtenteDelegato_FK")
    private Utente utenteDelegato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUtenteDelegante_FK")
    private Utente utenteDelegante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idScenario_FK")
    private Scenario scenario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOperatore") // operatore che ha gestito la delega
    private Operatore operatore;

    
    private LocalDate dataInizioValidita;
    
    private LocalDate dataFineValidita;
    
    @Column(name = "dataCreazione")
    private LocalDateTime dataCreazione;

    @Column(name = "dataUltimoAggiornamento")
    private LocalDateTime dataUltimoAggiornamento;

    
    @Column(name = "dicEsercenteRespGenitorialeDelegato")
    private Boolean dicEsercenteRespGenitorialeDelegato;

    @Column(name = "dicEsercenteRespGenitorialeApprovatore")
    private Boolean dicEsercenteRespGenitorialeApprovatore;

    @Column(name = "flagResponsabilitaGenitoriale")
    private Boolean flagResponsabilitaGenitoriale;

   
    @Column(name = "esitoVerificaResponsabilitaGenitoriale")
    private String esitoVerificaResponsabilitaGenitoriale;

    @Column(name = "dataVideoRegistrazione")
    private LocalDateTime dataVideoRegistrazione;

    @Column(name = "idVideoChiamata")
    private String idVideoChiamata;
/*
    @OneToMany(mappedBy = "delega", cascade = CascadeType.ALL)
    private List<DocumentiAllegati> documenti;
*/
    @OneToMany(mappedBy = "delega")
    private List<StoricoDelega> storicoStati;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUltimoStatoDelega", nullable = false)
    private StatoDelega statoCorrente;
}

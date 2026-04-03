package com.fincons.pgd.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
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

    
    private LocalDate dataInizioValidita;

    
    private LocalDate dataFineValidita;
/*
    @OneToMany(mappedBy = "delega", cascade = CascadeType.ALL)
    private List<DocumentiAllegati> documenti;
*/
    @OneToMany(mappedBy = "delega")
    private List<StoricoDelega> storicoStati;
}

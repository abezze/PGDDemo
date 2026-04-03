package com.fincons.pgd.models;

import java.time.LocalDateTime;
import java.util.List;

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


@Getter 
@Setter
@Entity
@Table(name = "Appuntamento")
public class Appuntamento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDelegato_FK")
    private Utente delegato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDelegante_FK")
    private Utente delegante;

    private LocalDateTime dataCreazioneAppuntamento;

    @OneToMany(mappedBy = "appuntamento")
    private List<StoricoStatoAppuntamento> storicoStati;

}

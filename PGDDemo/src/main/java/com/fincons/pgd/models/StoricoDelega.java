package com.fincons.pgd.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "storicodelega")
public class StoricoDelega {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDelega_FK", nullable = false)
    private Delega delega;
/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJob_FK")
    private Job job; // Valorizzato se il cambio stato è avvenuto tramite un processo batch
*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idStatoDelega_FK", nullable = false)
    private StatoDelega statoDelega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUtenteCambioStato_FK")
    private Utente utenteCambioStato; // Può essere null se l'azione è automatica (batch)

    @Column(name = "dataCambioStato", nullable = false)
    private LocalDateTime dataCambioStato;

    @Column(name = "causa", length = 500)
    private String causa;

}

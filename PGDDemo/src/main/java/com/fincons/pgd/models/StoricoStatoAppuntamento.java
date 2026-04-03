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
@Table(name = "storicostatoappuntamento")
public class StoricoStatoAppuntamento {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAppuntamento_FK", nullable = false)
    private Appuntamento appuntamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idStatoAppuntamento_FK", nullable = false)
    private StatoAppuntameno statoAppuntamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUtenteCambioStato_FK")
    private Utente utenteCambioStato;

    @Column(name = "dataCambioStato", nullable = false)
    private LocalDateTime dataCambioStato;

}

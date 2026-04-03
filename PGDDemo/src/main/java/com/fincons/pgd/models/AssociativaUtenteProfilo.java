package com.fincons.pgd.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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
@Table(name = "associativautenteprofilo")
public class AssociativaUtenteProfilo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "idUtente_FK")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "idProfilo_FK")
    private Profilo profilo;

    private LocalDateTime dataAbilitazione;
    private Boolean flagAbilitazione;
}

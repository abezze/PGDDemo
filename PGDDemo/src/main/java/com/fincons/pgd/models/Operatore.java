package com.fincons.pgd.models;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "operatore")
public class Operatore {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "codiceUnivoco", nullable = false, unique = true)
    private String codiceUnivoco;

    // deleghe gestite da questo operatore
    @OneToMany(mappedBy = "operatore")
    private List<Delega> delegheGestite;

    // profili associati all'operatore
    @OneToMany(mappedBy = "operatore")
    private Set<AssociativaUtenteProfilo> profiliAssegnati;

    // log delle attività
    /*@OneToMany(mappedBy = "operatore")
    private List<LogAttivitaUtente> logAttivita;*/

}

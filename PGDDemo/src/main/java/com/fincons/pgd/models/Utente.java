package com.fincons.pgd.models;

import java.util.List;
import java.util.Set;

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
@Table(name = "utente")
public class Utente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String nome;
    private String cognome;
    private String codiceFiscale;
    private String emailEmailCertificata;

    @OneToMany(mappedBy = "utente")
    private Set<AssociativaUtenteProfilo> profili;

    @OneToMany(mappedBy = "utenteDelegante")
    private List<Delega> delegheConcesse;
}

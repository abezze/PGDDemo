package com.fincons.pgd.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
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

    @Column(name = "idPNR", nullable = false, unique = true)
    private String idPNR;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "dataNascita", nullable = false)
    private LocalDate dataNascita;

    @Column(name = "residenza", nullable = false)
    private String residenza;

    @Column(name = "luogoDiNascita", nullable = false)
    private String luogoDiNascita;

    @Column(name = "codiceFiscale", nullable = false, unique = true, length = 16)
    private String codiceFiscale;

    @Column(name = "indirizzoEmail", nullable = false)
    private String indirizzoEmail;

    @Column(name = "flagEmailCertificata")
    private Boolean flagEmailCertificata;

    @Column(name = "flagAccettazionePolicy")
    private Boolean flagAccettazionePolicy;

    @OneToMany(mappedBy = "utente")
    private Set<AssociativaUtenteProfilo> profili;

    @OneToMany(mappedBy = "utenteDelegante")
    private List<Delega> delegheConcesse;
}

package com.fincons.pgd.models;

import java.time.LocalDateTime;

import com.fincons.pgd.enums.Severity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Data
@Table(name = "logproxy")
public class LogProxy {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "servizioChiamato")
    private String servizioChiamato; // Il nome dell'endpoint o del servizio esterno

    @Column(name = "dataChiamata", nullable = false)
    private LocalDateTime dataChiamata;

    @Column(name = "dataRisposta")
    private LocalDateTime dataRisposta;

    @Column(name = "datiInput", columnDefinition = "TEXT")
    private String datiInput; // Il payload inviato (spesso JSON o XML)

    @Column(name = "datiOutput", columnDefinition = "TEXT")
    private String datiOutput; // La risposta ricevuta dal servizio

    @Column(name = "statusCode")
    private Integer statusCode; // es. 200, 404, 500

    @Column(name = "eccezione", columnDefinition = "TEXT")
    private String eccezione; // Tracciamento di eventuali errori Java o stacktrace

    @Enumerated(EnumType.STRING)
    @Column(name = "severity")
    private Severity severity; // INFO, WARNING, ERROR

    /**
     * Relazione opzionale con la Delega.
     * Serve a capire a quale pratica appartiene questa chiamata di log.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDelega_FK")
    private Delega delega;


}

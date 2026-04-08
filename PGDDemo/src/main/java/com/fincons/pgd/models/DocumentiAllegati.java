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
@Table(name = "documentiallegati")
public class DocumentiAllegati {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDelega_FK", nullable = false)
    private Delega delega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipologiaDocumento_FK", nullable = false)
    private TipologiaDocumento tipologiaDocumento;

    @Column(name = "nomeFile", nullable = false, unique = true)
    private String nomeFile;

    @Column(name = "pathBucket")
    private String pathBucket; 

    @Column(name = "dataCaricamento", nullable = false)
    private LocalDateTime dataCaricamento;

    @Column(name = "formatoFile", length = 10)
    private String formatoFile; 

}

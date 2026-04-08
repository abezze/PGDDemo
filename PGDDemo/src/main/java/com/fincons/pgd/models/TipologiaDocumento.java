package com.fincons.pgd.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "tipologiadocumento")
public class TipologiaDocumento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "nomeModello", nullable = false)
    private String nomeModello;

    @Column(name = "formatiAmmessi")
    private String formatiAmmessi; // Spesso una stringa separata da virgole (es. "PDF,JPEG")

    @Column(name = "nomeFile")
    private String nomeFile; // Eventuale nome del template scaricabile

    @Column(name = "pathBucket")
    private String pathBucket;

}

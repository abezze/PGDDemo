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
@Table(name = "profilo")
public class Profilo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "nomeProfilo", nullable = false, unique = true)
    private String nomeProfilo;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "gruppiActiveDirectory")
    private String gruppiActiveDirectory;

}

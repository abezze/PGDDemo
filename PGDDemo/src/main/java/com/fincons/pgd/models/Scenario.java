package com.fincons.pgd.models;

import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name = "scenario")
public class Scenario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "labelScenario")
    private String labelScenario;

    @Column(name = "descrizione", columnDefinition = "TEXT")
    private String descrizione;

    @Column(name = "flagAttivo")
    private Boolean flagAttivo;

    /*
    @OneToMany(mappedBy = "scenario", cascade = CascadeType.ALL)
    private List<ProfiliCoinvolti> profiliCoinvolti;

    
    @OneToMany(mappedBy = "scenario")
    private List<ControlliScenario> controlliScenario;

    
    @OneToMany(mappedBy = "scenario")
    private List<AssociativaScenarioTipoDocumento> configurazioneDocumenti;
*/
    
    @OneToMany(mappedBy = "scenario")
    private List<Delega> deleghe;

}

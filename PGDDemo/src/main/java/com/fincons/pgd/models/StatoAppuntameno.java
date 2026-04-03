package com.fincons.pgd.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "statoappuntamento")
public class StatoAppuntameno {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "nomeStato", nullable = false, unique = true, length = 100)
    private String nomeStato;

    @Column(name = "descrizioneStato", length = 500)
    private String descrizioneStato;

   
    @OneToMany(mappedBy = "statoAppuntamento", fetch = FetchType.LAZY)
    private List<StoricoStatoAppuntamento> recordsStorico;

}

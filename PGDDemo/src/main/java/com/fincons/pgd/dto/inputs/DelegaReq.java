package com.fincons.pgd.dto.inputs;

import com.fincons.pgd.dto.outputs.UtenteDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class DelegaReq {

    private Long id;
    private String codiceUnivocoDelega;
    private UtenteDTO utenteDelegato;
    private UtenteDTO utenteDelegante;
    //private ScenarioDTO scenario;
    //private OperatoreDTO operatore;
    private LocalDate dataInizioValidita;
    private LocalDate dataFineValidita;
    private LocalDateTime dataCreazione;
    private LocalDateTime dataUltimoAggiornamento;
    private Boolean dicEsercenteRespGenitorialeDelegato;
    private Boolean dicEsercenteRespGenitorialeApprovatore;
    private Boolean flagResponsabilitaGenitoriale;
    private String esitoVerificaResponsabilitaGenitoriale;
    private LocalDateTime dataVideoRegistrazione;
    private String idVideoChiamata;

    // private StatoDelegaDTO statoCorrente;
    // private TipoDelegatoDTO tipoDelegato;

}

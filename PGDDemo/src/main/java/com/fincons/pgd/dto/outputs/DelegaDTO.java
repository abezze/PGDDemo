package com.fincons.pgd.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("ALL")
@Getter
@Setter
@ToString
@Builder
public class DelegaDTO {
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
    private List<DocumentiAllegatiDTO> documenti;
   // private List<StoricoDelegaDTO> storicoStati;
   // private StatoDelegaDTO statoCorrente;
    // private TipoDelegatoDTO tipoDelegato;

}

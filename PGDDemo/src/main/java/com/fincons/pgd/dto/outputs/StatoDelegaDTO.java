package com.fincons.pgd.dto.outputs;

import lombok.*;

@SuppressWarnings("ALL")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatoDelegaDTO {
    private Long id;
    private String nomeStato;
    private String descrizioneStato;

}

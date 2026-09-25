package com.fincons.pgd.controllers;

import com.fincons.pgd.dto.inputs.UtenteReq; // Il tuo DTO esistente
import com.fincons.pgd.dto.outputs.UtenteDTO;
import com.fincons.pgd.response.Resp;      // Il tuo oggetto Risposta esistente
import com.fincons.pgd.services.interfaces.IUtenteServices;
import com.fincons.pgd.services.interfaces.IMessaggioServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller // 👈 Diventa un Controller standard, non più @RestController
public class UtenteGraphqlController {

    private final IUtenteServices utS;
    private final IMessaggioServices msgS;

    // 1. GESTIONE DELLA CREAZIONE (Ex POST REST)
    // Il nome del metodo corrisponde a 'create' nella Mutation dello schema
    @MutationMapping
    public Resp create(@Argument UtenteReq req) {
        log.info("Richiesta di creazione utente in GraphQL per: {}", req.getCodiceFiscale());

        // Esegui la stessa identica logica del tuo Service
        utS.create(req);

        // Prepari la risposta proprio come prima
        Resp r = new Resp();
        r.setMsg(msgS.get("rest_created"));

        return r; // Ritorna l'oggetto direttamente. Ci pensa GraphQL a serializzarlo
    }

    // 2. GESTIONE DELLA LISTA (Ex GET REST)
    // Il nome del metodo corrisponde a 'list' nella Query dello schema
    @QueryMapping
    public List<UtenteDTO> list() throws Exception {
        log.info("Richiesta lista utenti in GraphQL");

        // Se il database è vuoto restituirà una lista vuata.
        // Se il service lancia una RuntimeException (es. errore di connessione al DB),
        // Spring for GraphQL intercetta l'errore da solo e lo sposta nel blocco "errors" del JSON.
        return utS.list();
    }
}

package com.fincons.pgd.controllers;

import com.fincons.pgd.dto.inputs.DelegaReq;
import com.fincons.pgd.dto.outputs.DelegaDTO;
import com.fincons.pgd.dto.outputs.RespDTO;
import com.fincons.pgd.services.interfaces.IDelegaServices;
import com.fincons.pgd.services.interfaces.IMessaggioServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pgd/delega")
@Tag(name = "Delega", description = "Delega API")
public class DelegaController {

    private final IDelegaServices delegaS;
    private final IMessaggioServices msgS;

    @Operation(
            summary = "Get list of messages",
            security = { @SecurityRequirement(name = "bearer-jwt") }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = DelegaDTO.class)))
    })


    @GetMapping(value = "/get-delegation-data",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<DelegaDTO> getDelega(@RequestHeader Map<String, String> headers){
    	log.debug(" getDelega" );
        if (headers!=null && !headers.isEmpty())
        {  //	Authorization
            String authorization = headers.get("Authorization");
            String dPoP = headers.get("DPoP");
            //TODO
        }
        DelegaDTO delega = null;
        try {
            delega = delegaS.findById(new Long(1));
        } catch (Exception e){

        }
        if (delega!=null) {
	        return Flux.just(
	                delega
	        );
        } else return null;
    }

    @PostMapping("/create")
    public ResponseEntity<RespDTO> create(@RequestBody(required = true) @Valid DelegaReq req){

        String codiceUnivocoDelega = delegaS.create(req);

        RespDTO r = new RespDTO();
        if (codiceUnivocoDelega.startsWith("TEMP_"))
            r.setMsg("delega bozza");
        else
            r.setMsg("delega completa");
        r.setCodiceUnivocoDelega(codiceUnivocoDelega);

        return ResponseEntity.status(HttpStatus.CREATED).body(r);


    }

}

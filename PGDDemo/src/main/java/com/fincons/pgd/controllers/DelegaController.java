package com.fincons.pgd.controllers;

import com.fincons.pgd.dto.outputs.DelegaDTO;
import com.fincons.pgd.services.interfaces.IDelegaServices;
import com.fincons.pgd.services.interfaces.IMessaggioServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        if (headers!=null && !headers.isEmpty())
        {  //	Authorization
            String authorization = headers.get("Authorization");
            String DPoP = headers.get("DPoP");
            //TODO
        }
        DelegaDTO delega = null;
        try {
            delega = delegaS.findById(new Long(1));
        } catch (Exception e){

        }
        return Flux.just(
                delega
        );
    }
}

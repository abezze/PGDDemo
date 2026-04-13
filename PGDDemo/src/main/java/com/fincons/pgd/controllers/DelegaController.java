package com.fincons.pgd.controllers;

import com.fincons.pgd.models.Delega;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/pgd/delega")
@Tag(name = "Delega", description = "Delega API")
public class DelegaController {

    @Operation(summary = "Get Delega")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Delega.class)))
    })

    @GetMapping(value = "/hello",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Delega> sayHello() {
        log.info("Hello World!");
        return Mono.just(new Delega());
    }

    @Operation(
            summary = "Get list of messages",
            security = { @SecurityRequirement(name = "bearer-jwt") }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping(value = "/list",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Delega> getList() {
        log.info("list authenticated");
        return Flux.just(
                new Delega(),
                new Delega(),
                new Delega()
        );
    }

    @GetMapping(value = "/get-delegation-data",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Delega> getDelega(){
        return Flux.just(
                new Delega()
        );
    }
}

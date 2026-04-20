package com.fincons.pgd.advice;

import com.fincons.pgd.controllers.UtenteController;
import com.fincons.pgd.dto.outputs.ErrorDTO;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = { UtenteController.class })
@Aspect
public class UtenteControllerAdvice {
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ErrorDTO> handleAllExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO(ex.getMessage(), "Default advice"));
    }
}

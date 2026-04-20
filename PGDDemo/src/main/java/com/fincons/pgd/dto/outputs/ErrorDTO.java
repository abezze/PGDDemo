package com.fincons.pgd.dto.outputs;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorDTO {
    private final String msg;
    private final String code;
}
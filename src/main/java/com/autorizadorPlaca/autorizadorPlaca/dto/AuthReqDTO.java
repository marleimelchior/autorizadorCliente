package com.autorizadorPlaca.autorizadorPlaca.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthReqDTO {
    @NotNull(message = "Campo 'placa' é obrigatório.")
    private String placa;
    @NotNull(message = "Campo 'concessionaria' é obrigatório.")
    private Long concessionaria;
    @NotNull(message = "Campo 'praca' é obrigatório.")
    private Long praca;
}

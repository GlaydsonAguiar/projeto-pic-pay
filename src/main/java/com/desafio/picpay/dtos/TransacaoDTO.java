package com.desafio.picpay.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransacaoDTO(
        @NotNull BigDecimal valor,
        @NotNull Long remetenteId,
        @NotNull Long recebedorId) {
}

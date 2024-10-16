package com.desafio.picpay.dtos;

import java.math.BigDecimal;

public record TransacaoDTO(BigDecimal valor, Long remetenteId, Long recebedorId) {}

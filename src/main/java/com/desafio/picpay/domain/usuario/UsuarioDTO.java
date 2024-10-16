package com.desafio.picpay.domain.usuario;

import java.math.BigDecimal;

public record UsuarioDTO(String primeiroNome, String sobrenome, String documento, BigDecimal saldo, String email, String senha, TipoUsuario tipoUsuario) {}

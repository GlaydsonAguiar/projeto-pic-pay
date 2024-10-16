package com.desafio.picpay.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "usuarios")
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String primeiroNome;
    private String sobrenome;

    @Column(unique = true)
    private String documento;

    @Column(unique = true)
    private String email;

    private String password;
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

}

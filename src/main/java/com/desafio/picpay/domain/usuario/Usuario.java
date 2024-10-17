package com.desafio.picpay.domain.usuario;

import com.desafio.picpay.dtos.TipoUsuario;
import com.desafio.picpay.dtos.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "usuarios")
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    private String senha;
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    public Usuario(UsuarioDTO usuarioDTO) {
        this.primeiroNome = usuarioDTO.primeiroNome();
        this.sobrenome = usuarioDTO.sobrenome();
        this.documento = usuarioDTO.documento();
        this.email = usuarioDTO.email();
        this.senha = usuarioDTO.senha();
        this.saldo = usuarioDTO.saldo();
        this.tipoUsuario = usuarioDTO.tipoUsuario();
    }

}

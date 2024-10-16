package com.desafio.picpay.controllers;

import com.desafio.picpay.domain.usuario.Usuario;
import com.desafio.picpay.domain.usuario.UsuarioDTO;
import com.desafio.picpay.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> criandoUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario novoUsuario = this.usuarioService.criarUsuario(usuarioDTO);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuaris() {
        List<Usuario> usuarios = this.usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}

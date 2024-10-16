package com.desafio.picpay.controllers;

import com.desafio.picpay.domain.usuario.Usuario;
import com.desafio.picpay.domain.usuario.UsuarioDTO;
import com.desafio.picpay.services.UsuarioService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @POST
    public Response criandoUsuario(UsuarioDTO usuarioDTO) {
        Usuario novoUsuario = this.usuarioService.criarUsuario(usuarioDTO);
        return Response.status(Response.Status.CREATED).entity(novoUsuario).build();
    }

    @GET
    public Response listarUsuarios() {
        List<Usuario> usuarios = this.usuarioService.listarUsuarios();
        return Response.ok(usuarios).build();
    }
}

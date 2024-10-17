package com.desafio.picpay.controllers;

import com.desafio.picpay.domain.usuario.Usuario;
import com.desafio.picpay.dtos.UsuarioDTO;
import com.desafio.picpay.services.UsuarioService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @POST
    @Operation(summary = "Cadastro de usuário")
    public Response criandoUsuario(@Valid UsuarioDTO usuarioDTO) {
        Usuario novoUsuario = this.usuarioService.criarUsuario(usuarioDTO);
        return Response.status(Response.Status.CREATED).entity(novoUsuario).build();
    }

    @GET
    @Operation(summary = "Listagem de todos usuários cadastrados")
    public Response listarUsuarios() {
        List<Usuario> usuarios = this.usuarioService.listarUsuarios();
        return Response.ok(usuarios).build();
    }
}

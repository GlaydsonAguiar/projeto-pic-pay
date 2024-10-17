package com.desafio.picpay.controllers;

import com.desafio.picpay.domain.transacao.Transacao;
import com.desafio.picpay.dtos.TransacaoDTO;
import com.desafio.picpay.services.TransacaoService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/transacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Transação")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @POST
    @Operation(summary = "Realizar transação entre usuários")
    public Response criarTransacao(@Valid TransacaoDTO transacaoDTO) {
        Transacao novaTransacao = this.transacaoService.criandoTransacao(transacaoDTO);
        return Response.status(Response.Status.CREATED).entity(novaTransacao).build();
    }

    @GET
    @Operation(summary = "Listagem de todas as transações realizadas")
    public Response listarTransacoes() {
        List<Transacao> transacoes = this.transacaoService.listarTransacoes();
        return Response.ok(transacoes).build();
    }
}

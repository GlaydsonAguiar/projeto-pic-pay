package com.desafio.picpay.controllers;

import com.desafio.picpay.domain.transacao.Transacao;
import com.desafio.picpay.dtos.TransacaoDTO;
import com.desafio.picpay.services.TransacaoService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/transacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @POST
    public Response criarTransacao(TransacaoDTO transacaoDTO) throws Exception {
        Transacao novaTransacao = this.transacaoService.criandoTransacao(transacaoDTO);
        return Response.status(Response.Status.CREATED).entity(novaTransacao).build();
    }
}

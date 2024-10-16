package com.desafio.picpay.controllers;

import com.desafio.picpay.domain.transacao.Transacao;
import com.desafio.picpay.dtos.TransacaoDTO;
import com.desafio.picpay.services.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Transacao> criarTransacao(@RequestBody TransacaoDTO transacaoDTO) throws Exception {
        Transacao novaTransacao = this.transacaoService.criandoTransacao(transacaoDTO);
        return new ResponseEntity<>(novaTransacao, HttpStatus.CREATED);
    }
}

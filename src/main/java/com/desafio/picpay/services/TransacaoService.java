package com.desafio.picpay.services;

import com.desafio.picpay.client.AutorizacaoClient;
import com.desafio.picpay.domain.transacao.Transacao;
import com.desafio.picpay.domain.usuario.Usuario;
import com.desafio.picpay.dtos.AutorizacaoResponse;
import com.desafio.picpay.dtos.TransacaoDTO;
import com.desafio.picpay.repositories.TransacaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApplicationScoped
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final UsuarioService usuarioService;
    private final NotificacaoService notificacaoService;
    private final AutorizacaoClient autorizacaoClient;

    public TransacaoService(TransacaoRepository transacaoRepository,
                            UsuarioService usuarioService,
                            NotificacaoService notificacaoService,
                            @RestClient AutorizacaoClient autorizacaoClient
    ) {
        this.transacaoRepository = transacaoRepository;
        this.usuarioService = usuarioService;
        this.notificacaoService = notificacaoService;
        this.autorizacaoClient = autorizacaoClient;
    }

    public Transacao criandoTransacao(TransacaoDTO transacaoDTO) throws Exception {
        Usuario remetente = this.usuarioService.encontrarUsuarioPorId(transacaoDTO.remetenteId());
        Usuario recebedor = this.usuarioService.encontrarUsuarioPorId(transacaoDTO.recebedorId());

        this.usuarioService.validarTransacao(remetente, transacaoDTO.valor());

        boolean estaAutorizado = this.autorizacaoTransacao(remetente, transacaoDTO.valor());

        if (!estaAutorizado) {
            throw new Exception("Transação não autorizada");
        }

        Transacao transacao = new Transacao();
        transacao.setRecebedor(recebedor);
        transacao.setRemetente(remetente);
        transacao.setValor(transacaoDTO.valor());
        transacao.setTimestamp(LocalDateTime.now());

        remetente.setSaldo(remetente.getSaldo().subtract(transacao.getValor()));
        recebedor.setSaldo(recebedor.getSaldo().add(transacao.getValor()));

        this.notificacaoService.enviarNotificacao(remetente, "Trasanção realizada com sucesso");
        this.notificacaoService.enviarNotificacao(recebedor, "Trasanção recebida com sucesso");

        this.transacaoRepository.save(transacao);

        this.usuarioService.salvarUsuario(remetente);
        this.usuarioService.salvarUsuario(recebedor);

        return transacao;
    }

    public boolean autorizacaoTransacao(Usuario remetente, BigDecimal valor) throws Exception {
        try {
            AutorizacaoResponse autorizacaoResponse = autorizacaoClient.autorizar();
            return "success".equalsIgnoreCase(autorizacaoResponse.getStatus());
        } catch (Exception exception) {
            throw new Exception("Serviço de autorização está indisponível", exception);
        }
    }
}

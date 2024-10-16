package com.desafio.picpay.services;

import com.desafio.picpay.domain.transacao.Transacao;
import com.desafio.picpay.domain.usuario.Usuario;
import com.desafio.picpay.dtos.TransacaoDTO;
import com.desafio.picpay.repositories.TransacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final UsuarioService usuarioService;
    private final RestTemplate restTemplate;
    private final NotificacaoService notificacaoService;

    public TransacaoService(TransacaoRepository transacaoRepository,
                            UsuarioService usuarioService,
                            RestTemplate restTemplate,
                            NotificacaoService notificacaoService
    ) {
        this.transacaoRepository = transacaoRepository;
        this.usuarioService = usuarioService;
        this.restTemplate = restTemplate;
        this.notificacaoService = notificacaoService;
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

        this.transacaoRepository.save(transacao);

        this.usuarioService.salvarUsuario(remetente);
        this.usuarioService.salvarUsuario(recebedor);

        this.notificacaoService.enviarNotificacao(remetente, "Trasanção realizada com sucesso");
        this.notificacaoService.enviarNotificacao(recebedor, "Trasanção recebida com sucesso");

        return transacao;
    }

    public boolean autorizacaoTransacao(Usuario remetente, BigDecimal valor) {
        ResponseEntity<Map> autorizacaoResponse = this.restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if (autorizacaoResponse.getStatusCode() == HttpStatus.OK) {
            String status = (String) autorizacaoResponse.getBody().get("status");
            return "success".equalsIgnoreCase(status);
        } else return false;
    }
}

package com.desafio.picpay.services;

import com.desafio.picpay.domain.usuario.Usuario;
import com.desafio.picpay.dtos.NotificacaoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificacaoService {

    private final RestTemplate restTemplate;

    public NotificacaoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void enviarNotificacao(Usuario usuario, String mensagem) throws Exception {
        String email = usuario.getEmail();
        NotificacaoDTO notificacaoDTO = new NotificacaoDTO(email, mensagem);

        ResponseEntity<String> notificacaoResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificacaoDTO, String.class);
        if (notificacaoResponse.getStatusCode() != HttpStatus.NO_CONTENT) {
            throw new Exception("Serviço de notificação está indisponível");
        }
    }
}

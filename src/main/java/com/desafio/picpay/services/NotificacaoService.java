package com.desafio.picpay.services;

import com.desafio.picpay.client.NotificacaoClient;
import com.desafio.picpay.domain.usuario.Usuario;
import com.desafio.picpay.dtos.NotificacaoDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class NotificacaoService {

    private final NotificacaoClient notificacaoClient;

    public NotificacaoService(@RestClient NotificacaoClient notificacaoClient) {
        this.notificacaoClient = notificacaoClient;
    }

    public void enviarNotificacao(Usuario usuario, String mensagem) throws Exception {
        String email = usuario.getEmail();
        NotificacaoDTO notificacaoDTO = new NotificacaoDTO(email, mensagem);

        try {
            notificacaoClient.enviar(notificacaoDTO);
        } catch (Exception e) {
            throw new Exception("Serviço de notificação está indisponível", e);
        }
    }
}

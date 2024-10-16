package com.desafio.picpay.client;

import com.desafio.picpay.dtos.NotificacaoDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://util.devi.tools")
@Path("/api/v1/notify")
public interface NotificacaoClient {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    void enviar(NotificacaoDTO notificacaoDTO);
}

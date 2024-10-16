package com.desafio.picpay.client;

import com.desafio.picpay.dtos.AutorizacaoResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://util.devi.tools")
@Path("/api/v2/authorize")
public interface AutorizacaoClient {
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    AutorizacaoResponse autorizar();
}
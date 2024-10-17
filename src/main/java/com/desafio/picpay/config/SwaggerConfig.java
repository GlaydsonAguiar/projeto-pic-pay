package com.desafio.picpay.config;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Desafio PicPay Quarkus",
                version = "1.0.0",
                description = "Resolução do desafio do PicPay em Quarkus"
        )
)
public class SwaggerConfig extends Application {
}

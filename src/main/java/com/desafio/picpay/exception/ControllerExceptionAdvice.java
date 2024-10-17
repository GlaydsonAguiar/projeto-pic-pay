package com.desafio.picpay.exception;

import com.desafio.picpay.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.hibernate.exception.ConstraintViolationException;

@Provider
public class ControllerExceptionAdvice implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof ConstraintViolationException) {
            ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário já cadastrado", "400");
            return Response.status(Response.Status.BAD_REQUEST).entity(exceptionDTO).build();
        } else if (exception instanceof EntityNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else if (exception instanceof UsuarioExeption || exception instanceof TransacaoException) {
            ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exceptionDTO).build();
        } else {
            ExceptionDTO exceptionDTO = new ExceptionDTO("Ocorreu um erro inesperado", "500");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exceptionDTO).build();
        }
    }
}

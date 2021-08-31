package com.halmaks.sumservice.handlers;

import com.halmaks.sumservice.exceptions.AlreadyExistsException;
import com.halmaks.sumservice.models.Response;
import com.halmaks.sumservice.services.RequestDescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final RequestDescriptionService descriptionService;

    public GlobalExceptionHandler(final RequestDescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response alreadyExistsException() {
        return new Response(2, descriptionService.getDescriptionByCode(2));
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response notFoundException() {
        return new Response(1, descriptionService.getDescriptionByCode(1));
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response exception() {
        return new Response(3, descriptionService.getDescriptionByCode(3));
    }

    @ExceptionHandler(value = ClientErrorException.class)
    public Response clientErrorException(final HttpServletResponse response, final ClientErrorException e) {
        response.setStatus(e.getResponse().getStatus());
        return new Response(4, descriptionService.getDescriptionByCode(4));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidException() {
        return new Response(5, descriptionService.getDescriptionByCode(5));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response httpMessageNotReadableException() {
        return new Response(5, descriptionService.getDescriptionByCode(5));
    }
}

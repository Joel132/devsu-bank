package com.prueba.devsubank.controller;

import com.prueba.devsubank.dto.ErrorResponse;
import com.prueba.devsubank.exceptions.BankException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {BankException.class})
    public ResponseEntity<ErrorResponse> handleConflict(
            BankException ex, WebRequest request
    ){
        logger.error("Ha ocurrido un error", ex);
        ErrorResponse errorResponse = ErrorResponse.buildFromException(ex);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleConflict(
            RuntimeException ex, WebRequest request
    ){
        logger.error("Ha ocurrido un error", ex);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCodigoError("500");
        errorResponse.setMensajeError("Ha ocurrido un error inseperado");
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(value = {BindException.class, MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> handleConflict(
            Exception ex, WebRequest request
    ){
        //TODO: extraer mensajes de error
        logger.error("Ha ocurrido un error", ex);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCodigoError("400");
        errorResponse.setMensajeError("Parametros invalidos: "+ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

}

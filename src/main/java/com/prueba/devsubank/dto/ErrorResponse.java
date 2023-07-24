package com.prueba.devsubank.dto;

import com.prueba.devsubank.exceptions.BankException;

public class ErrorResponse {

    private String codigoError;

    private String mensajeError;

    public static ErrorResponse buildFromException(BankException bankException){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.codigoError = bankException.getCodigoError();
        errorResponse.mensajeError = bankException.getDescripcionError();
        return errorResponse;
    }


    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
}

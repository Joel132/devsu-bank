package com.prueba.devsubank.exceptions;

public class BankException extends RuntimeException{

    private String codigoError;
    private String descripcionError;


    public static BankException newBankException(String codigo, String descripcionError, Throwable cause){
        BankException bankException = new BankException(descripcionError,cause);
        bankException.setCodigoError(codigo);
        bankException.setDescripcionError(descripcionError);
        return bankException;
    }

    public static BankException newBankException(String codigo, String descripcionError){
        BankException bankException = new BankException(descripcionError);
        bankException.setCodigoError(codigo);
        bankException.setDescripcionError(descripcionError);
        return bankException;
    }

    public BankException() {
    }

    public BankException(String message) {
        super(message);
    }

    public BankException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankException(Throwable cause) {
        super(cause);
    }

    public BankException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }
}

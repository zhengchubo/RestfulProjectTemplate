package com.justin4u.exception;


public class ClientReportException extends RuntimeException {

    public ClientReportException() {
    }

    public ClientReportException(String ms) {
        this.message = ms;
    }


    public ClientReportException(String ms, Throwable cause) {
        this.message = ms;
        this.cause = cause;
    }

    private String message;

    private Throwable cause;

    @Override
    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
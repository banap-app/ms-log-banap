package com.banap.exceptions;

public class NoStacktraceException extends RuntimeException {
    public NoStacktraceException(String message) {
        super(message);
    }
    public NoStacktraceException(String message, final Throwable cause) { super(message,cause, true,false);}
}

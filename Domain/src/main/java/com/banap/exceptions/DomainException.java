package com.banap.exceptions;

import com.banap.validation.Error;

import java.util.List;

public class DomainException extends NoStacktraceException {
    private final List<Error> errors;

    protected DomainException(String message, final List<Error> errors) {
      super(message);
      this.errors = errors;
    }

    public static DomainException with(final Error anError) {
      return new DomainException(anError.message(), List.of(anError));
    }

    public static DomainException with(final List<Error> anErrors) {
      return new DomainException("", anErrors);
    }

    public List<Error> getErrors() {
      return errors;
    }
}

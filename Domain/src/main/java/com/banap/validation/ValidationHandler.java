package com.banap.validation;

import java.util.List;

public interface ValidationHandler {
    ValidationHandler append(ValidationHandler aHandler);

    ValidationHandler append(Error error);

    List<Error> getErrors();

    default boolean hasErrors(){
        return getErrors() != null && !getErrors().isEmpty();
    }

    default Error firstError(){
        return hasErrors()? getErrors().get(0): null;
    }

    interface Validation<T> {
        T validate();
    }

    <T> T validate(Validation<T> aValidate);
}

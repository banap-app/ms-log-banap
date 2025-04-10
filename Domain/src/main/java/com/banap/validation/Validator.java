package com.banap.validation;

public abstract class Validator {
    protected final ValidationHandler validationHandler;

    protected Validator(final ValidationHandler validationHandler){
        this.validationHandler = validationHandler;
    }

    public abstract void validate();

    protected ValidationHandler validationHandler() {
        return this.validationHandler;
    }
}

package com.banap.validation.handlers;

import com.banap.exceptions.DomainException;
import com.banap.validation.Error;
import com.banap.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class Notification implements ValidationHandler {
    private List<Error> errors;

    private Notification(List<Error> errors) {
        this.errors = errors;
    }

    public static Notification create() {
        return new Notification(new ArrayList<>());
    }

    public static Notification create(Error error) {
        return new Notification(new ArrayList<>()).append(error);
    }

    @Override
    public Notification append(ValidationHandler aHandler) {
        this.errors.addAll(aHandler.getErrors());
        return this;
    }

    @Override
    public Notification append(Error error) {
        this.errors.add(error);
        return this;
    }

    @Override
    public <T> T validate(Validation<T> aValidate) {
        try {
            return aValidate.validate();
        } catch(DomainException ex) {
            this.errors.addAll(ex.getErrors());
        } catch (Throwable t) {
            this.errors.add(new Error(t.getMessage()));
        }
        return null;
    }

    @Override
    public List<Error> getErrors() {
        return errors;
    }
}

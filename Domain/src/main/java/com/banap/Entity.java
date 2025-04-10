package com.banap;

import com.banap.validation.ValidationHandler;

import java.util.Objects;

public abstract class Entity<ID extends Identifier> {
    private final ID id;

    protected Entity(final ID id) {
        this.id = Objects.requireNonNull(id);
    }

    public ID getId() {return id;};

    public abstract void validate(ValidationHandler handler);
}

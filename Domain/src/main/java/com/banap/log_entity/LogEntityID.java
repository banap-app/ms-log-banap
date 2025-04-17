package com.banap.log_entity;

import com.banap.Identifier;

import java.util.Objects;
import java.util.UUID;

public class LogEntityID extends Identifier {
    private String value;

    private LogEntityID(final String anId) {
        this.value = anId;
    }

    public static LogEntityID unique() {
        return new LogEntityID(UUID.randomUUID().toString());
    }

    public static LogEntityID from(final LogEntityID anId) {
        return new LogEntityID(anId.getValue());
    }

    public static LogEntityID from(final String anId) {
        return new LogEntityID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LogEntityID that = (LogEntityID) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}

package com.banap.log_entity;

import com.banap.exceptions.DomainException;
import com.banap.validation.Error;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LogEntityTypeAction {
    private final static Map<Integer, String> logEntityTypeAction = new HashMap<>();

    private final Integer typeId;
    private final String typeName;

    static {
        logEntityTypeAction.put(10, "createField");

        logEntityTypeAction.put(20, "createProperty");

        logEntityTypeAction.put(30, "createAnalysis");

        logEntityTypeAction.put(11, "getField");
        logEntityTypeAction.put(12, "getAllField");

        logEntityTypeAction.put(21, "getProperty");
        logEntityTypeAction.put(22, "getAllProperty");

        logEntityTypeAction.put(31, "getAnalyze");
        logEntityTypeAction.put(32, "getAllAnalysis");
    }

    public LogEntityTypeAction(Integer typeId) {
        if(!logEntityTypeAction.containsKey(typeId)) {
            throw DomainException.with(new Error("Invalid type Action"));
        }

        this.typeId = typeId;
        this.typeName = logEntityTypeAction.get(typeId);
    }

    public Integer getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LogEntityTypeAction that = (LogEntityTypeAction) o;
        return Objects.equals(typeId, that.typeId) && Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, typeName);
    }
}

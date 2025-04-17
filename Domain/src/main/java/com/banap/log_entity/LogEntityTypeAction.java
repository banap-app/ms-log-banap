package com.banap.log_entity;

import com.banap.exceptions.DomainException;
import com.banap.validation.Error;
import com.banap.validation.ValidationHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LogEntityTypeAction {
    private final static Map<Integer, String> logEntityTypeAction = new HashMap<>();

    private final Integer typeId;
    private String typeName;

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
        this.typeId = typeId;
    }

    public void validate(ValidationHandler aHandler) {
        if(!logEntityTypeAction.containsKey(this.typeId)){
            aHandler.append(new Error("Invalid Type Action"));
        } else {
            this.typeName = logEntityTypeAction.get(this.typeId);
        }
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

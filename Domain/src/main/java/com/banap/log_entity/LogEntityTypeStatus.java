package com.banap.log_entity;

import com.banap.exceptions.DomainException;
import com.banap.validation.Error;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LogEntityTypeStatus {

    private final static Map<Integer, String> typesLogEntity = new HashMap<>();

    private final Integer typeId;
    private final String typeName;

    static {
        typesLogEntity.put(1, "notification");
        typesLogEntity.put(2, "error");
        typesLogEntity.put(3, "success");
    }

    public LogEntityTypeStatus(Integer typeId) {
        if(!typesLogEntity.containsKey(typeId)){
            throw DomainException.with(new Error("Invalid type Status"));
        }
        this.typeId = typeId;
        this.typeName = typesLogEntity.get(typeId);
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
        LogEntityTypeStatus that = (LogEntityTypeStatus) o;
        return Objects.equals(typeId, that.typeId) && Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, typeName);
    }
}

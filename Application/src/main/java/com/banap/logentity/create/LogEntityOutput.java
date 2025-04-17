package com.banap.logentity.create;

import com.banap.log_entity.LogEntity;

import java.util.UUID;

public record LogEntityOutput(Boolean success, LogEntity logEntity) {

    public static LogEntityOutput from(final Boolean success, final LogEntity logEntity)  {
        return new LogEntityOutput(success, logEntity);
    }
}

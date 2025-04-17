package com.banap.log_entity;

import java.util.UUID;

public interface LogEntityGateway {

    LogEntity create(LogEntity logEntity);
    void delete(LogEntity logEntity);
    LogEntity update(LogEntity logEntity);
    LogEntity findByUserId(UUID userId);
    LogEntity findByAuthorApplication(String authorApplication);
}

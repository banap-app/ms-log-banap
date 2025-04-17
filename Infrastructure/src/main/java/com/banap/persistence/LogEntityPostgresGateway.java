package com.banap.persistence;

import com.banap.log_entity.LogEntity;
import com.banap.log_entity.LogEntityGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LogEntityPostgresGateway implements LogEntityGateway {

    private final LogEntityRepository logEntityRepository;

    public LogEntityPostgresGateway(final LogEntityRepository logEntityRepository){
        this.logEntityRepository = logEntityRepository;
    }

    @Override
    public LogEntity create(LogEntity logEntity) {
        return save(logEntity);
    }

    @Override
    public void delete(LogEntity logEntity) {

    }

    @Override
    public LogEntity update(LogEntity logEntity) {
        return null;
    }

    @Override
    public LogEntity findByUserId(UUID userId) {
        return null;
    }

    @Override
    public LogEntity findByAuthorApplication(String authorApplication) {
        return null;
    }

    private LogEntity save(LogEntity logEntity) {
        return this.logEntityRepository.save(LogEntityJpa.from(logEntity)).toDomain();
    }

}

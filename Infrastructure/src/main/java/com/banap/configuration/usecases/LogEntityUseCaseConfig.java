package com.banap.configuration.usecases;


import com.banap.log_entity.LogEntityGateway;
import com.banap.logentity.create.DefaultLogEntityCreateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class LogEntityUseCaseConfig {

    private final LogEntityGateway logEntityGateway;

    public LogEntityUseCaseConfig(final LogEntityGateway logEntityGateway) {
        this.logEntityGateway = Objects.requireNonNull(logEntityGateway);
    }

    @Bean
    public DefaultLogEntityCreateUseCase logEntityCreateUseCase() {
        return new DefaultLogEntityCreateUseCase(logEntityGateway);
    }

}

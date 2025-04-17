package com.banap.logentity.create;

import com.banap.Result;
import com.banap.exceptions.DomainException;
import com.banap.log_entity.LogEntity;
import com.banap.log_entity.LogEntityGateway;
import com.banap.validation.Error;
import com.banap.validation.handlers.Notification;

import java.util.Objects;

public class DefaultLogEntityCreateUseCase extends LogEntityUseCase{
    private final LogEntityGateway logEntityGateway;

    public DefaultLogEntityCreateUseCase(final LogEntityGateway logEntityGateway) {
        this.logEntityGateway = Objects.requireNonNull(logEntityGateway);
    }

    @Override
    public Result<LogEntityOutput, Notification> execute(LogEntityCommand aCommand) {
        final var description = aCommand.description();
        final var titleLog = aCommand.titleLog();
        final var authorApplication = aCommand.authorApplication();
        final var logEntityTypeStatus = aCommand.logEntityTypeStatus();
        final var logEntityTypeAction = aCommand.logEntityTypeAction();
        final var propertyId = aCommand.propertyId();
        final var userId = aCommand.userId();

        final LogEntity logEntity = LogEntity.newEntity(description, titleLog, authorApplication, userId, propertyId, logEntityTypeStatus, logEntityTypeAction);
        final var notification = Notification.create();
        try {
            logEntity.validate(notification);
            if(notification.hasErrors()) {
                return new Result<>(null, notification);
            }

           //return new Result<>(LogEntityOutput.from(true, logEntity), null);
            return notification.hasErrors() ? Result.error(notification) : create(logEntity, notification);
        } catch (DomainException ex) {
            notification.append(new Error(ex.getMessage()));
            return new Result<>(null,notification);
        }

    }

    private Result<LogEntityOutput, Notification> create(final LogEntity logEntity, final Notification notification) {
        try {
            final var persistence = this.logEntityGateway.create(logEntity);
            return new Result<>(LogEntityOutput.from(true, persistence), null);
        } catch (DomainException ex) {
            notification.append(new Error(ex.getMessage()));
            return new Result<>(null, notification);
        }
    }
}


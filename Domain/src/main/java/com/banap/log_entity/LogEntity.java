package com.banap.log_entity;

import com.banap.AggregateRoot;
import com.banap.validation.ValidationHandler;

import java.time.Instant;
import java.util.UUID;

public class LogEntity extends AggregateRoot<LogEntityID> {

    private final Instant createdAt;
    private final String description;
    private final String titleLog;
    private final String authorApplication;
    private final UUID userId;
    private final Integer propertyId;
    private final LogEntityTypeStatus logEntityTypeStatus;
    private final LogEntityTypeAction logEntityTypeAction;


    private LogEntity(
            final LogEntityID logEntityID,
            final Instant createdAt,
            final String description,
            final String titleLog,
            final String authorApplication,
            final UUID userId,
            final Integer propertyId,
            final LogEntityTypeStatus logEntityTypeStatus,
            final LogEntityTypeAction logEntityTypeAction
    ) {
        super(logEntityID);
        this.createdAt = createdAt;
        this.description = description;
        this.titleLog = titleLog;
        this.authorApplication = authorApplication;
        this.userId = userId;
        this.propertyId = propertyId;
        this.logEntityTypeStatus = logEntityTypeStatus;
        this.logEntityTypeAction = logEntityTypeAction;
    }

    public static LogEntity newEntity(
            final String description,
            final String titleLog,
            final String authorApplication,
            final UUID userId,
            final Integer propertyId,
            final Integer logEntityTypeStatus,
            final Integer logEntityTypeAction
    ) {
        final var logId = LogEntityID.unique();
        final Instant now = Instant.now();
        final var logTypeStatus = new LogEntityTypeStatus(logEntityTypeStatus);
        final var logTypeAction = new LogEntityTypeAction(logEntityTypeAction);
        return new LogEntity(logId, now, description, titleLog, authorApplication, userId, propertyId,logTypeStatus, logTypeAction);
    }

    public static LogEntity with(LogEntityID logEntityID,
                                 final String description,
                                 final String titleLog,
                                 final String authorApplication,
                                 final UUID userId,
                                 final Integer propertyId,
                                 final Integer logEntityTypeStatusId,
                                 final Integer logEntityTypeActionId,
                                 final Instant createdAt
    ) {
        final LogEntityTypeStatus logEntityTypeStatus = new LogEntityTypeStatus(logEntityTypeStatusId);

        final LogEntityTypeAction logEntityTypeAction = new LogEntityTypeAction(logEntityTypeActionId);

        return new LogEntity(
                logEntityID,
                createdAt,
                description,
                titleLog,
                authorApplication,
                userId,
                propertyId,
                logEntityTypeStatus,
                logEntityTypeAction
                );
    }


    @Override
    public void validate(ValidationHandler handler) {
        getLogEntityTypeAction().validate(handler);
        getLogEntityTypeStatus().validate(handler);
        new LogEntityValidator(handler, this).validate();
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public String getTitleLog() {
        return titleLog;
    }

    public String getAuthorApplication() {
        return authorApplication;
    }

    public UUID getUserId() {
        return userId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public LogEntityTypeStatus getLogEntityTypeStatus() {
        return logEntityTypeStatus;
    }

    public LogEntityTypeAction getLogEntityTypeAction() {
        return logEntityTypeAction;
    }
}


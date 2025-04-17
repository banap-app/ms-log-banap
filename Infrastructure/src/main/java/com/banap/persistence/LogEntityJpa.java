package com.banap.persistence;


import com.banap.log_entity.LogEntity;
import com.banap.log_entity.LogEntityID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity(name = "LogEntity")
@Table(name = "log_entity")
public class LogEntityJpa {

    @Id
    @Column(name = "id", nullable = false)
    private String id;


    @Column(name = "description")
    public String description;

    @Column(name = "title_log")
    public String titleLog;

    @Column(name = "author_application")
    public String authorApplication;

    @Column(name = "user_id")
    public UUID userId;

    @Column(name = "property_id")
    public Integer propertyId;

    @Column(name = "log_entity_type_status")
    public Integer logEntityTypeStatus;

    @Column(name = "log_entity_type_action")
    public Integer logEntityTypeAction;

    @Column(name = "created_at", columnDefinition = "DATETIME(6)")
    public Instant createdAt;

    private LogEntityJpa(final String id,
                         final String description,
                         final String titleLog,
                         final String authorApplication,
                         final UUID userId,
                         final Integer propertyId,
                         final Integer logEntityTypeStatus,
                         final Integer logEntityTypeAction,
                         final Instant createdAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.titleLog = titleLog;
        this.authorApplication = authorApplication;
        this.description = description;
        this.userId = userId;
        this.propertyId = propertyId;
        this.logEntityTypeAction = logEntityTypeAction;
        this.logEntityTypeStatus = logEntityTypeStatus;

    }

    public static LogEntityJpa from(final LogEntity logEntity) {
        return new LogEntityJpa(
                logEntity.getId().getValue(),
                logEntity.getDescription(),
                logEntity.getTitleLog(),
                logEntity.getAuthorApplication(),
                logEntity.getUserId(),
                logEntity.getPropertyId(),
                logEntity.getLogEntityTypeStatus().getTypeId(),
                logEntity.getLogEntityTypeAction().getTypeId(),
                logEntity.getCreatedAt()
        );
    }
    public LogEntityJpa () {}

    public LogEntity toDomain() {
        return LogEntity.with(
                LogEntityID.from(getId()),
                getDescription(),
                getTitleLog(),
                getAuthorApplication(),
                getUserId(),
                getPropertyId(),
                getLogEntityTypeStatus(),
                getLogEntityTypeAction(),
                getCreatedAt()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitleLog() {
        return titleLog;
    }

    public void setTitleLog(String titleLog) {
        this.titleLog = titleLog;
    }

    public String getAuthorApplication() {
        return authorApplication;
    }

    public void setAuthorApplication(String authorApplication) {
        this.authorApplication = authorApplication;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getLogEntityTypeStatus() {
        return logEntityTypeStatus;
    }

    public void setLogEntityTypeStatus(Integer logEntityTypeStatus) {
        this.logEntityTypeStatus = logEntityTypeStatus;
    }

    public Integer getLogEntityTypeAction() {
        return logEntityTypeAction;
    }

    public void setLogEntityTypeAction(Integer logEntityTypeAction) {
        this.logEntityTypeAction = logEntityTypeAction;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

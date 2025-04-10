package com.banap.log_entity;


import com.banap.validation.handlers.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class LogEntityTest {

    @Test
    public void createLogEntityWithValidParams() {
        final var anTitleLog = "Title de teste";
        final var description = "Descrição para o log";
        final var authorApplication = "BANAP_TESTER_APP";
        final var userId = UUID.randomUUID();
        final var propertyId = 1;
        final var logEntityStatus = new LogEntityTypeStatus(1).getTypeId();
        final var logEntityAction =new LogEntityTypeAction(1).getTypeId();

        final LogEntity logEntity = LogEntity.newEntity(description,anTitleLog,authorApplication,userId,propertyId,logEntityStatus, logEntityAction);
        final Notification notification = Notification.create();
        logEntity.validate(notification);

        Assertions.assertNotNull(logEntity.getId());
        Assertions.assertEquals(anTitleLog, logEntity.getTitleLog());
        Assertions.assertEquals("notification", logEntity.getLogEntityTypeStatus().getTypeName());
        Assertions.assertEquals("");
    }

}

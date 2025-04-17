package com.banap.logentity.create;

import java.util.UUID;

public record LogEntityCommand(String description, String titleLog, String authorApplication, UUID userId, Integer propertyId, Integer logEntityTypeStatus, Integer logEntityTypeAction) {

    public static LogEntityCommand from(final String description, final String titleLog, final String authorApplication, final UUID userId, Integer propertyId, Integer logEntityTypeStatus, Integer logEntityTypeAction)  {
        return new LogEntityCommand(description,titleLog,authorApplication, userId, propertyId, logEntityTypeStatus, logEntityTypeAction);
    }
}


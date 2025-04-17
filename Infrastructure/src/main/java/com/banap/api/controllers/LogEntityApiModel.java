package com.banap.api.controllers;



public record LogEntityApiModel(String description,
                                String titleLog,
                                String authorApplication,
                                String userId,
                                Integer propertyId,
                                Integer logEntityTypeStatus,
                                Integer logEntityTypeAction
) {

}

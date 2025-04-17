package com.banap.api.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("LogEntityApi")
public record LogEntityApi(
        @JsonProperty("description") String description,
        @JsonProperty("titleLog") String titleLog,
        @JsonProperty("authorApplication") String authorApplication,
        @JsonProperty("userId") String userId,
        @JsonProperty("propertyId") Integer propertyId,
        @JsonProperty("logEntityTypeStatus") Integer logEntityTypeStatus,
        @JsonProperty("logEntityTypeAction") Integer logEntityTypeAction
) {

}

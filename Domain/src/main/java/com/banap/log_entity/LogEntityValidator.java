package com.banap.log_entity;

import com.banap.exceptions.DomainException;
import com.banap.validation.Error;
import com.banap.validation.ValidationHandler;
import com.banap.validation.Validator;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

public class LogEntityValidator extends Validator {

    private LogEntity logEntity;

    private final Integer MIN_AUTHOR_APPLICATION_LENGTH = 5;
    private final Integer MAX_AUTHOR_APPLICATION_LENGTH = 100;

    private final Integer MIN_DESCRIPTION_LENGTH = 5;
    private final Integer MAX_DESCRIPTION_LENGTH = 320;

    private final Integer MIN_TITLE_LOG_LENGTH = 3;
    private final Integer MAX_TITLE_LOG_LENGTH = 100;


    public LogEntityValidator(ValidationHandler aHandler, LogEntity logEntity) {
        super(aHandler);
        this.logEntity = Objects.requireNonNull(logEntity);
    }

    @Override
    public void validate() {
        checkAuthorApplication();
        checkDescription();
        checkTitleLog();
        checkUUIDUser();
        checkProperty();
    }

    private void checkAuthorApplication() {
        final var authorApplication = logEntity.getAuthorApplication();

        if(authorApplication == null) {
            this.validationHandler().append(new Error("Author Application not be null"));
            return;
        }
        if(authorApplication.isEmpty()) {
            this.validationHandler().append(new Error("Author Application not be null"));
            return;
        }

        if(authorApplication.isBlank()) {
            this.validationHandler().append(new Error("Author Application not be null"));
            return;
        }

        if(authorApplication.length() > MAX_AUTHOR_APPLICATION_LENGTH) {
            this.validationHandler().append(new Error("Max length for author application is 100 chars"));
            return;
        }

        if(authorApplication.length() < MIN_AUTHOR_APPLICATION_LENGTH) {
            this.validationHandler().append(new Error("Min length for author application is 5 chars or more"));
        }
    }

    private void checkDescription() {
        final var description = logEntity.getDescription();

        if(description == null) {
            this.validationHandler().append(new Error("Description not be null"));
            return;
        }

        if(description.isEmpty()){
            this.validationHandler().append(new Error("Description not be null"));
            return;
        }
        if(description.isBlank()){
            this.validationHandler().append(new Error("Description not be null"));
            return;
        }


        if(description.length() > MAX_DESCRIPTION_LENGTH){
            this.validationHandler().append(new Error("Description not more %".formatted(MAX_DESCRIPTION_LENGTH)));
            return;
        }

        if(description.length() < MIN_DESCRIPTION_LENGTH){
            this.validationHandler().append(new Error("Description more %".formatted(MIN_DESCRIPTION_LENGTH)));
        }

    }


    private void checkTitleLog() {
        final var titleLog = logEntity.getTitleLog();

        if(titleLog == null){
            this.validationHandler().append(new Error("Title log not be null"));
            return;
        }

        if(titleLog.isEmpty()){
            this.validationHandler().append(new Error("Title log not be null"));
            return;
        }

        if(titleLog.isBlank()) {
            this.validationHandler().append(new Error("Title log not be null"));
            return;
        }

        if (titleLog.length() > MAX_TITLE_LOG_LENGTH) {
            this.validationHandler().append(new Error("Title log not more %".formatted(MAX_TITLE_LOG_LENGTH)));
            return;
        }

        if (titleLog.length() < MIN_TITLE_LOG_LENGTH) {
            this.validationHandler().append(new Error("Title log more %".formatted(MIN_TITLE_LOG_LENGTH)));
        }
    }

    private void checkUUIDUser(){
        final var userId = logEntity.getUserId() != null ? logEntity.getUserId().toString() : null;
        if(userId == null){
            this.validationHandler().append(new Error("UserID not be null"));
            return;
        }

        if(userId.isEmpty()){
            this.validationHandler().append(new Error("UserID not be null"));
            return;
        }

        if(userId.isBlank()){
            this.validationHandler().append(new Error("UserID not be null"));
            return;
        }

        Pattern UUID_REGEX =
                Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

        if(!UUID_REGEX.matcher(userId).matches()){
          this.validationHandler().append(new Error("UserID not valid UUID"));
          return;
        }

        boolean isValidUUID;

        try {
            UUID.fromString(userId);
            isValidUUID = true;
        } catch(DomainException ex) {
            isValidUUID = false;
        }

        if(!isValidUUID) {
            this.validationHandler().append(new Error("UserID not valid UUID"));
        }

    }

    private void checkProperty() {
        final var propertyId = logEntity.getPropertyId() != null ? logEntity.getPropertyId() : null;

        if(propertyId == null) {
            this.validationHandler().append(new Error("PropertyId not must be null"));
            return;
        }
        if(propertyId == 0) {
            this.validationHandler().append(new Error("PropertyId not valid"));
        }
    }

}

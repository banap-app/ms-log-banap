package com.banap.api.controllers;


import com.banap.Result;
import com.banap.api.swagger.LogEntityControllerApi;
import com.banap.logentity.create.DefaultLogEntityCreateUseCase;
import com.banap.logentity.create.LogEntityCommand;
import com.banap.logentity.create.LogEntityOutput;
import com.banap.validation.handlers.Notification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/log")
public class LogEntityController implements LogEntityControllerApi {

    private final DefaultLogEntityCreateUseCase logEntityCreateUseCase;

    public LogEntityController(final DefaultLogEntityCreateUseCase logEntityCreateUseCase) {
        this.logEntityCreateUseCase = logEntityCreateUseCase;
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<?> create(@RequestBody LogEntityApi logEntityApi) {
        final var description = logEntityApi.description();
        final var titleLog = logEntityApi.titleLog();
        final var authorApplication = logEntityApi.authorApplication();
        final var userId = logEntityApi.userId() == null || logEntityApi.userId().isBlank() ? null : UUID.fromString(logEntityApi.userId());
        final var propertyId = logEntityApi.propertyId();
        final var logEntityTypeAction = logEntityApi.logEntityTypeAction();
        final var logEntityTypeStatus = logEntityApi.logEntityTypeStatus();

        final var logEntityCommand = LogEntityCommand.from(description, titleLog, authorApplication, userId, propertyId, logEntityTypeStatus, logEntityTypeAction);

        try {
            final Result<LogEntityOutput, Notification> output = this.logEntityCreateUseCase.execute(logEntityCommand);

            if (output.getFirstValue() != null) {
                return ResponseEntity.ok(output.getFirstValue());
            } else {
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("code", HttpStatus.UNPROCESSABLE_ENTITY.value());
                responseBody.put("errors", output.getSecondValue());
                responseBody.put("status", HttpStatus.UNPROCESSABLE_ENTITY.name());
                return ResponseEntity.unprocessableEntity().body(output.getSecondValue());
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.badRequest().body("Internal server error");
        }
    }


}

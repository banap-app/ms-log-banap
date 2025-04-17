package com.banap.api.controllers;


import com.banap.logentity.create.DefaultLogEntityCreateUseCase;
import com.banap.logentity.create.LogEntityCommand;
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
public class LogEntityController {

    private final DefaultLogEntityCreateUseCase logEntityCreateUseCase;

    public LogEntityController(final DefaultLogEntityCreateUseCase logEntityCreateUseCase) {
        this.logEntityCreateUseCase = logEntityCreateUseCase;
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody LogEntityApiModel logEntityApiModel) {
        final var description = logEntityApiModel.description();
        final var titleLog = logEntityApiModel.titleLog();
        final var authorApplication = logEntityApiModel.authorApplication();
        final var userId = logEntityApiModel.userId() == null || logEntityApiModel.userId().isBlank() ? null : UUID.fromString(logEntityApiModel.userId());
        final var propertyId = logEntityApiModel.propertyId();
        final var logEntityTypeAction = logEntityApiModel.logEntityTypeAction();
        final var logEntityTypeStatus = logEntityApiModel.logEntityTypeStatus();

        final var logEntityCommand = LogEntityCommand.from(description, titleLog,authorApplication, userId, propertyId, logEntityTypeStatus, logEntityTypeAction);


        try {
            final var output = this.logEntityCreateUseCase.execute(logEntityCommand);

            if(output.getSecondValue() == null) {
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

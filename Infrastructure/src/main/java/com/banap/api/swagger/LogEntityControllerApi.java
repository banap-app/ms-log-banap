package com.banap.api.swagger;


import com.banap.api.controllers.LogEntityApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Log Entity Controller")
public interface LogEntityControllerApi {
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = ""
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "")
    })
    ResponseEntity<?> create(@RequestBody LogEntityApi logEntityApi);
}

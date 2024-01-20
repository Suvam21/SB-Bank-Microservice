package com.sb_bank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "Error Response",
        description = "Schema to hold the error response information"
)
@Data @AllArgsConstructor
public class ErrorResponseDTO {

    @Schema(
            description = "Api path which caused the error"
    )
    private String apiPath;

    @Schema(
            description = "Error code in the response"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message in the response"
    )
    private String errorMessage;

    @Schema(
            description = "Time at which the error occurred"
    )
    private LocalDateTime errorTime;
}

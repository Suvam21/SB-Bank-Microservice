package com.sb_bank.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponse",
        description = "Schema to hold the error response information"
)
@Data @AllArgsConstructor
public class ErrorResponseDTO {

    @Schema(
            description = "API path which caused the error"
    )
    private String apiPath;

    @Schema(
            description = "Error code in the response"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message in the response"
    )
    private String errorMsg;

    @Schema(
            description = "Time when the error occurred",
            example = "2021-08-01T12:00:00"
    )
    private LocalDateTime errorTime;
}

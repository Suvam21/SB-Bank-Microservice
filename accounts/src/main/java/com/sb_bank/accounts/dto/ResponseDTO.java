package com.sb_bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Response", description = "Schema to hold the successful response information")
public class ResponseDTO {

        @Schema(description = "Status code in the response")
        private String statusCode;

        @Schema(description = "Status message in the response")
        private String statusMsg;

}

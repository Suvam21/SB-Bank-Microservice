package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold the customer's account information like account number etc."
)
public class AccountsDTO {

    @Schema(
            description = "Account number of a customer",
            example = "3452671235"
    )
    @NotEmpty(message = "Account Number CANNOT be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number SHOULD have 10 digit")
    private Long accountNumber;

    @Schema(
            description = "Account type of a user",
            example = "SAVINGS / CURRENT"
    )
    @NotEmpty(message = "Account type CANNOT be null or empty")
    private String accountType;

    @Schema(
            description = "Branch Address of a Customer"
    )
    @NotEmpty(message = "Branch address CANNOT be null or empty")
    private String branchAddress;
}


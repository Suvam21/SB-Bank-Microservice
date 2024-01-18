package com.bank.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDTO {

    @NotEmpty(message = "Account Number CANNOT be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number SHOULD have 10 digit")
    private Long accountNumber;

    @NotEmpty(message = "Account type CANNOT be null or empty")
    private String accountType;

    @NotEmpty(message = "Branch address CANNOT be null or empty")
    private String branchAddress;
}


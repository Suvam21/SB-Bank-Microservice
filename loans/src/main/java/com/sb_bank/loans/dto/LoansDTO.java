package com.sb_bank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Loans",
        description = "Schema to hold the loans information"
)
@Data
public class LoansDTO {

    @NotEmpty(message = "Mobile number cannot be Null")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter an valid 10 digit mobile number")
    @Schema(
            description = "Mobile number of the customer",
            example = "9976439012"
    )
    private String mobile;

    @NotEmpty(message = "Loan Number cannot be Null")
    @Pattern(regexp="(^$|[0-9]{12})",message = "LoanNumber must be 12 digits")
    @Schema(
            description = "Loan number of the customer", example = "587654321098"
    )
    private String loanNumber;

    @NotEmpty(message = "LoanType can not be a null or empty")
    @Schema(
            description = "Loan type of the customer", example = "Home Loan"
    )
    private String loanType;

    @Positive(message = "Total loan amount should be greater than zero")
    @Schema(
            description = "Loan amount of the customer", example = "1000000"
    )
    private int totalLoan;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    @Schema(
            description = "Loan duration of the customer", example = "10"
    )
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    @Schema(
            description = "Outstanding amount of the customer", example = "100000"
    )
    private int outstandingAmount;
}

package com.sb_bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold the customer and account information"
)
public class  CustomerDTO {

    @Schema(
            description = "Name of the customer", example = "Rahul Khurrana"
    )
    @NotEmpty(message = "Name cannot be Null")
    @Size(min = 5, max = 50, message = "Length of name should be between 5 and 50 characters")
    private String name;

    @Schema(
            description = "Email ID of the customer",
            example = "rahul@gmail.com"
    )
    @NotEmpty(message = "Email cannot be Null")
    @Email(message = "Enter a valid Email address")
    private String email;

    @Schema(
            description = "Mobile number of the customer",
            example = "9933456532"
    )
    @NotEmpty(message = "mobile cannot be Null")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter an valid 10 digit mobile number")
    private String mobile;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountsDTO account;
}

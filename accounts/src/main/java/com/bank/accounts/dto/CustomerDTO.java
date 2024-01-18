package com.bank.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class  CustomerDTO {

    @NotEmpty(message = "Name cannot be Null")
    @Size(min = 5, max = 50, message = "Length of name should be between 5 and 50 characters")
    private String name;

    @NotEmpty(message = "Email cannot be Null")
    @Email(message = "Enter a valid Email address")
    private String email;

    @NotEmpty(message = "mobile cannot be Null")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter an valid 10 digit mobile number")
    private String mobile;

    private AccountsDTO account;
}

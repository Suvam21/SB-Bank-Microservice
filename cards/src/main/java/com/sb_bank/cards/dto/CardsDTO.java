package com.sb_bank.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Cards",
        description = "Schema to hold the card information"
)
@Data
public class CardsDTO {

    @NotEmpty(message = "Please provide a mobile number")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter an valid 10 digit mobile number")
    @Schema(
            description = "Mobile number of the customer",
            example = "9933456532"
    )
    private String mobile;

    @NotEmpty(message = "Please provide a card number")
    @Pattern(regexp = "(^$|[0-9]{16})", message = "Enter an valid 16 digit card number")
    @Schema(
            description = "Card number of the customer",
            example = "1234-5678-9012-3456"
    )
    private String cardNumber;

    @NotEmpty(message = "Please provide a card type")
    @Schema(
            description = "Card type of the customer",
            example = "VISA"
    )
    private String cardType;

    @Positive(message = "Please provide a correct total limit. Amount should be greater than 0")
    @Schema(
            description = "Total limit of the card",
            example = "100000"
    )
    private int totalLimit;

    @PositiveOrZero(message = "Please provide a correct amount used. Amount should be greater than or equal to 0")
    @Schema(
            description = "Amount used from the card",
            example = "0"
    )
    private int amountUsed;

    @PositiveOrZero(message = "Please provide a correct available amount. Amount should be greater than or equal to 0")
    @Schema(
            description = "Available amount in the card",
            example = "100000"
    )
    private int availableAmount;
}

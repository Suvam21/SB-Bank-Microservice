package com.sb_bank.cards.controller;

import com.sb_bank.cards.constants.CardsConstants;
import com.sb_bank.cards.dto.CardsDTO;
import com.sb_bank.cards.dto.ErrorResponseDTO;
import com.sb_bank.cards.dto.ResponseDTO;
import com.sb_bank.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD API's for Accounts MS",
        description = "Performs CRUD Operation on the account details"
)
@RestController
@RequestMapping(value = "/api/cards", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class CardsController {

    private ICardsService cardsService;

    @Operation(
            summary = "REST API- Creates New Card",
            description = "This api will create a new card inside our SB_Bank with mobile as the input"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "HTTP status CREATED"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP status INTERNAL_SERVER_ERROR",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseDTO.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ResponseDTO> createCard(@Valid @RequestParam
                                                      @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                      String mobile) {
        cardsService.createCard(mobile);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "REST API- Fetches Card Details",
            description = "This api will fetch the card details from our SB_Bank with mobile as the input"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP status OK"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP status INTERNAL_SERVER_ERROR",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseDTO.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<CardsDTO> fetchCardDetails(@Valid @RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                         String mobile) {

        CardsDTO cardsDTO = cardsService.fetchCardDetails(mobile);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsDTO);
    }

    @Operation(
            summary = "REST API- Updates Card Details",
            description = "This api will update the card details inside our SB_Bank with CardsDTO as the input"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP status OK"
                    ),
                    @ApiResponse(
                            responseCode = "304",
                            description = "HTTP status NOT_MODIFIED",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP status INTERNAL_SERVER_ERROR",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseDTO.class)
                            )
                    )
            }
    )
    @PutMapping
    public ResponseEntity<ResponseDTO> updateCard(@Valid @RequestBody CardsDTO cardsDTO){
        boolean isUpdated = cardsService.updateCard(cardsDTO);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED)
                    .body(new ResponseDTO(CardsConstants.STATUS_304, CardsConstants.MESSAGE_304));
        }
    }

    @Operation(
            summary = "REST API- Deletes Card Details",
            description = "This api will delete the card details inside our SB_Bank with mobile as the input"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP status OK"
                    ),
                    @ApiResponse(
                            responseCode = "417",
                            description = "HTTP status EXPECTATION_FAILED",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP status INTERNAL_SERVER_ERROR",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseDTO.class)
                            )
                    )
            }
    )
    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteCard(@Valid @RequestParam
                                                      @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                      String mobile){
        boolean isDeleted = cardsService.deleteCard(mobile);

        if (isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
        }
    }
}

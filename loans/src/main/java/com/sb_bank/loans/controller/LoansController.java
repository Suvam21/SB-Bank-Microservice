package com.sb_bank.loans.controller;

import com.sb_bank.loans.constants.LoansConstants;
import com.sb_bank.loans.dto.ErrorResponseDTO;
import com.sb_bank.loans.dto.LoansDTO;
import com.sb_bank.loans.dto.ResponseDTO;
import com.sb_bank.loans.service.ILoansService;
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

import java.util.Locale;

@Tag(
        name = "CRUD API's for Loans MS",
        description = "Performs CRUD Operation on the Loan details"
)
@RestController
@RequestMapping(value = "/api/loans", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class LoansController {

    private ILoansService loansService;

    @Operation(
            summary = "Create Loan REST API",
            description = "REST API to create new loan inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @PostMapping
    public ResponseEntity<ResponseDTO> createLoan(@Valid @RequestParam
                                                  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                  String mobile) {
        loansService.createLoan(mobile);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }


    @Operation(
            summary = "Fetch Loan Details REST API",
            description = "REST API to fetch loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status NOT_FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @GetMapping
    public ResponseEntity<LoansDTO> fetchLoanDetails(@Valid @RequestParam
                                                     @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                     String mobile) {
        LoansDTO loansDto = loansService.fetchLoan(mobile);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }


    @Operation(
            summary = "Update Loan Details REST API",
            description = "REST API to update loan details based on a loan number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "304",
                    description = "Modification Failed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @PutMapping()
    public ResponseEntity<ResponseDTO> updateLoanDetails(@Valid @RequestBody LoansDTO loansDto) {
        boolean isUpdated = loansService.updateLoan(loansDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED)
                    .body(new ResponseDTO(LoansConstants.STATUS_304, LoansConstants.MESSAGE_304));
        }
    }

    @Operation(
            summary = "Delete Loan Details REST API",
            description = "REST API to delete Loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }


    )
    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteLoanDetails(@Valid @RequestParam
                                                         @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                         String mobile) {
        boolean isDeleted = loansService.deleteLoan(mobile);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }
    }

}

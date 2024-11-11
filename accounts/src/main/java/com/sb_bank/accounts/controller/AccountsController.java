package com.sb_bank.accounts.controller;

import com.sb_bank.accounts.constants.AccountConstants;
import com.sb_bank.accounts.dto.CustomerDTO;
import com.sb_bank.accounts.dto.ErrorResponseDTO;
import com.sb_bank.accounts.dto.ResponseDTO;
import com.sb_bank.accounts.service.IAccountsService;
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

@Tag(name = "CRUD API's for Accounts MS", description = "Performs CRUD Operation on the account details")
@RestController
@RequestMapping(path = "/api/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsController {

        private IAccountsService accountsService;

        @Operation(summary = "REST API- Creates New Account", description = "This api will create a new account inside our SB_Bank with CustomerDTO as the input")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "HTTP status CREATED"),
                        @ApiResponse(responseCode = "500", description = "HTTP status INTERNAL_SERVER_ERROR", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
        })
        @PostMapping()
        public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
                accountsService.createAccount(customerDTO);
                return ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(new ResponseDTO(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
        }

        @Operation(summary = "REST API- Fetches Account Details", description = "This api will fetch the account details of the customer by taking the mobile number as the Request parameter.")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "HTTP status OK"),
                        @ApiResponse(responseCode = "500", description = "HTTP status INTERNAL_SERVER_ERROR", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
        })
        @GetMapping()
        public ResponseEntity<CustomerDTO> fetchAccountDetails(
                        @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter an valid 10 digit mobile number") String mobile) {

                CustomerDTO customerDTO = accountsService.fetchAccountDetails(mobile);

                return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(customerDTO);
        }

        @Operation(summary = "REST API- Updates existing account", description = "This api will update any field except the account number of a customer by taking in the CustomerDTO as request body")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "HTTP status OK"),
                        @ApiResponse(responseCode = "304", description = "HTTP status NOT_MODIFIED", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),
                        @ApiResponse(responseCode = "500", description = "HTTP status INTERNAL_SERVER_ERROR", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
        })
        @PutMapping()
        public ResponseEntity<ResponseDTO> updateAccountDetails(@Valid @RequestBody CustomerDTO customerDTO) {
                boolean isUpdated = accountsService.updateAccount(customerDTO);
                if (isUpdated) {
                        return ResponseEntity
                                        .status(HttpStatus.OK)
                                        .body(new ResponseDTO(AccountConstants.STATUS_200,
                                                        AccountConstants.MESSAGE_200));
                } else {
                        return ResponseEntity
                                        .status(HttpStatus.NOT_MODIFIED)
                                        .body(new ResponseDTO(AccountConstants.STATUS_304,
                                                        AccountConstants.MESSAGE_417_UPDATE));
                }
        }

        @Operation(summary = "REST API- Deletes a user's account", description = "This api will delete a customer information by taking in the phone number as request parameter")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "HTTP status OK"),
                        @ApiResponse(responseCode = "500", description = "HTTP status INTERNAL_SERVER_ERROR", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),
                        @ApiResponse(responseCode = "417", description = "HTTP status EXPECTATION_FAILED", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
        })
        @DeleteMapping()
        public ResponseEntity<ResponseDTO> deleteAccount(
                        @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter an valid 10 digit mobile number") String mobile) {

                boolean isDeleted = accountsService.deleteAccount(mobile);
                if (isDeleted) {
                        return ResponseEntity
                                        .status(HttpStatus.OK)
                                        .body(new ResponseDTO(AccountConstants.STATUS_200,
                                                        AccountConstants.MESSAGE_200));
                } else {
                        return ResponseEntity
                                        .status(HttpStatus.EXPECTATION_FAILED)
                                        .body(new ResponseDTO(AccountConstants.STATUS_417,
                                                        AccountConstants.MESSAGE_417_DELETE));
                }
        }

}

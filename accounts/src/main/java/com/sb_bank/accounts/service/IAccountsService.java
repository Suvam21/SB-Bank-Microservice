package com.sb_bank.accounts.service;

import com.sb_bank.accounts.dto.CustomerDTO;

public interface IAccountsService {

    /**
     * Create account for the customer
     * @param customerDTO - CustomerDTO object
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     * Fetch account details for the customer
     * @param mobile - input mobile number of the customer
     * @return Account Details associated with the mobile number
     */
    CustomerDTO fetchAccountDetails(String mobile);

    /**
     * Update account details for the customer
     * @param customerDTO - CustomerDTO object
     * @return boolean
     */
    boolean updateAccount(CustomerDTO customerDTO);


    /**
     * Delete account details for the customer
     * @param mobile - Mobile Number
     * @return boolean
     */
    boolean deleteAccount(String mobile);
}

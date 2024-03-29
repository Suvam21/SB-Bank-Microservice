package com.sb_bank.accounts.service.impl;

import com.sb_bank.accounts.constants.AccountConstants;
import com.sb_bank.accounts.dto.AccountsDTO;
import com.sb_bank.accounts.dto.CustomerDTO;
import com.sb_bank.accounts.entity.Accounts;
import com.sb_bank.accounts.entity.Customer;
import com.sb_bank.accounts.exception.CustomerAlreadyExistsException;
import com.sb_bank.accounts.exception.ResourceNotFoundException;
import com.sb_bank.accounts.mapper.AccountsMapper;
import com.sb_bank.accounts.mapper.CustomerMapper;
import com.sb_bank.accounts.repository.AccountsRepository;
import com.sb_bank.accounts.repository.CustomerRepository;
import com.sb_bank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;


    /**
     * Create account for the customer
     *
     * @param customerDTO - CustomerDTO object
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobile(customer.getMobile());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with mobile number: " + customer.getMobile());
        }

        Optional<Customer> byEmail = customerRepository.findByEmail(customerDTO.getEmail());
        if (byEmail.isPresent()){
            throw new CustomerAlreadyExistsException("Customer Already exists with email: " + customer.getEmail());
        }

        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }


    /**
     * Create account for the customer
     * @param customer - CustomerDTO object
     */
    private Accounts createNewAccount(Customer customer){
        Long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);

        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        return newAccount;
    }

    /**
     * Fetch account details for the customer
     *
     * @param mobile - input mobile number of the customer
     * @return Account Details associated with the mobile number
     */
    @Override
    public CustomerDTO fetchAccountDetails(String mobile) {
        Customer customer = customerRepository.findByMobile(mobile)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer", "mobile number", mobile)
                );

        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account", "mobile number", mobile)
                );

        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDto(customer, new CustomerDTO());
        customerDTO.setAccount(AccountsMapper.mapToAccountsDto(account, new AccountsDTO()));

        return customerDTO;


    }

    /**
     * Update account details for the customer
     *
     * @param customerDTO - CustomerDTO object
     * @return boolean
     */
    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountsDTO accountsDTO = customerDTO.getAccount();
        if(accountsDTO != null) {
            Accounts accounts = accountsRepository.findById(accountsDTO.getAccountNumber())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Account", "account number", accountsDTO.getAccountNumber().toString())
                    );
            AccountsMapper.mapToAccounts(accountsDTO, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "customer id", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDTO, customer);
            customerRepository.save(customer);

            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * Delete account details for the customer
     *
     * @param mobile - mobile Number
     * @return boolean
     */
    @Override
    public boolean deleteAccount(String mobile) {

        Customer customer = customerRepository.findByMobile(mobile).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Mobile number", mobile)
        );
        customerRepository.deleteById(customer.getCustomerId());
        accountsRepository.deleteByCustomerId(customer.getCustomerId());

        return true;
    }

}

package com.bank.accounts.repository;

import com.bank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    public Optional<Customer> findByMobile(String mobile);
}

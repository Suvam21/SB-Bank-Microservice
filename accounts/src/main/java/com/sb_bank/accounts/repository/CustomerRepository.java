package com.sb_bank.accounts.repository;

import com.sb_bank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    public Optional<Customer> findByMobile(String mobile);

    Optional<Customer> findByEmail(String email);
}

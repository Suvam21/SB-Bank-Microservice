package com.sb_bank.loans.repository;

import com.sb_bank.loans.entity.Loans;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface LoansRepository extends JpaRepository<Loans, Long>{

    Optional<Loans> findByMobile(String mobile);

    @Transactional
    @Modifying
    void deleteByMobile(String mobile);

    Optional<Loans> findByLoanNumber(String loanNumber);
}

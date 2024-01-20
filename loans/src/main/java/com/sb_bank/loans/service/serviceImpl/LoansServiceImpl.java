package com.sb_bank.loans.service.serviceImpl;

import com.sb_bank.loans.constants.LoansConstants;
import com.sb_bank.loans.dto.LoansDTO;
import com.sb_bank.loans.entity.Loans;
import com.sb_bank.loans.exception.LoanAlreadyExistsException;
import com.sb_bank.loans.exception.ResourceNotFoundException;
import com.sb_bank.loans.mapper.LoansMapper;
import com.sb_bank.loans.repository.LoansRepository;
import com.sb_bank.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    LoansRepository loansRepository;

    /**
     * @param mobile - Mobile Number of the Customer
     */
    @Override
    public void createLoan(String mobile) {
        Optional<Loans> optionalLoans= loansRepository.findByMobile(mobile);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobile);
        }
        loansRepository.save(createNewLoan(mobile));
    }

    /**
     * @param mobile - Mobile Number of the Customer
     * @return the new loan details
     */
    private Loans createNewLoan(String mobile) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobile(mobile);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    /**
     *
     * @param mobile - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoansDTO fetchLoan(String mobile) {
        Loans loans = loansRepository.findByMobile(mobile).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobile)
        );
        return LoansMapper.mapToLoansDto(loans, new LoansDTO());
    }

    /**
     *
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of loan details is successful or not
     */
    @Override
    public boolean updateLoan(LoansDTO loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
        LoansMapper.mapToLoans(loansDto, loans);
        loansRepository.save(loans);
        return  true;
    }

    /**
     * @param mobile - Input MobileNumber
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobile) {
        Loans loans = loansRepository.findByMobile(mobile).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobile)
        );
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }
}

package com.enigma.vica.kopnigma.repository;

import com.enigma.vica.kopnigma.entity.TxLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<TxLoan, String> {
    TxLoan findByMember_Id(String id);
}

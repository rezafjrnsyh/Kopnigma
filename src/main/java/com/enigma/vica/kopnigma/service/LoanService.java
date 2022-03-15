package com.enigma.vica.kopnigma.service;

import com.enigma.vica.kopnigma.controller.httpreq.LoaningReq;
import com.enigma.vica.kopnigma.entity.TxLoan;

public interface LoanService {
    void create(LoaningReq loaningReq, String memberId);
    TxLoan findMemberIdOnLoan(String memberId);
}

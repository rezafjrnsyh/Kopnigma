package com.enigma.vica.kopnigma.service;

import com.enigma.vica.kopnigma.controller.httpreq.PayReq;
import com.enigma.vica.kopnigma.entity.Member;
import com.enigma.vica.kopnigma.entity.TxInstallment;
import com.enigma.vica.kopnigma.entity.TxLoan;

import java.util.List;

public interface InstallmentService {
    void calculateInstallment(TxLoan loan, Member member);
    List<TxInstallment> findInstallmentsById(String id);
    void payInstallment(PayReq payReq);
}

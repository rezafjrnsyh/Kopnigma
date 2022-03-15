package com.enigma.vica.kopnigma.service.impl;

import com.enigma.vica.kopnigma.constant.Constant;
import com.enigma.vica.kopnigma.controller.httpreq.PayReq;
import com.enigma.vica.kopnigma.entity.Member;
import com.enigma.vica.kopnigma.entity.TxInstallment;
import com.enigma.vica.kopnigma.entity.TxLoan;
import com.enigma.vica.kopnigma.repository.InstallmentRepository;
import com.enigma.vica.kopnigma.repository.LoanRepository;
import com.enigma.vica.kopnigma.service.InstallmentService;
import com.enigma.vica.kopnigma.service.LoanService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstallmentServiceImpl implements InstallmentService {

    private final InstallmentRepository installmentRepository;
    private final LoanRepository loanRepository;

    public InstallmentServiceImpl(InstallmentRepository installmentRepository, LoanRepository loanRepository) {
        this.installmentRepository = installmentRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public void calculateInstallment(TxLoan loan, Member member) {
        Long saldo = loan.getAmountSubmitted();
        long installmentUnderlying = getInstallmentUnderlying(loan);
        List<TxInstallment> installments = new ArrayList<>();
        for (Integer i = 1; i <= loan.getInstallment(); i++) {
            Long result = Double.valueOf(saldo * (Constant.SUKU_BUNGA/12)).longValue();
            TxInstallment installment = mapper(i, result, member, installmentUnderlying);
            installments.add(installment);
            saldo -= installmentUnderlying;
        }
        this.storeAll(installments);
    }

    private void storeAll(List<TxInstallment> installments) {
        installmentRepository.saveAll(installments);

    }

    private TxInstallment mapper(Integer index, Long result, Member member, long installmentUnderlying) {
        TxInstallment installment = new TxInstallment();
        installment.setInstallmentOrder(index);
        installment.setInstallmentInterest(result);
        long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        installment.setCreatedAt(timestamp);
        installment.setInstallmentUnderlying(installmentUnderlying);
        installment.setMember(member);
        installment.setStatus(Constant.UNPAID);
        return installment;
    }

    private Long getInstallmentUnderlying(TxLoan loan) {
        return loan.getAmountSubmitted() / loan.getInstallment();
    }

    @Override
    public List<TxInstallment> findInstallmentsById(String id) {
        return installmentRepository.findByMember_IdAndAndStatus(id, Constant.UNPAID);
    }

    @Override
    public void payInstallment(PayReq payReq) {
        installmentRepository.updateStatus(payReq.getOrder(), payReq.getNominal());
    }
}

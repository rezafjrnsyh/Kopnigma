package com.enigma.vica.kopnigma.service.impl;

import com.enigma.vica.kopnigma.constant.Constant;
import com.enigma.vica.kopnigma.controller.httpreq.LoaningReq;
import com.enigma.vica.kopnigma.entity.Member;
import com.enigma.vica.kopnigma.entity.TxLoan;
import com.enigma.vica.kopnigma.repository.LoanRepository;
import com.enigma.vica.kopnigma.service.InstallmentService;
import com.enigma.vica.kopnigma.service.LoanService;
import com.enigma.vica.kopnigma.service.MemberService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    private final MemberService memberService;
    private final LoanRepository loanRepository;
    private final InstallmentService installmentService;

    public LoanServiceImpl(MemberService memberService, LoanRepository loanRepository, InstallmentService installmentService) {
        this.memberService = memberService;
        this.loanRepository = loanRepository;
        this.installmentService = installmentService;
    }

    @Override
    public void create(LoaningReq loaningReq, String memberId) {
        Member member = memberService.findById(memberId);
        TxLoan mapperLoan = new TxLoan();
        mapperLoan.setAmountSubmitted(loaningReq.getAmountSubmitted());
        mapperLoan.setInstallment(loaningReq.getInstallment());
        mapperLoan.setMember(member);
        long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        mapperLoan.setCreatedAt(timestamp);
        mapperLoan.setMember(member);
        TxLoan loan = loanRepository.save(mapperLoan);
        installmentService.calculateInstallment(loan, member);
    }

    @Override
    public TxLoan findMemberIdOnLoan(String memberId) {
        return loanRepository.findByMember_Id(memberId);
    }
}

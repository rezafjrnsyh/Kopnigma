package com.enigma.vica.kopnigma.service.impl;

import com.enigma.vica.kopnigma.constant.ResponseMessage;
import com.enigma.vica.kopnigma.controller.httpreq.DepositReq;
import com.enigma.vica.kopnigma.controller.httpreq.LoginReq;
import com.enigma.vica.kopnigma.controller.httpresp.NewMemberResp;
import com.enigma.vica.kopnigma.entity.Member;
import com.enigma.vica.kopnigma.entity.Savings;
import com.enigma.vica.kopnigma.exception.BadRequest;
import com.enigma.vica.kopnigma.repository.MemberRepository;
import com.enigma.vica.kopnigma.service.FileStorageService;
import com.enigma.vica.kopnigma.service.HistoryService;
import com.enigma.vica.kopnigma.service.MemberService;
import com.enigma.vica.kopnigma.service.SavingService;
import com.enigma.vica.kopnigma.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepo;
    private final FileStorageService fileStorageService;
    private final SavingService savingService;
    private final HistoryService historyService;
    private final Generator generator;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepo, FileStorageService fileStorageService, SavingService savingService, HistoryService historyService, Generator generator) {
        this.memberRepo = memberRepo;
        this.fileStorageService = fileStorageService;
        this.savingService = savingService;
        this.historyService = historyService;
        this.generator = generator;
    }

    @Override
    public NewMemberResp create(Member member, MultipartFile file) throws IOException {
        Long rek = generator.generaterekening();
        member.setAccountNumber(rek);
        Member newMember = memberRepo.save(member);
        Savings saving = savingService.createBalance(newMember);
        fileStorageService.store(file,newMember);
        NewMemberResp resp = new NewMemberResp();
        resp.setMember(newMember);
        resp.setBalance(saving.getBalance());
        return resp;
    }

    @Override
    public Member login(LoginReq loginReq) {
        Member member = memberRepo.findByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword());
        if (member == null) {
            throw new BadRequest(ResponseMessage.BAD_REQUEST_MESSAGE);
        }
        return member;
    }

    @Override
    public void depositBalance(DepositReq depositReq, Long accountNumber) {
        Member member = memberRepo.findByAccountNumber(accountNumber);
        savingService.moneyDeposit(depositReq.getNominal(), member);
        historyService.store(member, depositReq.getNominal());
    }

    @Override
    public Member findById(String id) {
        return memberRepo.findById(id);
    }
}

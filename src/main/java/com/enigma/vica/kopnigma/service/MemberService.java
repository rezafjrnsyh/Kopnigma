package com.enigma.vica.kopnigma.service;

import com.enigma.vica.kopnigma.controller.httpreq.DepositReq;
import com.enigma.vica.kopnigma.controller.httpreq.LoginReq;
import com.enigma.vica.kopnigma.controller.httpresp.NewMemberResp;
import com.enigma.vica.kopnigma.entity.Member;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface MemberService {
    NewMemberResp create(Member member, MultipartFile file) throws IOException;
    Member login(LoginReq loginReq);
    void depositBalance(DepositReq depositReq, Long accountNumber);
    Member findById(String memberId);
}

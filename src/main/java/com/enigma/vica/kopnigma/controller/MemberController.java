package com.enigma.vica.kopnigma.controller;

import com.enigma.vica.kopnigma.controller.httpreq.DepositReq;
import com.enigma.vica.kopnigma.controller.httpreq.LoaningReq;
import com.enigma.vica.kopnigma.controller.httpreq.LoginReq;
import com.enigma.vica.kopnigma.controller.httpreq.PayReq;
import com.enigma.vica.kopnigma.controller.httpresp.NewMemberResp;
import com.enigma.vica.kopnigma.dto.WebResponse;
import com.enigma.vica.kopnigma.entity.Document;
import com.enigma.vica.kopnigma.entity.Member;
import com.enigma.vica.kopnigma.entity.TxInstallment;
import com.enigma.vica.kopnigma.service.FileStorageService;
import com.enigma.vica.kopnigma.service.InstallmentService;
import com.enigma.vica.kopnigma.service.LoanService;
import com.enigma.vica.kopnigma.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final FileStorageService fileStorageService;
    private final LoanService loanService;
    private final InstallmentService installmentService;

    @Autowired
    public MemberController(MemberService memberService, FileStorageService fileStorageService, LoanService loanService, InstallmentService installmentService) {
        this.memberService = memberService;
        this.fileStorageService = fileStorageService;
        this.loanService = loanService;
        this.installmentService = installmentService;
    }

    @PostMapping("/signup")
    public WebResponse<NewMemberResp> signUp(@RequestPart("member") String request, @RequestPart("ktp") MultipartFile file) throws IOException {
//        Member memberJson = new Member();
        ObjectMapper objectMapper = new ObjectMapper();
        Member memberJson = objectMapper.readValue(request, Member.class);
        NewMemberResp member = memberService.create(memberJson, file);
        return new WebResponse<>(
                201,
                "Ok",
                member
        );
    }

    @PostMapping("/signin")
    public WebResponse<String> signIn(@RequestBody LoginReq payload) {
        Member member = memberService.login(payload);
        return new WebResponse<>(
                200,
                "Ok",
                member.getId()
        );
    }

    @GetMapping("/download/{memberId}")
    public ResponseEntity<Resource> download(@PathVariable String memberId){
        Document document = fileStorageService.getFile(memberId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
                .body(new ByteArrayResource(document.getData()));
    }

    @PostMapping("/deposit")
    public WebResponse<String> deposit(@RequestBody DepositReq nominal, @RequestParam(required = false) String rekening) {
        memberService.depositBalance(nominal, Long.parseLong(rekening));
        return new WebResponse<>(
                200,
                "Ok",
                "Anda berhasil melakukan deposit"
        );
    }

    @PostMapping("/loan/{memberId}")
    public WebResponse<String> loaning(@RequestBody LoaningReq payload, @PathVariable String memberId) {
        System.out.println(memberId);
        loanService.create(payload, memberId);
        return new WebResponse<>(
                200,
                "Ok",
                "Anda Berhasil Melakukan Pinjaman"
        );
    }

    @GetMapping("/installments")
    public WebResponse<List<TxInstallment>> allInstallment(@RequestParam String memberId) {
        List<TxInstallment> installments= installmentService.findInstallmentsById(memberId);
        return new WebResponse<>(
                200,
                "Ok",
                installments
        );
    }

    @PutMapping("/pay")
    public WebResponse<String> payInstallment(@RequestBody PayReq payReq) {
        installmentService.payInstallment(payReq);
        return new WebResponse<>(
                200,
                "Ok",
                "Anda berhasil melakukan pembayaran"
        );
    }
}

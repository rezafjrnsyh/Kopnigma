package com.enigma.vica.kopnigma.repository;

import com.enigma.vica.kopnigma.controller.httpreq.LoginReq;
import com.enigma.vica.kopnigma.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByEmailAndPassword(String email, String password);
    Member findByAccountNumber(Long accountNumber);
    Member findById(String id);
}

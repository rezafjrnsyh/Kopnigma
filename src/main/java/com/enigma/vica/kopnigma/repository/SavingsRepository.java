package com.enigma.vica.kopnigma.repository;

import com.enigma.vica.kopnigma.entity.Member;
import com.enigma.vica.kopnigma.entity.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SavingsRepository extends JpaRepository<Savings, String> {
    @Modifying
    @Query("update Savings as s set s.Balance = :nominal where s.member = :member")
    void updateBalance(Long nominal, Member member);

    Savings findByMember_Id(String memberId);
}

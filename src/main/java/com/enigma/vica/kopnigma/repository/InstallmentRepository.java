package com.enigma.vica.kopnigma.repository;

import com.enigma.vica.kopnigma.entity.TxInstallment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface InstallmentRepository extends JpaRepository<TxInstallment, String> {
    List<TxInstallment> findByMember_IdAndAndStatus(String memberId, Integer status);

    @Transactional
    @Modifying
    @Query("update TxInstallment as ti SET ti.status = 0 where ti.installmentOrder = :order " +
            "and (ti.installmentInterest+ ti.installmentUnderlying)= :nominal")
    void updateStatus(Integer order, Long nominal);
}

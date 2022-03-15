package com.enigma.vica.kopnigma.service;

import com.enigma.vica.kopnigma.entity.Member;
import com.enigma.vica.kopnigma.entity.Savings;

public interface SavingService {
    Savings createBalance(Member member);
    void moneyDeposit(Long nominal, Member member);
}

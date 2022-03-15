package com.enigma.vica.kopnigma.service.impl;

import com.enigma.vica.kopnigma.entity.Member;
import com.enigma.vica.kopnigma.entity.Savings;
import com.enigma.vica.kopnigma.repository.SavingsRepository;
import com.enigma.vica.kopnigma.service.SavingService;
import org.springframework.stereotype.Service;

@Service
public class SavingServiceImpl implements SavingService {

    private final SavingsRepository savingsRepository;

    public SavingServiceImpl(SavingsRepository savingsRepository) {
        this.savingsRepository = savingsRepository;
    }

    @Override
    public Savings createBalance(Member member) {
        Savings savings = new Savings();
        savings.setBalance(0L);
        savings.setMember(member);
        return savingsRepository.save(savings);
    }

    @Override
    public void moneyDeposit(Long nominal, Member member) {
        Savings savings = savingsRepository.findByMember_Id(member.getId());
        savings.setBalance(savings.getBalance() + nominal);
        savingsRepository.updateBalance(savings.getBalance(), member);
    }
}

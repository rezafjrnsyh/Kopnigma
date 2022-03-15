package com.enigma.vica.kopnigma.service.impl;

import com.enigma.vica.kopnigma.entity.Member;
import com.enigma.vica.kopnigma.entity.TxHistory;
import com.enigma.vica.kopnigma.repository.HistoryRepository;
import com.enigma.vica.kopnigma.service.HistoryService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public void store(Member member, Long nominal) {
        TxHistory history = new TxHistory();
        history.setAmount(nominal);
        history.setMember(member);
        long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        history.setCreatedAt(timestamp);
        historyRepository.save(history);
    }
}

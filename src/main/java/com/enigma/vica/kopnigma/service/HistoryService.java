package com.enigma.vica.kopnigma.service;

import com.enigma.vica.kopnigma.entity.Member;

public interface HistoryService {
    void store(Member member, Long nominal);
}

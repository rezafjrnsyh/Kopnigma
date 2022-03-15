package com.enigma.vica.kopnigma.repository;

import com.enigma.vica.kopnigma.entity.TxHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<TxHistory, String> {
}

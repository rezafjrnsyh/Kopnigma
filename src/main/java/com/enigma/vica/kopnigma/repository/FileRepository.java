package com.enigma.vica.kopnigma.repository;

import com.enigma.vica.kopnigma.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Document, String> {
    Document findByMember_Id(String memberId);
}

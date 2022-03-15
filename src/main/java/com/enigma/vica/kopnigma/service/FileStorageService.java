package com.enigma.vica.kopnigma.service;

import com.enigma.vica.kopnigma.entity.Document;
import com.enigma.vica.kopnigma.entity.Member;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {
    Document store(MultipartFile file, Member member) throws IOException;
    Document getFile(String memberId);
}

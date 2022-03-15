package com.enigma.vica.kopnigma.service.impl;

import com.enigma.vica.kopnigma.entity.Document;
import com.enigma.vica.kopnigma.entity.Member;
import com.enigma.vica.kopnigma.exception.FileNotFoundException;
import com.enigma.vica.kopnigma.repository.FileRepository;
import com.enigma.vica.kopnigma.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final FileRepository fileRepository;

    public FileStorageServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public Document store(MultipartFile file, Member member) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Document FileDB = new Document(fileName, file.getContentType(), file.getBytes(), member);
        return fileRepository.save(FileDB);
    }

    @Override
    public Document getFile(String memberId) {
        Document document = fileRepository.findByMember_Id(memberId);
        if (document == null) {
            throw new FileNotFoundException("File not found with id " + memberId);
        }
        return document;
    }
}

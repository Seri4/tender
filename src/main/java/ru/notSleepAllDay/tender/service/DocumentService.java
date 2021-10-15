package ru.notSleepAllDay.tender.service;

import org.springframework.web.multipart.MultipartFile;
import ru.notSleepAllDay.tender.dto.DocumentDto;
import ru.notSleepAllDay.tender.dto.UploadResponse;

import java.io.IOException;

public interface DocumentService {

    UploadResponse uploadFile(MultipartFile file) throws IOException;

    DocumentDto findById(Long id);
}

package ru.notSleepAllDay.tender.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.notSleepAllDay.tender.dto.DocumentDto;
import ru.notSleepAllDay.tender.dto.UploadResponse;
import ru.notSleepAllDay.tender.model.Document;
import ru.notSleepAllDay.tender.repository.DocumentRepository;
import ru.notSleepAllDay.tender.service.DocumentService;
import ru.notSleepAllDay.tender.service.mapper.DocumentMapper;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final static int MEGABYTES = 1048576;

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    @Override
    public UploadResponse uploadFile(MultipartFile file) throws IOException {
        Document document = Document.builder()
                .date(LocalDate.now())
                .extension(file.getContentType())
                .errorCount(0)
                .data(file.getBytes().toString())
                .name(file.getName())
                .size(getSizeInMegabytes(file.getSize()))
                .build();

        Document savedDocument = documentRepository.save(document);

        return UploadResponse.builder()
                .id(Objects.toString(savedDocument.getId()))
                .status("success")
                .build();

    }

    @Override
    public DocumentDto findById(Long id) throws EntityNotFoundException {
        Document item = documentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return documentMapper.toDto(item);
    }

    /**
     * Метод для пдсчета размера файла в мегабайтах
     * @param size
     * @return
     */
    private String getSizeInMegabytes(long size) {
        return Objects.toString(size / MEGABYTES);
    }
}

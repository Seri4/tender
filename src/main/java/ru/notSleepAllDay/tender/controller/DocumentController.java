package ru.notSleepAllDay.tender.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ru.notSleepAllDay.tender.dto.DocumentDto;
import ru.notSleepAllDay.tender.dto.UploadResponse;
import ru.notSleepAllDay.tender.service.DocumentService;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/api/v1")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }


    @Operation(
            summary = "Метод для загрузки документов",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Документ загружен"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ошибка сервера"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Загружен невалидный файл"
                    )
            }
    )
    @PostMapping("/uploadFile")
    public ResponseEntity uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            UploadResponse response = documentService.uploadFile(file);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            log.error("Ошибка при загрузке файла: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is invalid");
        }

    }

    @Operation(
            summary = "Метод получение документа по id",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Документ загружен"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ошибка сервера"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Документ не найден в системе"
                    )
            }
    )
    @PostMapping("/document")
    public ResponseEntity<DocumentDto> getById(@RequestParam Long id) {
        try {
            DocumentDto result = documentService.findById(id);
            return ResponseEntity.ok()
                    .body(result);
        } catch (EntityNotFoundException e) {
            log.info("В базе не найдено документа с id={}", id);
            return ResponseEntity.notFound().build();
        }
    }
}

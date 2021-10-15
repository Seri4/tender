package ru.notSleepAllDay.tender.service.mapper;

import org.springframework.stereotype.Component;
import ru.notSleepAllDay.tender.dto.DocumentDto;
import ru.notSleepAllDay.tender.model.Document;

import java.util.Objects;

@Component
public class DocumentMapper {

    /**
     * Метод для маппинга сущности из бд на дто
     * @param item сущность документа из бд
     * @return
     */
    public DocumentDto toDto(Document item) {
        return DocumentDto.builder()
                .id(item.getId())
                .date(item.getDate())
                .name(item.getName())
                .size(item.getSize())
                .errorCount(item.getErrorCount())
                .build();

    }

}

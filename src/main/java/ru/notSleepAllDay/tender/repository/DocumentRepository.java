package ru.notSleepAllDay.tender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.notSleepAllDay.tender.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}

package ru.notSleepAllDay.tender.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocumentDto {

    Long id;

    String name;

    LocalDate date;

    Integer errorCount;

    String size;

}

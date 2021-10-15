package ru.notSleepAllDay.tender.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.notSleepAllDay.tender.client.config.DefaultClientConfiguration;

@FeignClient(name="documentClient", url="${tender.docx.url}", configuration = DefaultClientConfiguration.class)
public interface DocumentClient {


}

package net.engineeringdigest.journalApp.dto;

import lombok.Data;
import net.engineeringdigest.journalApp.enums.Sentiment;

import java.time.LocalDateTime;

@Data
public class JournalEntryDTO {
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;
}

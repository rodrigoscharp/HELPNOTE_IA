package com.notemind.ai.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class NoteResponse {
    private Long id;
    private String title;
    private String summary;
    private List<String> keywords;
    private String structuredContent;
    private LocalDateTime createdAt;
}

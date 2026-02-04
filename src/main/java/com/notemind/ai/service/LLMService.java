package com.notemind.ai.service;

import lombok.Builder;
import lombok.Data;
import java.util.List;

public interface LLMService {

    /**
     * Processes raw text to generate a structured analysis.
     * @param rawText The text to process.
     * @return A ProcessingResult object containing summary, keywords, etc.
     */
    ProcessingResult generateStructuredContent(String rawText);

    @Data
    @Builder
    class ProcessingResult {
        private String summary;
        private List<String> keywords;
        private String structuredContent; // Markdown format
    }
}

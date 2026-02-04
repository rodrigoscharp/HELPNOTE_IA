package com.notemind.ai.service.impl;

import com.notemind.ai.service.LLMService;
import com.notemind.ai.service.TranscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class LLMServiceImpl implements LLMService {

    @Override
    public ProcessingResult generateStructuredContent(String rawText) {
        log.warn("Using mock LLM service. Returning placeholder content.");
        // In a real implementation, this method would make a call to a Large Language Model API (e.g., OpenAI, Gemini).
        // It would send the rawText and ask for a summary, keywords, and structured content in Markdown.

        String summary = "This is a mock summary about software architecture and microservices, highlighting their pros and cons.";
        List<String> keywords = Arrays.asList("Microservices", "Software Architecture", "Scalability", "Deployment");
        String structuredContent = """
                # Software Architecture: Microservices
                                
                ## Key Topic: Introduction to Microservices
                Microservices are an architectural style that structures an application as a collection of loosely coupled services. This approach enables the continuous delivery/deployment of large, complex applications.
                                
                ### Benefits
                - **Scalability**: Services can be scaled independently.
                - **Flexibility**: Different technologies can be used for different services.
                - **Resilience**: Failure in one service does not necessarily impact the entire application.
                                
                ### Drawbacks
                - **Operational Complexity**: Requires mature DevOps practices.
                - **Distributed System Challenges**: Involves network latency and fault tolerance concerns.
                """;

        return ProcessingResult.builder()
                .summary(summary)
                .keywords(keywords)
                .structuredContent(structuredContent)
                .build();
    }
}

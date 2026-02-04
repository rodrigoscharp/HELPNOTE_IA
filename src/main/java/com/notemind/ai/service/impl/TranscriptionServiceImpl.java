package com.notemind.ai.service.impl;

import com.notemind.ai.service.TranscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TranscriptionServiceImpl implements TranscriptionService {

    @Override
    public String transcribe(String audioFilePath) {
        log.warn("Using mock transcription service. Returning placeholder text.");
        // In a real implementation, this would call an external Speech-to-Text API.
        // e.g., using WebClient to send the file at audioFilePath.
        return "This is a placeholder for the transcribed text from the audio file located at " + audioFilePath + ". " +
               "The topic is about software architecture, specifically microservices. " +
               "We will discuss the benefits, such as scalability and independent deployment, " +
               "and the drawbacks, like complexity in management and testing.";
    }
}

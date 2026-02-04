package com.notemind.ai.service;

public interface TranscriptionService {
    /**
     * Transcribes an audio file to text.
     * @param audioFilePath Path to the audio file.
     * @return The transcribed text.
     */
    String transcribe(String audioFilePath);
}

package com.notemind.ai.service.impl;

import com.notemind.ai.domain.Note;
import com.notemind.ai.dto.NoteResponse;
import com.notemind.ai.exception.ResourceNotFoundException;
import com.notemind.ai.repository.NoteRepository;
import com.notemind.ai.service.LLMService;
import com.notemind.ai.service.NoteService;
import com.notemind.ai.service.TranscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final TranscriptionService transcriptionService;
    private final LLMService llmService;
    // Would be a separate service to handle file I/O, e.g., FileStorageService

    @Override
    public NoteResponse processAudio(MultipartFile audioFile, String title) {
        log.info("Processing audio file: {}", audioFile.getOriginalFilename());

        // 1. Store the audio file (placeholder logic)
        // In a real app: Path filePath = fileStorageService.store(audioFile);
        String filePath = "/path/to/uploads/" + audioFile.getOriginalFilename();

        // 2. Transcribe audio to text
        String transcribedText = transcriptionService.transcribe(filePath);
        log.info("Transcription completed.");

        // 3. Use LLM to process text
        LLMService.ProcessingResult llmResult = llmService.generateStructuredContent(transcribedText);
        log.info("LLM processing completed.");

        // 4. Create and save the Note entity
        Note note = Note.builder()
                .title(title)
                .audioFilePath(filePath)
                .originalContent(transcribedText)
                .summary(llmResult.getSummary())
                .keywords(llmResult.getKeywords())
                .structuredContent(llmResult.getStructuredContent())
                .build();

        Note savedNote = noteRepository.save(note);
        log.info("Note saved successfully with ID: {}", savedNote.getId());

        // 5. Return the response DTO
        return toNoteResponse(savedNote);
    }

    @Override
    public NoteResponse getNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id: " + id));
        return toNoteResponse(note);
    }
    
    @Override
    public NoteResponse regenerateSummary(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id: " + id));
                
        log.info("Regenerating summary for note ID: {}", id);
        
        LLMService.ProcessingResult llmResult = llmService.generateStructuredContent(note.getOriginalContent());
        log.info("LLM processing completed for regeneration.");

        note.setSummary(llmResult.getSummary());
        note.setKeywords(llmResult.getKeywords());
        note.setStructuredContent(llmResult.getStructuredContent());
        
        Note updatedNote = noteRepository.save(note);
        log.info("Note updated successfully with ID: {}", updatedNote.getId());

        return toNoteResponse(updatedNote);
    }

    private NoteResponse toNoteResponse(Note note) {
        return NoteResponse.builder()
                .id(note.getId())
                .title(note.getTitle())
                .summary(note.getSummary())
                .keywords(note.getKeywords())
                .structuredContent(note.getStructuredContent())
                .createdAt(note.getCreatedAt())
                .build();
    }
}

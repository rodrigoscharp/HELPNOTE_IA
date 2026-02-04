package com.notemind.ai.service;

import com.notemind.ai.dto.NoteResponse;
import org.springframework.web.multipart.MultipartFile;

public interface NoteService {

    NoteResponse processAudio(MultipartFile audioFile, String title);

    NoteResponse getNoteById(Long id);
    
    NoteResponse regenerateSummary(Long id);
}

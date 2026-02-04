package com.notemind.ai.controller;

import com.notemind.ai.dto.NoteResponse;
import com.notemind.ai.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/upload")
    public ResponseEntity<NoteResponse> uploadAudio(@RequestParam("file") MultipartFile file) {
        String title = file.getOriginalFilename() != null ? file.getOriginalFilename() : "Untitled";
        NoteResponse noteResponse = noteService.processAudio(file, title);
        return ResponseEntity.status(HttpStatus.CREATED).body(noteResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable Long id) {
        NoteResponse noteResponse = noteService.getNoteById(id);
        return ResponseEntity.ok(noteResponse);
    }
    
    @PostMapping("/{id}/summary")
    public ResponseEntity<NoteResponse> regenerateSummary(@PathVariable Long id) {
        NoteResponse noteResponse = noteService.regenerateSummary(id);
        return ResponseEntity.ok(noteResponse);
    }
}

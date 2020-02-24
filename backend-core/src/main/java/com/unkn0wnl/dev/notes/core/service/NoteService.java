package com.unkn0wnl.dev.notes.core.service;

import com.unkn0wnl.dev.notes.core.dto.NoteDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteService {

    NoteDto saveNote(String heading, String text, String username);

    List<NoteDto> getAll(Long userId);

    NoteDto getNoteById(Long noteId);

}

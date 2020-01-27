package com.unkn0wnl.dev.notes.core.service;

import com.unkn0wnl.dev.notes.core.entity.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteService {

    Note saveNote(String heading, String text, String username);

    List<Note> getAll();

    Note getNoteById(Long noteId);

}

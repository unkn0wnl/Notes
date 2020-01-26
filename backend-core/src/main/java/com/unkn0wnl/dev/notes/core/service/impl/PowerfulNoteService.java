package com.unkn0wnl.dev.notes.core.service.impl;

import com.unkn0wnl.dev.notes.core.entity.model.Note;
import com.unkn0wnl.dev.notes.core.repository.NoteRepository;
import com.unkn0wnl.dev.notes.core.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowerfulNoteService implements NoteService {

    private NoteRepository noteRepository;

    @Autowired
    public PowerfulNoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note saveNote(String heading, String text) {
        Note newNote = new Note(heading, text);
        return noteRepository.save(newNote);
    }

    @Override
    public List<Note> getAll() {
        return noteRepository.findAll();
    }
}

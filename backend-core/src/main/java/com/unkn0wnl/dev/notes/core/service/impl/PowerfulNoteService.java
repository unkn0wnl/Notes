package com.unkn0wnl.dev.notes.core.service.impl;

import com.unkn0wnl.dev.notes.core.entity.model.Note;
import com.unkn0wnl.dev.notes.core.entity.model.User;
import com.unkn0wnl.dev.notes.core.repository.NoteRepository;
import com.unkn0wnl.dev.notes.core.repository.UserRepository;
import com.unkn0wnl.dev.notes.core.service.NoteService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@Service
public class PowerfulNoteService implements NoteService {

    private static final Logger LOGGER;

    static {
        LOGGER = getLogger(PowerfulNoteService.class);
    }

    private NoteRepository noteRepository;
    private UserRepository userRepository;

    @Autowired
    public PowerfulNoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Note saveNote(String heading, String text, String username) {
        Note newNote = new Note(heading, text);
        User user = userRepository.findUserByUsernameOrEmail(username, null)
                .orElseThrow(
                        () -> new EntityNotFoundException("User not found with username : " + username)
                );
        newNote.setAuthor(user);
        return noteRepository.save(newNote);
    }

    @Override
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNoteById(Long noteId) {
        return noteRepository.getOne(noteId);
    }
}

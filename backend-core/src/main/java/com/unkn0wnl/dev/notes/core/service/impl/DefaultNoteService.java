package com.unkn0wnl.dev.notes.core.service.impl;

import com.unkn0wnl.dev.notes.core.dto.NoteDto;
import com.unkn0wnl.dev.notes.core.entity.model.Note;
import com.unkn0wnl.dev.notes.core.entity.model.User;
import com.unkn0wnl.dev.notes.core.mapper.NoteMapper;
import com.unkn0wnl.dev.notes.core.repository.NoteRepository;
import com.unkn0wnl.dev.notes.core.repository.UserRepository;
import com.unkn0wnl.dev.notes.core.service.NoteService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.LogManager.getLogger;

@Service
public class DefaultNoteService implements NoteService {

    private static final Logger LOGGER;

    static {
        LOGGER = getLogger(DefaultNoteService.class);
    }

    private NoteMapper noteMapper = NoteMapper.NOTE_MAPPER;
    private NoteRepository noteRepository;
    private UserRepository userRepository;

    @Autowired
    public DefaultNoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public NoteDto saveNote(String heading, String text, String username) {
        Note newNote = new Note(heading, text);
        User user = userRepository.findUserByUsernameOrEmail(username, null)
                .orElseThrow(
                        () -> new EntityNotFoundException("User not found with username : " + username)
                );
        newNote.setAuthor(user);
        return noteMapper.fromNote(noteRepository.save(newNote));
    }

    @Override
    public List<NoteDto> getAll(Long userId) {
        return noteRepository.findNoteByUserId(userId)
                .stream()
                .map(noteMapper::fromNote)
                .collect(Collectors.toList());
    }

    @Override
    public NoteDto getNoteById(Long noteId) {
        return noteMapper.fromNote(noteRepository.getOne(noteId));
    }

}
package com.unkn0wnl.dev.notes.api.controller;

import com.unkn0wnl.dev.notes.core.entity.model.Note;
import com.unkn0wnl.dev.notes.core.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

}

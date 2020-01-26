package com.unkn0wnl.dev.notes.api.controller;

import com.unkn0wnl.dev.notes.api.payload.request.NoteRequest;
import com.unkn0wnl.dev.notes.api.payload.response.ApiResponse;
import com.unkn0wnl.dev.notes.core.entity.model.Note;
import com.unkn0wnl.dev.notes.core.service.NoteService;
import com.unkn0wnl.dev.notes.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class NoteController {

    private NoteService noteService;
    private UserService userService;

    @Autowired
    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public List<Note> getNotes(@AuthenticationPrincipal UserDetails currentUser) {
        return noteService.getAll();
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    public ResponseEntity<?> createNote(@Valid @RequestBody NoteRequest noteRequest, @AuthenticationPrincipal UserDetails userDetails) {
        Note savedNote = noteService.saveNote(noteRequest.getHeading(), noteRequest.getText(), userDetails.getUsername());
        URI savedNoteLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{newNoteId}")
                .buildAndExpand(savedNote.getId()).toUri();

        return ResponseEntity.created(savedNoteLocation)
                .body(new ApiResponse(true, "Note created successfully."));
    }

}

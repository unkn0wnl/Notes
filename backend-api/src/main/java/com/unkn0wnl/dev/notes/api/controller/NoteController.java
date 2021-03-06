package com.unkn0wnl.dev.notes.api.controller;

import com.unkn0wnl.dev.notes.api.payload.request.NoteRequest;
import com.unkn0wnl.dev.notes.api.payload.response.ApiResponse;
import com.unkn0wnl.dev.notes.api.security.principal.UserPrincipal;
import com.unkn0wnl.dev.notes.core.dto.NoteDto;
import com.unkn0wnl.dev.notes.core.service.NoteService;
import org.apache.logging.log4j.Logger;
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

import static org.apache.logging.log4j.LogManager.getLogger;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class NoteController {

    private static final Logger LOGGER;

    static {
        LOGGER = getLogger(NoteController.class);
    }

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public List<NoteDto> getNotes(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return noteService.getAll(userPrincipal.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    public ResponseEntity<?> createNote(@Valid @RequestBody NoteRequest noteRequest,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        NoteDto savedNote = noteService.saveNote(noteRequest.getHeading(), noteRequest.getText(), userDetails.getUsername());
        URI savedNoteLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{newNoteId}")
                .buildAndExpand(savedNote.getId()).toUri();

        return ResponseEntity.created(savedNoteLocation)
                .body(new ApiResponse(true, "Note created successfully."));
    }

    @RequestMapping(value = "/note/{noteId}")
    public NoteDto getNoteById(@PathVariable Long noteId) {
        return noteService.getNoteById(noteId);
    }

}
package com.unkn0wnl.dev.notes.core.repository;

import com.unkn0wnl.dev.notes.core.entity.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
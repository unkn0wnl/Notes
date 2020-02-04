package com.unkn0wnl.dev.notes.core.repository;

import com.unkn0wnl.dev.notes.core.entity.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RepositoryRestResource(path = "note")
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n WHERE n.author.id = :userId")
    List<Note> findNoteByUserId(@Param("userId") Long userId);
}
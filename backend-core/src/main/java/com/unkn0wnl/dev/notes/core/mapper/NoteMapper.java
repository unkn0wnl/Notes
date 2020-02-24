package com.unkn0wnl.dev.notes.core.mapper;

import com.unkn0wnl.dev.notes.core.dto.NoteDto;
import com.unkn0wnl.dev.notes.core.entity.model.Note;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoteMapper {

    NoteMapper NOTE_MAPPER = Mappers.getMapper(NoteMapper.class);

    NoteDto fromNote(Note note);

    Note toNote(NoteDto noteDto);

}
package com.unkn0wnl.dev.notes.core.dto;

import com.unkn0wnl.dev.notes.core.entity.model.User;

public class NoteDto {

    private Long id;
    private String text;
    private String heading;
    private User author;

    public NoteDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

}
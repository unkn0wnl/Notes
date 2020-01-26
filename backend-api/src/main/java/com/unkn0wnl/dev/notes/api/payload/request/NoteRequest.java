package com.unkn0wnl.dev.notes.api.payload.request;

import org.hibernate.validator.constraints.NotBlank;

public class NoteRequest {

    private String heading;

    @NotBlank
    private String text;

    public NoteRequest() {
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "NoteRequest{" +
                "heading='" + heading + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
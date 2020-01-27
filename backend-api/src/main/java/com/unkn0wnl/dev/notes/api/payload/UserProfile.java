package com.unkn0wnl.dev.notes.api.payload;

import java.time.Instant;

public class UserProfile {

    private Long id;
    private String name;
    private String username;
    private Instant joined;
    private Long noteCount;

    public UserProfile(Long id, String name, String username, Instant joined, Long noteCount) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.joined = joined;
        this.noteCount = noteCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getJoined() {
        return joined;
    }

    public void setJoined(Instant joined) {
        this.joined = joined;
    }

    public Long getNoteCount() {
        return noteCount;
    }

    public void setNoteCount(Long noteCount) {
        this.noteCount = noteCount;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", joined=" + joined +
                ", noteCount=" + noteCount +
                '}';
    }

}
package com.unkn0wnl.dev.notes.core.entity.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "note", schema = "public")
public class Note extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String heading;
    @NotBlank
    private String text;

    @ManyToOne(
            fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    public Note() {
        super();
    }

    public Note(String heading, String text) {
        this.heading = heading;
        this.text = text;
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

}
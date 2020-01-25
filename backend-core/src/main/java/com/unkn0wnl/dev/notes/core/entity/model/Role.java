package com.unkn0wnl.dev.notes.core.entity.model;

import com.unkn0wnl.dev.notes.core.entity.model.EntityConstantsHolder.RoleConstants;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role", schema = "public")
public class Role implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = RoleConstants.ROLE_NAME_LENGTH)
    private RoleEnum name;

    public Role() {
        super();
    }

    public Role(RoleEnum name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }

}
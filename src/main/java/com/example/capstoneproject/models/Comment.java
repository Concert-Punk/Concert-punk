package com.example.capstoneproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false, unique = false, columnDefinition = "TEXT")
    @Getter
    @Setter
    private String comment_text;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    @OneToMany
    private User potentialAttendee;

    @Column(nullable = false, unique = false)
    @Getter
    @Setter
    private Timestamp createdAt;

    @OneToMany
    @Getter
    @Setter
    private Event event;

}

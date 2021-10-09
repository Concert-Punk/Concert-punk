package com.example.capstoneproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
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


    @Column(nullable = false, unique = false)
    @Getter
    @Setter
    private Timestamp createdAt;


    @ManyToOne
    @JoinColumn(name = "event_id")
    @Getter
    @Setter
    private Event event;



    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;


}

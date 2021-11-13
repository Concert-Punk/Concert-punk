package com.example.capstoneproject.models;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="EventPhoto")
@AllArgsConstructor
@NoArgsConstructor
public class EventPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @Getter
    @Setter
    private Event event;
}

package com.example.capstoneproject.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="attending_status")
@NoArgsConstructor
@AllArgsConstructor
public class AttendingStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @Enumerated(EnumType.STRING)
    @Getter @Setter private Attending attending;

    @OneToOne
    @Getter @Setter private User potentialAttendee;

    @OneToOne
    @Getter @Setter private Event event;


}

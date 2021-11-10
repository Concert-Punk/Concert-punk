package com.example.capstoneproject.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Events")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = true, unique = true)
    @Getter
    @Setter
    private Long api_id;

    @Column(nullable = false, unique = false)
    @Getter
    @Setter
    private String title;

    @Column(nullable = false, unique = false)
    @Getter
    @Setter
    private String location;

    @Column(nullable = false, unique = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Getter
    @Setter
    private LocalDateTime startTime;

//    @Column(nullable = true, unique = false)
    @Column(nullable = false, unique = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Getter
    @Setter
    private LocalDateTime endTime;

    @Column(nullable = true, unique = false, columnDefinition = "TEXT")
    @Getter
    @Setter
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @Getter
    @Setter
    private User owner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "event_tags",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )

    @Column(nullable = false, unique = false)
    @Getter
    @Setter
    private List<Tag> tag;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    @Getter
    @Setter
    private List<Comment> comments;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "attending_users",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "attending_users")}
    )
    private List<User> potentialAttendees;



}

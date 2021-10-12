package com.example.capstoneproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String username;

    @Column(nullable = false)
    @Getter
    @Setter
    private String password;

    @Column(nullable = false)
    @Getter
    @Setter
    private String email;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Roles role;

    @Column(nullable = false, columnDefinition = "boolean")
    @Getter
    @Setter
    private Boolean isActive;

    @OneToOne
    @Getter
    @Setter
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @Getter
    @Setter
    private List<Event> events;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @Getter
//    @Setter
//    private User owner;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Getter
    @Setter
    private List<Comment> comments;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "followers",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "followed_id")}
    )
    private List<User> following;

    @ManyToMany(mappedBy = "following")
    private List<User> followers;


//    @ManyToOne
//    @JoinColumn(name="event_id")
//    @Getter @Setter
//    private Event event;

    @ManyToMany(mappedBy = "potentialAttendees")
    private List<Event> potentialEvents;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Getter
    @Setter
    private List<EventPhoto> eventphoto;





    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Getter
    @Setter
    private List<user_photo_table>  userphotos;

}




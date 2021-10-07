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
    @Getter @Setter private long id;

    @Column(nullable = false, unique = true)
    @Getter @Setter private String username;

    @Column(nullable = false)
    @Getter @Setter private String password;

    @Column(nullable = false)
    @Getter @Setter private String email;

    @Enumerated(EnumType.STRING)
    @Getter @Setter private Roles role;

    @Column(nullable = false, columnDefinition = "boolean")
    @Getter @Setter private Boolean isActive;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
//    private List<Post> posts;

}

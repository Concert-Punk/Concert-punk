//package com.example.capstoneproject.models;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name="user_playlist")
//@NoArgsConstructor
//@AllArgsConstructor
//public class Song {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Getter @Setter
//    private long userid;
//
//    @Column(nullable = false)
//    @Getter @Setter private String videoid;
//
//    @Column(nullable = false)
//    @Getter @Setter private String artist;
//
//    @Column(nullable = false)
//    @Getter @Setter private Long duration;
//
//    @Column(nullable = false)
//    @Getter @Setter private Long plays;
//
//    @ManyToOne(cascade = CascadeType.ALL, mappedBy = "user")
//    @Getter
//    @Setter
//    private List<Song> songs;
//
//}
//

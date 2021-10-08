package com.example.capstoneproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;



@Entity
@Table(name = "location")
@NoArgsConstructor
@AllArgsConstructor

public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String city;


    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String State;


    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String street_address;


    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String zipcode;

}



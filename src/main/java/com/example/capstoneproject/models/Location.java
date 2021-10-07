package com.example.capstoneproject.models;


import org.apache.catalina.User;

import javax.persistence.*;


@Entity
@Table(name = "location")

public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}

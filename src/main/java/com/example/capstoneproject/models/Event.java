package com.example.capstoneproject.models;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private int api_id;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false, unique = true)
    private String location;
    @Column(nullable = false, unique = true)
    private int datetime;
    @Column(nullable = false, unique = true)
    private String description;
    @Column(nullable = false, unique = true)
    private String genre;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User owner;

    public events() {
    }

    public events(String title, String location, String venue, int datetime, String description, String genre) {
        this.title = title;
        this.location = location;
        this.venue = venue;
        this.datetime = datetime;
        this.description = description;
        this.genre = genre;
    }

    public events(long id, int api_id, String title, String location, String venue, int datetime, String description, String genre) {
        this.id = id;
        this.api_id = api_id;
        this.title = title;
        this.location = location;
        this.venue = venue;
        this.datetime = datetime;
        this.description = description;
        this.genre = genre;
    }

    private String venue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getDatetime() {
        return datetime;
    }

    public void setDatetime(int datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}

package com.example.capstoneproject.models;

import jdk.jfr.Event;
import org.springframework.stereotype.Controller;

import org.apache.catalina.User;

import javax.persistence.*;


@Entity
@Table(name="photo")

public class EventPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @Column(nullable = false, columnDefinition = "PHOTO")
    private String image_url;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private User owner;

    public EventPhoto(){
    }

    public EventPhoto(Long id, String title, String body, User owner, String image_url) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.owner = owner;
        this.image_url = image_url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}

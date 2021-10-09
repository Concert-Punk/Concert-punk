package com.example.capstoneproject.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Entity
@Table(name="photo")


public class EventPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "PHOTO")
    private String image_url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public EventPhoto(){
    }

    public EventPhoto(Long id, String image_url) {
        this.id = id;
        this.image_url = image_url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}

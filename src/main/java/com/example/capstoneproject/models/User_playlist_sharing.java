package com.example.capstoneproject.models;



import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Users_playlist_sharing")
@NoArgsConstructor
@AllArgsConstructor


public class User_playlist_sharing {

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

}

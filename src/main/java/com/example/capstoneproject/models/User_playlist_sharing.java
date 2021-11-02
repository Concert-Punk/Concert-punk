package com.example.capstoneproject.models;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    public User_playlist_sharing(Long id) {
        this.id = id;
    }

}

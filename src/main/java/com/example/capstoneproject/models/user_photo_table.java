package com.example.capstoneproject.models;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="user_photo_table")
@NoArgsConstructor
@AllArgsConstructor
public class user_photo_table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String image_url;

    @ManyToOne
    @JoinColumn(name = "user_id" )
    @Getter
    @Setter
    private User user;


}

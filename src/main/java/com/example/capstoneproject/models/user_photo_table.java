package com.example.capstoneproject.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;


@Entity
@Table(name="user_photo_table")
@AllArgsConstructor
public class user_photo_table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false, columnDefinition = "PHOTO")
    @Getter
    @Setter
    private String image_url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private user_photo_table owner;

    public user_photo_table(){
    }

    public user_photo_table(Long id, String image_url) {
        this.id = id;
        this.image_url = image_url;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getImage_url() {
//        return image_url;
//    }
//
//    public void setImage_url(String image_url) {
//        this.image_url = image_url;
//    }
}

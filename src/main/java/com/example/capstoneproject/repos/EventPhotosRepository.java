package com.example.capstoneproject.repos;

import com.example.capstoneproject.models.EventPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventPhotosRepository extends JpaRepository<EventPhoto, Long> {
}

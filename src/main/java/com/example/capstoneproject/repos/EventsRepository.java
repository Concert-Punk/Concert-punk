package com.example.capstoneproject.repos;

import com.example.capstoneproject.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Event, Long> {
}

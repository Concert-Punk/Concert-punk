package com.example.capstoneproject.repos;

import com.example.capstoneproject.models.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
}

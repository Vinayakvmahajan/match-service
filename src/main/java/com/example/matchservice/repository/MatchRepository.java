package com.example.matchservice.repository;

import com.example.matchservice.entity.Match;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Tag(name = "Match Repository", description = "Repository for managing cricket matches")
public interface MatchRepository extends JpaRepository<Match, Long> {
}

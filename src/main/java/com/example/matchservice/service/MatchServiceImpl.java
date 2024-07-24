package com.example.matchservice.service;


import com.example.matchservice.entity.Match;
import com.example.matchservice.repository.MatchRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Tag(name = "Match Service", description = "Service for managing cricket matches")
public class MatchServiceImpl implements MatchService{

    @Autowired
    private MatchRepository matchRepository;

    @Operation(summary = "Create a new match")
    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    @Operation(summary = "Get a match by ID")
    public Optional<Match> getMatchById(Long id) {
        return Optional.ofNullable(matchRepository.findById(id).orElseThrow(() -> new RuntimeException("Match not found")));
    }

    @Operation(summary = "Get all matches")
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @Operation(summary = "Update a match by ID")
    public Match updateMatch(Long id, Match matchDetails) {
        Match match = getMatchById(id).get();
        match.setTeamA(matchDetails.getTeamA());
        match.setTeamB(matchDetails.getTeamB());
        match.setScore(matchDetails.getScore());
        return matchRepository.save(match);
    }

    @Operation(summary = "Delete a match by ID")
    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}

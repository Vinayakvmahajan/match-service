package com.example.matchservice.service;

import com.example.matchservice.entity.Match;
import java.util.Optional;

public interface MatchService {
    Match createMatch(Match match);
    Optional<Match> getMatchById(Long id);
    Match updateMatch(Long id, Match matchDetails);
    void deleteMatch(Long id);
}

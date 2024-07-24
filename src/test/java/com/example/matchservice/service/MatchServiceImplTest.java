package com.example.matchservice.service;

import com.example.matchservice.entity.Match;
import com.example.matchservice.repository.MatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchServiceImplTest {

    @InjectMocks
    private MatchServiceImpl matchService;

    @Mock
    private MatchRepository matchRepository;

    private Match match;

    @BeforeEach
    void setUp() {
        match = new Match(1L, "Team A", "Team B", "250/7");
    }

    @Test
    void testCreateMatch() {
        when(matchRepository.save(any(Match.class))).thenReturn(match);

        Match createdMatch = matchService.createMatch(match);
        assertNotNull(createdMatch);
        assertEquals(match, createdMatch);
        verify(matchRepository, times(1)).save(match);
    }

    @Test
    void testGetMatchById() {
        when(matchRepository.findById(1L)).thenReturn(Optional.of(match));

        Optional<Match> retrievedMatch = matchService.getMatchById(1L);
        assertTrue(retrievedMatch.isPresent());
        assertEquals(match, retrievedMatch.get());
    }

    @Test
    void testGetMatchById_NotFound() {
        when(matchRepository.findById(1L)).thenReturn(Optional.empty());

        try{
        Optional<Match> retrievedMatch = matchService.getMatchById(1L);
        } catch (RuntimeException e){
            assertTrue(e.getMessage().contains("Match not found"));
        }

    }

    @Test
    void testGetAllMatches() {
        List<Match> matches = Arrays.asList(
            new Match(1L, "Team A", "Team B", "250/7"),
            new Match(2L, "Team C", "Team D", "300/8")
        );
        when(matchRepository.findAll()).thenReturn(matches);

        List<Match> allMatches = matchService.getAllMatches();
        assertNotNull(allMatches);
        assertEquals(2, allMatches.size());
        assertEquals(matches, allMatches);
    }

    @Test
    void testUpdateMatch() {
        Match updatedMatch = new Match(1L, "Team A", "Team B", "300/8");
        when(matchRepository.findById(1L)).thenReturn(Optional.of(match));
        when(matchRepository.save(any(Match.class))).thenReturn(updatedMatch);

        Match result = matchService.updateMatch(1L, updatedMatch);
        assertNotNull(result);
        assertEquals(updatedMatch.getScore(), result.getScore());
        verify(matchRepository, times(1)).save(updatedMatch);
    }

    @Test
    void testUpdateMatch_NotFound() {
        when(matchRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            matchService.updateMatch(1L, match);
        });
        assertEquals("Match not found", exception.getMessage());
    }

    @Test
    void testDeleteMatch() {
        doNothing().when(matchRepository).deleteById(1L);

        matchService.deleteMatch(1L);
        verify(matchRepository, times(1)).deleteById(1L);
    }
}

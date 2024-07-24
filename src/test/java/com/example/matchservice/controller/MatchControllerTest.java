package com.example.matchservice.controller;

import com.example.matchservice.entity.Match;
import com.example.matchservice.service.MatchService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.AssertionErrors;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchControllerTest {

    @InjectMocks
    private MatchController matchController;

    @Mock
    private MatchService matchService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMatch() {
        Match match = new Match(1L, "Team A", "Team B", "250/7");
        when(matchService.createMatch(any(Match.class))).thenReturn(match);

        Match createdMatch = matchController.createMatch(match);
        Assertions.assertNotNull(createdMatch);
        //assertEquals(match, createdMatch);
        verify(matchService, times(1)).createMatch(match);
    }

    @Test
    public void testGetMatchById() {
        Match match = new Match(1L, "Team A", "Team B", "250/7");
        when(matchService.getMatchById(1L)).thenReturn(Optional.of(match));

        ResponseEntity<Match> responseEntity = matchController.getMatchById(1L);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(match, responseEntity.getBody());
    }

    @Test
    public void testGetMatchById_NotFound() {
        when(matchService.getMatchById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Match> responseEntity = matchController.getMatchById(1L);
        Assertions.assertEquals(404, responseEntity.getStatusCodeValue());
        Assertions.assertNull(responseEntity.getBody());
    }

    @Test
    public void testUpdateMatch() {
        Match match = new Match(1L, "Team A", "Team B", "250/7");
        Match updatedMatch = new Match(1L, "Team A", "Team B", "300/8");
        when(matchService.updateMatch(eq(1L), any(Match.class))).thenReturn(updatedMatch);

        ResponseEntity<Match> responseEntity = matchController.updateMatch(1L, match);
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(updatedMatch, responseEntity.getBody());
    }

    @Test
    public void testUpdateMatch_NotFound() {
        Match match = new Match(1L, "Team A", "Team B", "250/7");
        when(matchService.updateMatch(eq(1L), any(Match.class))).thenThrow(new RuntimeException());

        ResponseEntity<Match> responseEntity = matchController.updateMatch(1L, match);
        Assertions.assertEquals(404, responseEntity.getStatusCodeValue());
        Assertions.assertNull(responseEntity.getBody());
    }

    @Test
    public void testDeleteMatch() {
        doNothing().when(matchService).deleteMatch(1L);

        ResponseEntity<Void> responseEntity = matchController.deleteMatch(1L);
        Assertions.assertEquals(204, responseEntity.getStatusCodeValue());
        verify(matchService, times(1)).deleteMatch(1L);
    }
}

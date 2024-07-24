package com.example.matchservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matches")
@Schema(description = "Entity representing a cricket match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the match", example = "1")
    private Long id;

    @Schema(description = "Name of team A", example = "Team A")
    private String teamA;

    @Schema(description = "Name of team B", example = "Team B")
    private String teamB;

    @Schema(description = "Score of the match", example = "250/7")
    private String score;
}

package com.will.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchScoreView {
    private Long player1Id;
    private Long player2Id;
    private String player1Name;
    private String player2Name;
    private String player1GameScore;
    private String player2GameScore;
    private String player1SetScore;
    private String player2SetScore;
    private String player1MatchScore;
    private String player2MatchScore;
    private String gameState;
}

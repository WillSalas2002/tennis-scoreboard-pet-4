package com.will.dto;

import com.will.service.calculation.MatchScore;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MatchScoreModel {

    private final PlayerScoreModel player1;
    private final PlayerScoreModel player2;
    @Setter
    private PlayerScoreModel winner;
    private final MatchScore matchScore;

    public MatchScoreModel(PlayerDTO playerDTO1, PlayerDTO playerDTO2, MatchScore matchScore) {
        this.player1 = new PlayerScoreModel(playerDTO1.getId(), playerDTO1.getName());
        this.player2 = new PlayerScoreModel(playerDTO2.getId(), playerDTO2.getName());
        this.matchScore = matchScore;
    }
}

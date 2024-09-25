package com.will.dto;

import com.will.service.Point;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchScore {
    private PlayerScore player1;
    private PlayerScore player2;
    private PlayerScore winner;

    public MatchScore(PlayerScore player1, PlayerScore player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void refreshGameScores() {
        player1.setPoint(Point.LOVE);
        player2.setPoint(Point.LOVE);
    }

    public void refreshSetScores() {
        player1.setSetScore(0);
        player2.setSetScore(0);
    }
}

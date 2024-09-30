package com.will.service;

import com.will.dto.MatchScoreModel;
import com.will.service.calculation.State;

import java.util.Objects;

public class MatchScoreCalculationService {

    public State updateScore(MatchScoreModel matchScoreModel, Long scorerId) {
        int playerIndex = getPlayerIndex(matchScoreModel, scorerId);
        return matchScoreModel.getMatchScore().pointWon(playerIndex);
    }

    // Player 1 is always taking index --> 0, player 2 --> 1
    public int getPlayerIndex(MatchScoreModel matchScoreModel, Long scorerId) {
        return Objects.equals(scorerId, matchScoreModel.getPlayer1().getId()) ? 0 : 1;
    }
}

package com.will.service;

import com.will.service.calculation.MatchScore;
import com.will.service.calculation.State;
import com.will.dto.MatchScoreModel;

public class MatchScoreCalculationService {

    public State updateScore(MatchScoreModel matchScoreModel, Long scorerId) {

        State state;
        MatchScore matchScore = matchScoreModel.getMatchScore();

        // Player whose id is LESS - is taking playerNumber = 0
        // Player whose id is MORE - is taking playerNumber = 1
        if (scorerId < matchScoreModel.getPlayer1().getId() ||
                scorerId < matchScoreModel.getPlayer2().getId()) {
            state = matchScore.pointWon(0);
        } else {
            state = matchScore.pointWon(1);
        }
        return state;
    }
}

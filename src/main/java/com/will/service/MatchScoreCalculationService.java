package com.will.service;

import com.will.service.calculation.MatchScore;
import com.will.service.calculation.State;
import com.will.dto.MatchScoreModel;

public class MatchScoreCalculationService {


    public State updateScore(MatchScoreModel match, Long scorerId) {

        State state;
        MatchScore matchScore = match.getMatchScore();

        // Player whose id is LESS - is taking playerNumber = 0
        // Player whose id is MORE - is taking playerNumber = 1
        if (scorerId - match.getPlayer1().getId() < 0 ||
                scorerId - match.getPlayer2().getId() < 0) {
            state = matchScore.pointWon(0);
        } else {
            state = matchScore.pointWon(1);
        }

        return state;
    }
}

package com.will.service;

import com.will.dto.MatchScoreModel;
import com.will.model.Match;
import com.will.model.Player;
import com.will.repository.MatchRepository;

public class FinishedMatchesPersistenceService {

    private final MatchRepository repository = new MatchRepository();
    public void save(MatchScoreModel matchScoreModel) {
        Match match = buildMatch(matchScoreModel);
        repository.save(match);
    }

    private Match buildMatch(MatchScoreModel matchScoreModel) {
        return new Match(
                new Player(matchScoreModel.getPlayer1().getId(), matchScoreModel.getPlayer1().getName()),
                new Player(matchScoreModel.getPlayer2().getId(), matchScoreModel.getPlayer2().getName()),
                new Player(matchScoreModel.getWinner().getId(), matchScoreModel.getWinner().getName())
        );
    }
}

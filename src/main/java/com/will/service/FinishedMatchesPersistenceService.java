package com.will.service;

import com.will.dto.MatchScore;
import com.will.model.Match;
import com.will.model.Player;
import com.will.repository.MatchRepository;

public class FinishedMatchesPersistenceService {

    private final MatchRepository repository = new MatchRepository();
    public void save(MatchScore matchScore) {
        Match match = buildMatch(matchScore);
        repository.save(match);
    }

    private Match buildMatch(MatchScore matchScore) {
        Player player1 = new Player(matchScore.getPlayer1().getId());
        Player player2 = new Player(matchScore.getPlayer2().getId());
        Player winner = new Player(matchScore.getWinner().getId());

        return new Match(player1, player2, winner);
    }
}

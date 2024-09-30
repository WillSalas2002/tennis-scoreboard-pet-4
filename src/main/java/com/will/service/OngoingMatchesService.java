package com.will.service;

import com.will.dto.MatchScoreModel;
import com.will.dto.MatchScoreView;
import com.will.repository.OngoingMatchesRepository;

import java.util.UUID;

public class OngoingMatchesService {

    public void save(UUID uuid, MatchScoreModel matchScoreModel) {
        OngoingMatchesRepository.addMatch(uuid, matchScoreModel);
    }

    public MatchScoreModel findMatch(String uuidStr) {
        return OngoingMatchesRepository.findMatch(uuidStr);
    }

    public MatchScoreView findMatchView(String uuidStr) {

        MatchScoreModel matchScoreModel = OngoingMatchesRepository.findMatch(uuidStr);
        return convertToMatchScoreView(matchScoreModel);
    }

    public void delete(UUID uuid) {
        OngoingMatchesRepository.removeMatch(uuid);
    }

    private MatchScoreView convertToMatchScoreView(MatchScoreModel matchScoreModel) {
        return MatchScoreView.builder()
                .player1Id(matchScoreModel.getPlayer1().getId())
                .player2Id(matchScoreModel.getPlayer2().getId())
                .player1Name(matchScoreModel.getPlayer1().getName())
                .player2Name(matchScoreModel.getPlayer2().getName())
                .player1GameScore(matchScoreModel.getMatchScore().getCurrentGameScore(0))
                .player2GameScore(matchScoreModel.getMatchScore().getCurrentGameScore(1))
                .player1SetScore(matchScoreModel.getMatchScore().getCurrentSet().getPlayerScore(0).toString())
                .player2SetScore(matchScoreModel.getMatchScore().getCurrentSet().getPlayerScore(1).toString())
                .player1MatchScore(matchScoreModel.getMatchScore().getPlayerScore(0).toString())
                .player2MatchScore(matchScoreModel.getMatchScore().getPlayerScore(1).toString())
                .gameState(matchScoreModel.getMatchScore().getCurrentSet().getCurrentGame().getGameState().toString())
                .build();
    }
}

package com.will.service;

import com.will.dto.MatchScore;
import com.will.dto.PlayerScore;

import java.util.Objects;

public class MatchScoreCalculationService {

    private boolean isGameFinished;

    public void updateScore(MatchScore match, String scorerName) {

        PlayerScore scorer = Objects.equals(match.getPlayer1().getName(), scorerName)
                ? match.getPlayer1() : match.getPlayer2();

        PlayerScore opposite = scorer.equals(match.getPlayer2())
                ? match.getPlayer1() : match.getPlayer2();

        // 40 VS AD
        if (opposite.getPoint().equals(Point.ADVANTAGE)) {
            opposite.setPoint(Point.FORTY);
        }

        // AD VS 40
        else if (scorer.getPoint().equals(Point.ADVANTAGE)) {
            match.refreshGameScores();

            updateSetScore(match, scorer, opposite);
        }

        // 40 VS 00, 15, 30
        else if (scorer.getPoint().equals(Point.FORTY) &&
                opposite.getPoint().ordinal() < Point.FORTY.ordinal()) {
            match.refreshGameScores();

            updateSetScore(match, scorer, opposite);
        }

        // 00, 15, 30 VS XX
        else if (scorer.getPoint().ordinal() < Point.FORTY.ordinal()) {
            scorer.setPoint(scorer.getPoint().next());
        }

        // default
        else {
            scorer.setPoint(scorer.getPoint().next());
        }
    }

    private void updateSetScore(MatchScore match, PlayerScore scorer, PlayerScore opposite) {
        // SET: 6 VS 6
        if (scorer.getSetScore() == 6 && opposite.getSetScore() == 6) {
            // TODO: need to play tiebreak;

            if (scorer.getMatchScore() == 2) {
                isGameFinished = true;
            }
        }
        // SET: 6 VS 5 or more
        else if (scorer.getSetScore() == 5 && opposite.getSetScore() == 5) {
            scorer.setSetScore(scorer.getSetScore() + 1);
        }

        // SET: 5 VS less than 5
        else if (scorer.getSetScore() == 5 && opposite.getSetScore() <= 4) {
            scorer.setMatchScore(scorer.getMatchScore() + 1);
            match.refreshSetScores();

            if (scorer.getMatchScore() == 2) {
                isGameFinished = true;
            }
        }
        // SET: default
        else {
            scorer.setSetScore(scorer.getSetScore() + 1);
        }
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }
}

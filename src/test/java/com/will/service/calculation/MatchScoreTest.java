package com.will.service.calculation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class MatchScoreTest {

    private final int player1 = 0;
    private final int player2 = 0;
    private MatchScore matchScore;

    @BeforeEach
    public void initScore() {
        matchScore = new MatchScore(2);
    }

    @Test
    public void givenNewMatch_whenGameStart_thenMatchScoresShouldBeZero() {
        assertEquals(0, matchScore.getPlayerScore(player1));
        assertEquals(0, matchScore.getPlayerScore(player2));
    }

    @ParameterizedTest
    @ValueSource(ints = {player1, player2})
    public void givenPlayer_whenWinsTwoMatches_thenPlayerShouldWin(int player) {
        // given
        setMatchScoreOnePointApartFromWin(player);

        // when
        State state = matchScore.pointWon(player);

        // then
        assertEquals(player == player1 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON, state);
    }

    private void setMatchScoreOnePointApartFromWin(int player) {
        for (int i = 0; i < 47; i++) {
            matchScore.pointWon(player);
        }
    }
}
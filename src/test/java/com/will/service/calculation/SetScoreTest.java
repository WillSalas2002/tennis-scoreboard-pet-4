package com.will.service.calculation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SetScoreTest {

    private final int player1 = 0;
    private final int player2 = 1;
    private SetScore setScore;
    private State state;

    @BeforeEach
    public void initPoint() {
        setScore = new SetScore();
    }

    @Test
    public void givenNewSetScore_whenGameStarts_thenPlayerScoresShouldBeZero() {
        assertEquals(0, setScore.getPlayerScore(player1));
        assertEquals(0, setScore.getPlayerScore(player2));
    }

    @ParameterizedTest
    @ValueSource(ints = {player1, player2})
    public void givenPlayerOne_whenWinsAllPoints_thenReturn_PLAYER_ONE_WON(int player) {

        int opposite = player == player1 ? player2 : player1;
        winSet(player, 4);
        assertEquals(1, setScore.getPlayerScore(player));
        assertEquals(0, setScore.getPlayerScore(opposite));
        assertEquals(State.ONGOING, state);

        winSet(player, 4);
        assertEquals(State.ONGOING, state);
        assertEquals(2, setScore.getPlayerScore(player));
        assertEquals(0, setScore.getPlayerScore(opposite));

        winSet(player, 4);
        assertEquals(State.ONGOING, state);
        assertEquals(3, setScore.getPlayerScore(player));
        assertEquals(0, setScore.getPlayerScore(opposite));

        winSet(player, 4);
        assertEquals(State.ONGOING, state);
        assertEquals(4, setScore.getPlayerScore(player));
        assertEquals(0, setScore.getPlayerScore(opposite));

        winSet(player, 4);
        assertEquals(State.ONGOING, state);
        assertEquals(5, setScore.getPlayerScore(player));
        assertEquals(0, setScore.getPlayerScore(opposite));

        winSet(player, 4);
        assertEquals(player == player1 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON, state);
        assertEquals(6, setScore.getPlayerScore(player));
        assertEquals(0, setScore.getPlayerScore(opposite));
    }

    @Test
    public void givenTieBreak_whenWinsSixthSet_SetShouldContinue() {
        setScoreToTieBreak();
        GameState gameState = setScore.getCurrentGame().getGameState();

        assertEquals(GameState.TIEBREAK, gameState);
        assertEquals(State.ONGOING, state);
        assertEquals(6, setScore.getPlayerScore(player1));
        assertEquals(6, setScore.getPlayerScore(player2));
    }

    @Test
    public void givenTieBreak_whenPlayerWinsSevenPoints_thenPlayerShouldWinSet() {
        setScoreToTieBreak();

        winSet(player1, 7);

        assertEquals(State.PLAYER_ONE_WON, state);
    }

    private void setScoreToTieBreak() {
        winSet(player1, 20); // 5:0
        winSet(player2, 20); // 5:5

        winSet(player1, 4); // 6:5
        winSet(player2, 4); // 6:6
    }

    private void winSet(int player, int gameAmountForWin) {
        for (int i = 0; i < gameAmountForWin; i++) {
            state = setScore.pointWon(player);
        }
    }
}

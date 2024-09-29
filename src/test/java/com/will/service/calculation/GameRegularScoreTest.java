package com.will.service.calculation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class GameRegularScoreTest {

    private GameRegularScore gameRegularScore;
    private final int player1 = 0;
    private final int player2 = 1;

    @BeforeEach
    public void initPoint() {
        gameRegularScore = new GameRegularScore();
    }

    @Test
    public void givenNewGameRegularScore_whenGameStart_thenScoresShouldBe_ZERO() {
        assertEquals(GameRegularPlayerPoints.ZERO, gameRegularScore.getPlayerScore(player1));
        assertEquals(GameRegularPlayerPoints.ZERO, gameRegularScore.getPlayerScore(player2));
    }

    @ParameterizedTest
    @ValueSource(ints = {player1, player2})
    public void givenPlayer_whenWinsFourPoints_thenGameShouldBeWon(int player) {
        State state = gameRegularScore.pointWon(player);
        assertEquals(State.ONGOING, state);
        assertEquals(GameRegularPlayerPoints.FIFTEEN, gameRegularScore.getPlayerScore(player));

        state = gameRegularScore.pointWon(player);
        assertEquals(State.ONGOING, state);
        assertEquals(GameRegularPlayerPoints.THIRTY, gameRegularScore.getPlayerScore(player));

        state = gameRegularScore.pointWon(player);
        assertEquals(State.ONGOING, state);
        assertEquals(GameRegularPlayerPoints.FORTY, gameRegularScore.getPlayerScore(player));

        state = gameRegularScore.pointWon(player);
        assertEquals(player == player1 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON, state);
    }

    @Test
    public void givenDeuce_whenPlayerWinsAdvantage_thenGameContinues() {
        // given deuce
        setScoreToDeuce();

        // when player 1 wins point --> ADVANTAGE
        State state = gameRegularScore.pointWon(player1);
        assertEquals(State.ONGOING, state);
        assertEquals(GameRegularPlayerPoints.ADVANTAGE, gameRegularScore.getPlayerScore(player1));
        assertEquals(GameRegularPlayerPoints.FORTY, gameRegularScore.getPlayerScore(player2));

        // when player 2 wins point --> DEUCE
        state = gameRegularScore.pointWon(player2);
        assertEquals(State.ONGOING, state);
        assertEquals(GameRegularPlayerPoints.FORTY, gameRegularScore.getPlayerScore(player1));
        assertEquals(GameRegularPlayerPoints.FORTY, gameRegularScore.getPlayerScore(player2));
    }

    @Test
    public void givenDeuce_whenPlayerWinsTwoAdvantagePoints_thenPlayerWinsGame() {
        // given deuce
        setScoreToDeuce();

        // when player 1 wins point twice --> PLAYER_ONE_WON
        State state = gameRegularScore.pointWon(player1); // ADVANTAGE
        assertEquals(State.ONGOING, state);
        state = gameRegularScore.pointWon(player1); // Player 1 wins
        assertEquals(State.PLAYER_ONE_WON, state);
    }

    private void setScoreToDeuce() {
        gameRegularScore.pointWon(player1); // 15:0
        gameRegularScore.pointWon(player1); // 30:0
        gameRegularScore.pointWon(player1); // 40:0
        gameRegularScore.pointWon(player2); // 40:15
        gameRegularScore.pointWon(player2); // 40:30
        gameRegularScore.pointWon(player2); // 40:40 (deuce)
    }
}

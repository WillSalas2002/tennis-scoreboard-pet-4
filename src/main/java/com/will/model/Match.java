package com.will.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Match {
    private Long id;
    private Player player1;
    private Player player2;
    private Player winner;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Match(Player player1, Player player2, Player winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }
}

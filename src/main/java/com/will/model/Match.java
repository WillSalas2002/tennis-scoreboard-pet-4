package com.will.model;

import lombok.*;

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

    public Match(Player player1, Player player2, Player winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }
}

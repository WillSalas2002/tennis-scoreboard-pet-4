package com.will.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MatchDTO {
    private Long id;
    private String player1Name;
    private String player2Name;
    private String winnerName;
}

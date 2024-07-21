package com.will.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Player {
    private Long id;
    private String name;

    public Player(String name) {
        this.name = name;
    }
}

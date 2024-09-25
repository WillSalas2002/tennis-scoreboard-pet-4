package com.will.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Player {
    private Long id;
    private String name;

    public Player(Long id) {
        this.id = id;
    }

    public Player(String name) {
        this.name = name;
    }
}

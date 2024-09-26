package com.will.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlayerDTO {
    private Long id;
    private String name;
    private int gameScore;
    private int setScore;
    private int matchScore;

    public PlayerDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

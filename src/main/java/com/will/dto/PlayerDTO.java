package com.will.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {
    private Long id;
    private String name;
    private int gameScore;
    private int setScore;
    private int matchScore;
}

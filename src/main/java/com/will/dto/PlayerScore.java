package com.will.dto;

import com.will.service.Point;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerScore {
    private String name;
    private Point point;
    private int setScore;
    private int matchScore;

    public PlayerScore(String name) {
        this.name = name;
        this.point = Point.LOVE;
    }
}

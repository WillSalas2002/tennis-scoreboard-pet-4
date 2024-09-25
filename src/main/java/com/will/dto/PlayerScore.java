package com.will.dto;

import com.will.service.Point;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerScore {
    private Long id;
    private Point point;
    private int setScore;
    private int matchScore;

    public PlayerScore(Long id) {
        this.id = id;
        this.point = Point.LOVE;
    }
}

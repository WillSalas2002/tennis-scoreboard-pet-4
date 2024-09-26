package com.will.service.calculation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GameScore<T> extends Score<T> {
    protected GameState gameState = GameState.REGULAR;
}

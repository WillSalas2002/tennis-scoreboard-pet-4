package com.will.service;

import com.will.model.Player;
import com.will.repository.PlayerRepository;

import java.util.Optional;

public class PlayerService {
    private final PlayerRepository repository = new PlayerRepository();

    public Optional<Player> findByName(String name) {
        return repository.findByPlayerName(new Player(name));
    }

    public Player save(String name) {
        return repository.save(new Player(name));
    }

    public Player findOrSave(String name) {
        return findByName(name)
                .orElseGet(() -> save(name));
    }
}

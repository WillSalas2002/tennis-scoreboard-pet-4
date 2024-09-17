package com.will.service;

import com.will.entity.PlayerScore;
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

    public PlayerScore findOrSave(String name) {
        Player player;
        Optional<Player> playerOptional = findByName(name);

        if (playerOptional.isEmpty()) {
            player = save(name);
        } else {
            player = playerOptional.get();
        }
        return convertToPlayerScore(player);
    }

    private PlayerScore convertToPlayerScore(Player player) {
        return new PlayerScore(player.getId(), player.getName());
    }
}

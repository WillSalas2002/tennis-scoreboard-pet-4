package com.will.service;

import com.will.dto.PlayerDTO;
import com.will.model.Player;
import com.will.repository.PlayerRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public class PlayerService {
    private final PlayerRepository playerRepository = new PlayerRepository();

    public PlayerDTO findByName(String name) {
        return playerRepository.findByName(name)
                .map(this::convertToPlayerDTO)
                .orElseThrow(NoSuchElementException::new);
    }

    public PlayerDTO save(String name) {
        Player player = new Player(name);
        return convertToPlayerDTO(playerRepository.save(player));
    }

    public PlayerDTO findOrSave(String name) {
        Optional<Player> playerOptional = playerRepository.findByName(name);
        return playerOptional
                .map(this::convertToPlayerDTO)
                .orElseGet(() -> save(name));
    }

    private PlayerDTO convertToPlayerDTO(Player player) {
        return new PlayerDTO(player.getId(), player.getName());
    }
}

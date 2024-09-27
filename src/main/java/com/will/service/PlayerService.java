package com.will.service;

import com.will.dto.PlayerDTO;
import com.will.mapper.PlayerMapper;
import com.will.model.Player;
import com.will.repository.PlayerRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public class PlayerService {
    private final PlayerRepository playerRepository = new PlayerRepository();
    private final PlayerMapper playerMapper = PlayerMapper.INSTANCE;

    public PlayerDTO findByName(String name) {
        return playerRepository.findByName(name)
                .map(playerMapper::convertPlayerToPlayerDTO)
                .orElseThrow(NoSuchElementException::new);
    }

    public PlayerDTO save(String name) {
        Player player = new Player(name);
        return playerMapper.convertPlayerToPlayerDTO(playerRepository.save(player));
    }

    public PlayerDTO findOrSave(String name) {
        Optional<Player> playerOptional = playerRepository.findByName(name);
        return playerOptional
                .map(playerMapper::convertPlayerToPlayerDTO)
                .orElseGet(() -> save(name));
    }
}

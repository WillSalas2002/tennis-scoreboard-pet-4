package com.will.mapper;

import com.will.dto.PlayerDTO;
import com.will.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    PlayerDTO convertPlayerToPlayerDTO(Player player);
}

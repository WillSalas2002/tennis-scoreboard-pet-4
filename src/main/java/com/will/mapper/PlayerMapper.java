package com.will.mapper;

import com.will.dto.PlayerDTO;
import com.will.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    @Mapping(target = "gameScore", ignore = true)
    @Mapping(target = "setScore", ignore = true)
    @Mapping(target = "matchScore", ignore = true)
    PlayerDTO convertPlayerToPlayerDTO(Player player);

}

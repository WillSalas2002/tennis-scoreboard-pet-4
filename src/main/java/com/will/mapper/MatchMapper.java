package com.will.mapper;

import com.will.dto.MatchDTO;
import com.will.model.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "player1.name", target = "player1Name")
    @Mapping(source = "player2.name", target = "player2Name")
    @Mapping(source = "winner.name", target = "winnerName")
    MatchDTO convertMatchToMatchDTO(Match match);

}

package com.will.service;

import com.will.dto.MatchDTO;
import com.will.model.Match;
import com.will.repository.MatchRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MatchService {
    private final int RECORDS_PER_PAGE = 10;
    private final int DEFAULT_PAGE = 1;
    private final MatchRepository matchRepository = new MatchRepository();

    public List<MatchDTO> findAll(String pageStr, String filter) {
        int page = DEFAULT_PAGE;
        if (pageStr != null && pageStr.matches("^\\d+$")) {
            page = Integer.parseInt(pageStr);
        }
        List<Match> matches = matchRepository.findAll(RECORDS_PER_PAGE, (page - 1) * RECORDS_PER_PAGE, filter);

        return matches.stream()
                .map(this::convertToMatchDTO)
                .collect(Collectors.toList());
    }

    public int getPageCount() {
        int totalMatchesCount = matchRepository.getTotalMatchesCount();
        int division = totalMatchesCount / RECORDS_PER_PAGE;

        return totalMatchesCount % RECORDS_PER_PAGE == 0 ?
                division : division + 1;
    }

    private MatchDTO convertToMatchDTO(Match match) {
        return new MatchDTO(
                match.getId(),
                match.getPlayer1().getName(),
                match.getPlayer2().getName(),
                match.getWinner().getName()
        );
    }
}

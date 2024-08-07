package com.will.service;

import com.will.dto.MatchDTO;
import com.will.model.Match;
import com.will.repository.MatchRepository;

import java.util.ArrayList;
import java.util.List;

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
        return convertToMatchDTO(matches);
    }

    public int getPageCount() {

        int totalRowCount = matchRepository.getTotalRowCount();
        int division = totalRowCount / RECORDS_PER_PAGE;

        return totalRowCount % RECORDS_PER_PAGE == 0 ?
                division : division + 1;
    }

    private List<MatchDTO> convertToMatchDTO(List<Match> matches) {
        List<MatchDTO> matchDTOs = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            matchDTOs.add(new MatchDTO(
                    i + 1,
                    match.getPlayer1().getName(),
                    match.getPlayer2().getName(),
                    match.getWinner().getName()
            ));
        }
        return matchDTOs;
    }
}

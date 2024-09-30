package com.will.controller;

import com.will.dto.MatchScoreModel;
import com.will.dto.MatchScoreView;
import com.will.service.FinishedMatchesPersistenceService;
import com.will.service.MatchScoreCalculationService;
import com.will.service.OngoingMatchesService;
import com.will.service.calculation.State;
import com.will.util.PathFinder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/matchScore")
public class MatchScoreServlet extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private final MatchScoreCalculationService calculationService = new MatchScoreCalculationService();
    private final FinishedMatchesPersistenceService persistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        MatchScoreView matchScoreView = ongoingMatchesService.findMatchView(uuid);

        req.setAttribute("matchScoreView", matchScoreView);
        req.setAttribute("uuid", uuid);
        req.getRequestDispatcher(PathFinder.find("ongoing-match")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidStr = req.getParameter("uuid");
        String scorerIdStr = req.getParameter("scorerId");
        MatchScoreModel matchScoreModel = ongoingMatchesService.findMatch(uuidStr);

        State state = calculationService.updateScore(matchScoreModel, Long.valueOf(scorerIdStr));

        if (!state.equals(State.ONGOING)) {
            if (state.equals(State.PLAYER_ONE_WON)) {
                matchScoreModel.setWinner(matchScoreModel.getPlayer1());
            } else {
                matchScoreModel.setWinner(matchScoreModel.getPlayer2());
            }
            persistenceService.save(matchScoreModel);
            ongoingMatchesService.delete(UUID.fromString(uuidStr));
            // TODO: need redirect to the page where final score will be displayed
            resp.sendRedirect(PathFinder.find("home"));
            return;
        }
        doGet(req, resp);
    }
}

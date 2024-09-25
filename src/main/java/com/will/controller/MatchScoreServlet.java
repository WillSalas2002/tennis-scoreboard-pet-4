package com.will.controller;

import com.will.dto.MatchScore;
import com.will.model.Match;
import com.will.service.FinishedMatchesPersistenceService;
import com.will.service.MatchScoreCalculationService;
import com.will.service.OngoingMatchesService;
import com.will.util.PathFinder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/matchScore")
public class MatchScoreServlet extends HttpServlet {
    private final MatchScoreCalculationService calculationService = new MatchScoreCalculationService();
    private final FinishedMatchesPersistenceService persistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        MatchScore matchScore = OngoingMatchesService.getMatch(uuid);

        req.setAttribute("matchScore", matchScore);
        req.setAttribute("uuid", uuid);
        req.getRequestDispatcher(PathFinder.find("ongoing-match")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        String scorerIdStr = req.getParameter("scorerId");
        MatchScore matchScore = OngoingMatchesService.getMatch(uuid);

        calculationService.updateScore(matchScore, scorerIdStr);

        if (calculationService.isGameFinished()) {
            // TODO: persist match to database
            persistenceService.save(matchScore);
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        doGet(req, resp);
    }
}

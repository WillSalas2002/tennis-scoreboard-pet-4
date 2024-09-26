package com.will.controller;

import com.will.dto.MatchScoreModel;
import com.will.dto.PlayerDTO;
import com.will.dto.PlayerScoreModel;
import com.will.service.OngoingMatchesService;
import com.will.service.PlayerService;
import com.will.service.calculation.MatchScore;
import com.will.util.PathFinder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/newMatch")
public class NewMatchServlet extends HttpServlet {
    private final PlayerService playerService = new PlayerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PathFinder.find("new-match")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String player1Name = req.getParameter("player1");
        String player2Name = req.getParameter("player2");

        UUID uuid = UUID.randomUUID();

        PlayerDTO playerDTO1 = playerService.findOrSave(player1Name);
        PlayerDTO playerDTO2 = playerService.findOrSave(player2Name);
        //TODO: make game match count dynamic
        MatchScore matchScore = new MatchScore(2);
        MatchScoreModel matchScoreModel = new MatchScoreModel(playerDTO1, playerDTO2, matchScore);

        OngoingMatchesService.addMatch(uuid, matchScoreModel);

        resp.sendRedirect(req.getContextPath() + "/matchScore?uuid=" + uuid);
    }
}

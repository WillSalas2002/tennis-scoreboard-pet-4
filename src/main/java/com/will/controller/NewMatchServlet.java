package com.will.controller;

import com.will.entity.MatchScore;
import com.will.entity.PlayerScore;
import com.will.service.PlayerService;
import com.will.storage.Storage;
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

        PlayerScore player1 = playerService.findOrSave(player1Name);
        PlayerScore player2 = playerService.findOrSave(player2Name);

        UUID uuid = UUID.randomUUID();
        MatchScore matchScore = new MatchScore(player1, player2);
        Storage.addMatch(uuid, matchScore);

        resp.sendRedirect(req.getContextPath() + "/matchScore?uuid=" + uuid);
    }
}

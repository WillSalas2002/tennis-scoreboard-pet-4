package com.will.controller;

import com.will.entity.MatchScore;
import com.will.service.MatchScoreCalculationService;
import com.will.storage.Storage;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        MatchScore matchScore = Storage.getMatch(uuid);
        req.setAttribute("match", matchScore);

        req.getRequestDispatcher(PathFinder.find("match-score")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        MatchScore matchScore = Storage.getMatch(uuid);

        calculationService.handle(matchScore, 1L);

        doGet(req, resp);
    }
}

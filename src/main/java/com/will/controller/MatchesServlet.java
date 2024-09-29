package com.will.controller;

import com.will.dto.MatchDTO;
import com.will.service.MatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.will.util.PathFinder.find;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    private final MatchService service = new MatchService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        String filter = req.getParameter("filterByName");

        int pageCount = service.getPageCount(filter);
        List<MatchDTO> matches = service.findAll(pageStr, filter);

        req.setAttribute("matches", matches);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("filterByName", filter);

        req.getRequestDispatcher(find("matches")).forward(req, resp);
    }
}

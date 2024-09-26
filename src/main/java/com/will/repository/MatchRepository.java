package com.will.repository;

import com.will.model.Match;
import com.will.model.Player;
import com.will.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchRepository {

    private static final String QUERY_WITH_PAGINATION = """
            SELECT m.id    AS match_id,
                   p1.name AS player1_name,
                   p2.name AS player2_name,
                   w.name  AS winner_name
            FROM match m
                     JOIN player p1 ON m.player1 = p1.id
                     JOIN player p2 ON m.player2 = p2.id
                     JOIN player w  ON m.winner  = w.id
            LIMIT %d OFFSET %d;
            """;
    private static final String QUERY_WITH_FILTER_AND_PAGINATION = """
            SELECT m.id    AS match_id,
                   p1.name AS player1_name,
                   p2.name AS player2_name,
                   w.name  AS winner_name
            FROM match m
                     JOIN player p1 ON m.player1 = p1.id
                     JOIN player p2 ON m.player2 = p2.id
                     JOIN player w ON m.winner = w.id
            WHERE p1.name = '%s' OR p2.name = '%s'
            LIMIT %d OFFSET %d;
            """;
    private static final String QUERY_SAVE = """
            INSERT INTO match (player1, player2, winner)
            VALUES (?,?,?);
            """;

    private static final String QUERY_PAGE_COUNT = "SELECT COUNT(*) AS total FROM match";

    public List<Match> findAll(int limit, int offset, String filter) {

        String sql;
        if (filter != null && !filter.isEmpty()) {
            sql = String.format(QUERY_WITH_FILTER_AND_PAGINATION, filter, filter, limit, offset);
        } else {
            sql = String.format(QUERY_WITH_PAGINATION, limit, offset);
        }

        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            List<Match> matches = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Match match = buildMatch(resultSet);
                matches.add(match);
            }

            return matches;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTotalRowCount() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(QUERY_PAGE_COUNT)) {
            ResultSet resultSet = statement.executeQuery();
            int totalCount = 0;
            if (resultSet.next()) {
                totalCount = resultSet.getInt("total");
            }
            return totalCount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Match match) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(QUERY_SAVE)) {
            statement.setLong(1, match.getPlayer1().getId());
            statement.setLong(2, match.getPlayer2().getId());
            statement.setLong(3, match.getWinner().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Match buildMatch(ResultSet resultSet) throws SQLException {
        return new Match(
                resultSet.getLong("match_id"),
                new Player(resultSet.getString("player1_name")),
                new Player(resultSet.getString("player2_name")),
                new Player(resultSet.getString("winner_name"))
        );
    }
}

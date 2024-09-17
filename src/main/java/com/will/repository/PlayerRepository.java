package com.will.repository;

import com.will.model.Player;
import com.will.util.ConnectionManager;

import java.sql.*;
import java.util.Optional;

public class PlayerRepository {

    private final static String FIND_BY_NAME_SQL = "SELECT * FROM tennis.player WHERE name = ?";
    private final static String SAVE_SQL = "INSERT INTO tennis.player (name) VALUES (?)";

    public Optional<Player> findByPlayerName(Player player) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME_SQL)) {
            statement.setString(1, player.getName());
            ResultSet resultSet = statement.executeQuery();
            Player resultPlayer = null;
            if (resultSet.next()) {
                resultPlayer = new Player(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
            }
            return Optional.ofNullable(resultPlayer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Player save(Player player) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, player.getName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                player.setId(generatedKeys.getLong("id"));
            }
            return player;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

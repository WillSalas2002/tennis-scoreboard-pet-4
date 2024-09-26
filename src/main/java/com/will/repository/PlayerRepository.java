package com.will.repository;

import com.will.model.Player;
import com.will.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class PlayerRepository {

    private final static String FIND_BY_NAME_SQL = "SELECT * FROM player WHERE name = ?";
    private final static String SAVE_SQL = "INSERT INTO player (name) VALUES (?)";

    public Optional<Player> findByPlayerName(String name) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME_SQL)) {
            statement.setString(1, name);
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

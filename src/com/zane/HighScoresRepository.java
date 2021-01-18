package com.zane;

import com.zane.models.HighScore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HighScoresRepository {
    private static final String JDBC_URL = "jdbc:sqlite:fox.db";
    private Connection connection;

    public HighScoresRepository() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS high_scores (" +
                    "name TEXT," +
                    "score INTEGER" +
                    ")";
            statement.execute(sql);
        } catch (SQLException throwable) {
            System.out.println("Error connecting to SQLite database!");
            throwable.printStackTrace();
        }
    }

    public List<HighScore> getHighScores() {
        List<HighScore> highScores = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT name, score FROM high_scores ORDER BY score DESC";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                int score = rs.getInt("score");
                HighScore highScore = new HighScore(name, score);
                highScores.add(highScore);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return highScores;
    }

    public void putHighScore(String name, int score) {
        try {
            String sql = "INSERT INTO high_scores(name, score) VALUES(?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, score);
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}

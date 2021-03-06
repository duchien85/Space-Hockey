package com.mygdx.airhockey.statistics;

import com.mygdx.airhockey.database.DatabaseController;

import java.util.Objects;

public class Player {
    private float points;
    private String username;

    /**
     * Constructor for player.
     * @param username for the player.
     * @param points the player has.
     */
    public Player(String username, float points) {
        this.username = username;
        this.points = points;
    }

    /**
     * Getter for points.
     * @return points of the user.
     */
    public float getPoints() {
        return points;
    }

    /**
     * Setter for points.
     * @param points value to set.
     */
    public void setPoints(float points) {
        this.points = points;
    }

    /**
     * Getter for username.
     * @return username of the player.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username.
     * @param username value to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }


    //    /**
    //     * Updates user's points.
    //     * @param amount to add to current score.
    //     */
    //    public void updatePoints(double amount) {
    //        points += amount;
    //    }

    /**
     * Adds the score to the database.
     * @param database to update.
     * @param chosenName name the player chose for the leaderboard.
     */
    public void addDatabaseScore(DatabaseController database, String chosenName) {
        database.addScore(username, points, chosenName);
    }


    @Override
    public String toString() {
        return "Player{"
                + "Username='" + username
                + "', Points='" + points + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        Player player = (Player) o;
        return points == player.points
                && Objects.equals(username, player.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points, username);
    }

}

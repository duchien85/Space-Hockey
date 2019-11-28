package Game;

import database.DBController;

public class Player {
    private int points;
    private String username;

    /**
     * Constructor for player.
     * @param database
     * @param username
     */
    public Player(String username, int points) {
        this.username = username;
        this.points = points;
    }

    /**
     * Getter for points.
     * @return points of the user.
     */
    public double getPoints() {
        return points;
    }

    /**
     * Setter for points.
     * @param points value to set.
     */
    public void setPoints(int points) {
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

    /**
     * Updates user's points.
     * @param amount to add to current score.
     */
    public void updatePoints(double amount) {
        points += amount;
    }

    /**
     *
     */
    public void updateDBScore(DBController database) {
        database.updateScore(username, points);
    }
}

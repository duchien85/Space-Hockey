package database;

import game.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class DatabaseController {
    private Connection connection;

    /**
     * Constructor for database controller.
     * @param connection with the database.
     */
    public DatabaseController(Connection connection) {
        this.connection = connection;
    }

    /**
     * Checks if a user exists in the database.
     * @param username of the user.
     * @return true if exists, else false.
     */
    public boolean userExists(final String username) {
        try {
            String query = "select Username from User where Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    return resultSet.next();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    resultSet.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Returns hashed password of the user.
     * @param username of the user.
     * @return hashed password of the user.
     */
    public String getHashedPassword(final String username)  {
        assert userExists(username);
        try {
            String query = "select Password from User where Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    resultSet.next();
                    return resultSet.getString("Password");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    resultSet.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * Adds a user to the database.
     * @param username of the user.
     * @param hashedPassword of the user.
     */
    public void createUser(final String username, final String hashedPassword) {
        try {
            String queryUser = "insert into User (Username, Password) VALUES (?,?)";
            String queryScore = "insert into Score (username, score, chosen_name) VALUES (?,?,?)";
            PreparedStatement preparedStatementUser = connection.prepareStatement(queryUser);
            PreparedStatement preparedStatementScore = connection.prepareStatement(queryScore);
            try {
                preparedStatementUser.setString(1, username);
                preparedStatementUser.setString(2, hashedPassword);
                preparedStatementScore.setString(1, username);
                preparedStatementScore.setInt(2, 0);
                preparedStatementScore.setString(3, username);
                if (!userExists(username)) {
                    preparedStatementUser.execute();
                    preparedStatementScore.execute();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatementUser.close();
                preparedStatementScore.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets score of a user from the database.
     * @param username of the user.
     * @return the score of the user.
     */
    public int getScore(String username) {
        assert userExists(username);
        try {
            String query = "select score from Score where Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    resultSet.next();
                    return resultSet.getInt("Score");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    resultSet.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Updates the score of a user.
     * @param username of the user.
     */
    public void updateScore(String username, int score) {
        assert userExists(username);
        try {
            String query = "update Score SET score = ? where Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setInt(1,score);
                preparedStatement.setString(2, username);
                preparedStatement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets score of all users from the database.
     * @return the list of player objects, containing usernames and scores.
     */
    public List<Player> getAllScores() {
        List<Player> players = new LinkedList<Player>();
        try {
            String query = "select * from Score";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    while (resultSet.next()) {
                        String username = resultSet.getString("username");
                        int score = resultSet.getInt("score");
                        Player player = new Player(username,score);
                        players.add(player);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    resultSet.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    /**
     * Getter for connection.
     * @return connection with the database.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Setter for connection.
     * @param connection value to set.
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
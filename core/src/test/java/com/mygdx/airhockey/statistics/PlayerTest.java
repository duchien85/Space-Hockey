package com.mygdx.airhockey.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.mygdx.airhockey.database.DatabaseController;
import com.mygdx.airhockey.database.tables.Score;
import com.mygdx.airhockey.database.tables.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

class PlayerTest {
    @Mock
    private transient DatabaseController database;

    private transient Player player;

    @BeforeEach
    void setUp() {
        player = new Player("test", 0);
    }

    @Test
    void getPoints() {
        assertEquals(0, player.getPoints());
    }

    @Test
    void setPoints() {
        player.setPoints(100);
        assertEquals(100,player.getPoints());
    }

    @Test
    void getUsername() {
        assertEquals("test", player.getUsername());
    }

    @Test
    void setUsername() {
        player.setUsername("abc");
        assertEquals("abc", player.getUsername());
    }

    @Test
    void updatePoints() {
        player.updatePoints(50);
        assertEquals(50, player.getPoints());
    }

    @Test
    void updateDatabaseScore() {
        database = Mockito.mock(DatabaseController.class);
        player.updateDatabaseScore(database);
        verify(database, times(1)).updateScore(anyString(), anyInt());
    }

    @Test
    void testHashCode() {
        Player x = new Player("aap", 2);  // equals and hashCode check name field value
        Player y = new Player("aap", 2);
        Assertions.assertTrue(x.equals(y) && y.equals(x));
        Assertions.assertTrue(x.hashCode() == y.hashCode());
    }

    @Test
    void testEquals() {
        Player player = new Player("noot", 1);
        Player playerSame = new Player("noot", 1);
        Player playerWrong = new Player("false", 4);
        Assertions.assertFalse(player.equals(playerWrong));
        Assertions.assertTrue(player.equals(playerSame));
        Assertions.assertTrue(player.equals(player));
        Score score = new Score("abc", 4, "def");
        Assertions.assertFalse(player.equals(score));
    }
}
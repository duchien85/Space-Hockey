package com.mygdx.airhockey.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

import com.mygdx.airhockey.database.DatabaseController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AuthenticationTest {
    private static final String EXISTENT_USER = "existent_user";
    private static final String NON_EXISTENT_USER = "non_existent_user";
    private static final String PASSWORD = "password";
    public static final String HASH = new PasswordService("SHA-256").hashPassword(PASSWORD);
    private transient Authentication auth;
    private transient DatabaseController database;


    @BeforeEach
    void setUp() {
        database = Mockito.mock(DatabaseController.class);
        Mockito.when(database.userExists(EXISTENT_USER)).thenReturn(true);
        Mockito.when(database.userExists(NON_EXISTENT_USER)).thenReturn(false);
        Mockito.when(database.getHashedPassword(EXISTENT_USER)).thenReturn(HASH);

        auth = new Authentication(database);
    }

    @Test
    void signIn() {
        auth.signIn(EXISTENT_USER, PASSWORD);
        Mockito.verify(database, times(1)).userExists(EXISTENT_USER);
        Mockito.verify(database, times(1)).getHashedPassword(EXISTENT_USER);

        auth.signIn(NON_EXISTENT_USER, PASSWORD);
        Mockito.verify(database, times(1)).userExists(NON_EXISTENT_USER);
        Mockito.verify(database, times(1)).getHashedPassword(EXISTENT_USER);

        assertFalse(auth.signIn(NON_EXISTENT_USER, PASSWORD));
        assertFalse(auth.signIn(EXISTENT_USER, "wrong_password"));
        assertTrue(auth.signIn(EXISTENT_USER, PASSWORD));
    }

    @Test
    void signUp() {
        assertFalse(auth.signUp(EXISTENT_USER, PASSWORD));
        Mockito.verify(database, times(1)).userExists(EXISTENT_USER);

        assertTrue(auth.signUp(NON_EXISTENT_USER, PASSWORD));
        Mockito.verify(database, times(1)).userExists(NON_EXISTENT_USER);


    }
}
package com.mygdx.airhockey.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordService {
    private transient MessageDigest md;

    /**
     * Constructor for password service.
     * @param algorithmCode code we use (e.g. "SHA-256").
     */
    public PasswordService(String algorithmCode) {
        try {
            md = MessageDigest.getInstance(algorithmCode);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a hash of a plain-text password.
     * @param pwd password to hash.
     * @return hash of the password.
     */
    public String hashPassword(String pwd)  {
        byte[] hash = md.digest(pwd.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    /**
     * Checks if password is correct by comparing with its hash.
     * @param pwd password to check.
     * @param hash hash of the correct password.
     * @return true if correct, else false.
     */
    public boolean checkPassword(String pwd, String hash) {
        return hashPassword(pwd).equals(hash);
    }
}

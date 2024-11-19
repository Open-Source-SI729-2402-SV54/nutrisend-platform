package com.example.nutrisend.platform.iam.application.internal.outboundservices.tokens;

public interface TokenService {

    /**
     * Generate a token for a given username
     * @param username the username
     * @return String the token
     */
    String generateToken(String username);

    /**
     * Extract the username from a token
     * @param token the token
     * @return String the username
     */
    String getEmailFromToken(String token);

    /**
     * Validate a token
     * @param token the token
     * @return boolean true if the token is valid, false otherwise
     */
    boolean validateToken(String token);
}

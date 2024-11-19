package com.example.nutrisend.platform.iam.infrastructure.hashing.bcrypt.service;

import com.example.nutrisend.platform.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HashingServiceImpl implements BCryptHashingService {

    private final BCryptPasswordEncoder passwordEncoder;

    public HashingServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Encode a password
     *
     * @param rawPassword the password to encode
     * @return String the encoded password
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Match a raw password with an encoded password
     *
     * @param rawPassword     the raw password
     * @param encodedPassword the encoded password
     * @return boolean true if the raa password matches the encoded password, false otherwise
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}

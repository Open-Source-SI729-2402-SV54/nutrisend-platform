package com.example.nutrisend.platform.iam.infrastructure.tokens.jwt;

import com.example.nutrisend.platform.iam.application.internal.outboundservices.tokens.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

public interface BearerTokenService extends TokenService {

    String getBearerTokenFrom(HttpServletRequest token);

    String generateToken(Authentication authentication);
}

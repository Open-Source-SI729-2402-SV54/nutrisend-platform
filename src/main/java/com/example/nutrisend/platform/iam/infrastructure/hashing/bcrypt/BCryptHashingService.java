package com.example.nutrisend.platform.iam.infrastructure.hashing.bcrypt;

import com.example.nutrisend.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;


public interface BCryptHashingService extends HashingService, PasswordEncoder {}


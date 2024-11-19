package com.example.nutrisend.platform.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(String name,
                             String surname,
                             String email,
                             String password,
                             String phone,
                             List<String> roles) {
}

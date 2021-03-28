package by.mark.oauth2.resourceserver.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TokenController.PATH)
public class TokenController {

    public static final String PATH = "/api/v1/token";

    @GetMapping
    public Jwt getToken(@AuthenticationPrincipal Jwt jwt) {
        return jwt;
    }
}
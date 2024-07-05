package com.tyba.backend.enginner.api.controller;

import com.tyba.backend.enginner.api.services.security.TokenRevocationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/logout")
public class LogoutController {

    @Autowired
    private TokenRevocationService tokenRevocationService;

    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @PostMapping
    @Transactional
    @Operation(summary = "Logout", tags = {"Logout Controller", "POST"})
    public ResponseEntity<Void> logout(
            Authentication authentication,
            HttpServletRequest request,
            HttpServletResponse response,
            UriComponentsBuilder uriComponentsBuilder
    )
    {
        String token = request.getHeader("Authorization").substring(7);
        if(authentication != null){
            logoutHandler.logout(request, response, authentication);
            tokenRevocationService.revokeToken(token);
        }
        return ResponseEntity.noContent().build();
    }
}

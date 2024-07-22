package com.arianit.cityguidebe.controller;

import com.arianit.cityguidebe.dto.AuthenticationResponse;
import com.arianit.cityguidebe.dto.CurrentLoggedInUserDto;
import com.arianit.cityguidebe.dto.request.AuthenticationRequest;
import com.arianit.cityguidebe.dto.request.RefreshTokenRequest;
import com.arianit.cityguidebe.dto.request.RegisterRequest;
import com.arianit.cityguidebe.service.AuthenticationService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @NotNull AuthenticationRequest request){
        return new ResponseEntity<>(authenticationService.login(request), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody @NotNull RefreshTokenRequest refreshTokenRequest) {
        return new ResponseEntity<>(authenticationService.refreshToken(refreshTokenRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public void register(@RequestBody @NotNull RegisterRequest request){
        authenticationService.register(request);
    }

    @GetMapping("/auth-me")
    public CurrentLoggedInUserDto getLoggedInUser(){
        return authenticationService.getLoggedInUser();
    }
}

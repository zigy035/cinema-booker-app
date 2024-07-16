package com.cinema.booker.controller;

import com.cinema.booker.model.AuthUser;
import com.cinema.booker.service.AuthUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authusers")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserService authUserService;

    @GetMapping
    public Page<AuthUser> getAllAuthUsers() {
        return authUserService.getAllAuthUsers();
    }

    @PostMapping
    public AuthUser create(@Valid @RequestBody AuthUser authUser) {
        return authUserService.create(authUser);
    }

}

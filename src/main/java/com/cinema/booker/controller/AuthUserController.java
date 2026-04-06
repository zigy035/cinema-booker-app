package com.cinema.booker.controller;

import com.cinema.booker.model.AuthUser;
import com.cinema.booker.service.AuthUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Auth Users", description = "Endpoints for managing authenticated users")
public class AuthUserController {

    private final AuthUserService authUserService;

    @GetMapping
    @Operation(summary = "Get all users", description = "Returns a paginated list of all registered users")
    public Page<AuthUser> getAllAuthUsers() {
        return authUserService.getAllAuthUsers();
    }

    @PostMapping
    @Operation(summary = "Create user", description = "Registers a new user")
    public AuthUser create(@Valid @RequestBody AuthUser authUser) {
        return authUserService.create(authUser);
    }

}

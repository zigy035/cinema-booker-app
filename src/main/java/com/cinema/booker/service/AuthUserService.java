package com.cinema.booker.service;

import com.cinema.booker.exception.EmailAlreadyExistsException;
import com.cinema.booker.model.AuthUser;
import com.cinema.booker.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthUserService {

    private final AuthUserRepository authUserRepository;

    public Page<AuthUser> getAllAuthUsers() {
        return authUserRepository.findAll(Pageable.unpaged());
    }

    @Transactional
    public AuthUser create(AuthUser authUser) {
        authUserRepository.findAuthUserByEmail(authUser.getEmail())
                .ifPresent(user -> {
                    throw new EmailAlreadyExistsException(user.getEmail());
                });
        return authUserRepository.save(authUser);
    }
}

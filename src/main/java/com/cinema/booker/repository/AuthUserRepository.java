package com.cinema.booker.repository;

import com.cinema.booker.model.AuthUser;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthUserRepository extends PagingAndSortingRepository<AuthUser, Long> {

    Optional<AuthUser> findAuthUserByEmail(String email);
}

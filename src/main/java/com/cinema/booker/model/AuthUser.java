package com.cinema.booker.model;

import com.cinema.booker.config.EnumValue;
import com.cinema.booker.enumeration.AuthRole;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Table(name = "auth_user")
public class AuthUser {

    @Id
    private long id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 100, message = "First name must be between 2 and 100 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    @Email(message = "Invalid email value")
    private String email;

    @NotBlank(message = "Username is required")
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 10, max = 255, message = "Password must be between 10 and 255 characters")
    private String password;

    @NotNull
    @EnumValue(enumClass = AuthRole.class, message = "Invalid access role - allowed values: [USER, ADMIN]")
    private String accessRole;
}

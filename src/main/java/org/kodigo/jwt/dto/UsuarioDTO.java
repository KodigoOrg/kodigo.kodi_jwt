package org.kodigo.jwt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
        @Email @NotNull @Size(max = 120) String email,
        @NotNull @Size(max = 120) String nombre,
        @NotNull @Size(min = 6, max = 255) String password
) {}

package org.kodigo.jwt.dto;

public record AuthResponseDTO(
        String token,
        String email,
        String nombre
) {}

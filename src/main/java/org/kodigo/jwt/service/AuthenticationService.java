package org.kodigo.jwt.service;

import lombok.RequiredArgsConstructor;
import org.kodigo.jwt.dto.AuthResponseDTO;
import org.kodigo.jwt.dto.LoginDTO;
import org.kodigo.jwt.dto.UsuarioDTO;
import org.kodigo.jwt.entity.Usuario;
import org.kodigo.jwt.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO register(UsuarioDTO request) {
        // Verificar si el email ya existe
        if (repository.findByEmail(request.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya está registrado");
        }

        var user = new Usuario();
        user.setEmail(request.email());
        user.setNombre(request.nombre());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Usuario.Role.USER);
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponseDTO(jwtToken, user.getEmail(), user.getNombre());
    }

    public AuthResponseDTO authenticate(LoginDTO request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }

        var user = repository.findByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponseDTO(jwtToken, user.getEmail(), user.getNombre());
    }
}

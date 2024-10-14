package br.com.fiap.cp2.controller;

import br.com.fiap.cp2.dto.AuthDTO;
import br.com.fiap.cp2.dto.LoginResponse;
import br.com.fiap.cp2.dto.RegisterDTO;
import br.com.fiap.cp2.model.Diploma;
import br.com.fiap.cp2.model.Usuario;
import br.com.fiap.cp2.repository.DiplomaRepository;
import br.com.fiap.cp2.repository.UsuarioRepository;
import br.com.fiap.cp2.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DiplomaRepository diplomaRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO authDTO) {
        var rgSenha = new UsernamePasswordAuthenticationToken(authDTO.rg(), authDTO.senha());
        var auth = this.authenticationManager.authenticate(rgSenha);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        List<Diploma> diplomas = diplomaRepository.findByDiplomadoRg(authDTO.rg());
        List<UUID> diplomaUuids = diplomas.stream()
                .map(Diploma::getId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new LoginResponse(token, diplomaUuids));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (usuarioRepository.findByRg(registerDTO.rg()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.senha());
        Usuario novoUsurio = new Usuario(registerDTO.rg(), encryptedPassword, registerDTO.role());
        usuarioRepository.save(novoUsurio);
        return ResponseEntity.ok().build();
    }
}


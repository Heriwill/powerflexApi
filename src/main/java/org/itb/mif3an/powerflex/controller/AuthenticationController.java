package org.itb.mif3an.powerflex.controller;

import org.itb.mif3an.powerflex.infra.security.TokenService;
import org.itb.mif3an.powerflex.model.Administrador;
import org.itb.mif3an.powerflex.model.AuthenticationDTO;
import org.itb.mif3an.powerflex.model.LoginResponseDTO;
import org.itb.mif3an.powerflex.model.RegisterDTO;
import org.itb.mif3an.powerflex.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdministradorRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Administrador) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    @PostMapping("/register")
    public  ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Administrador newAdministrador = new Administrador(data.nome(), data.login(), encryptedPassword, data.role());

        this.repository.save(newAdministrador);

        return ResponseEntity.ok().build();
    }
}
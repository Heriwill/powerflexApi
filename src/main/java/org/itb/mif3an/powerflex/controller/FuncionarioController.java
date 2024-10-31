package org.itb.mif3an.powerflex.controller;

import org.itb.mif3an.powerflex.model.Usuario;
import org.itb.mif3an.powerflex.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@RestController                                      // -> Exclusivo para APIS
@RequestMapping("/")
public class FuncionarioController {

    public final UsuarioService usuarioService;

    public FuncionarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping("/usuario")
    @Transactional
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/usuario").toUriString());
        return ResponseEntity.created(uri).body(usuarioService.save(usuario));

    }

    @GetMapping("/usuario")
    public ResponseEntity<List<Usuario>> listarTodosUsuarios() {
        return ResponseEntity.ok().body(usuarioService.findAll());
    }


}
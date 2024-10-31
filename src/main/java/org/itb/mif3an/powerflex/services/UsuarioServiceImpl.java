package org.itb.mif3an.powerflex.services;

import org.itb.mif3an.powerflex.exceptions.BadRequest;
import org.itb.mif3an.powerflex.model.Usuario;
import org.itb.mif3an.powerflex.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario){
        usuario.setCodStatus(true);
        if(!usuario.validarUsuario()){
            throw new BadRequest(usuario.getMensagemErro());
        }
        return usuarioRepository.save((usuario));
    }
}

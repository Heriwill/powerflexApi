package org.itb.mif3an.powerflex.services;

import org.itb.mif3an.powerflex.model.Usuario;
import java.util.List;

public interface UsuarioService {
    public List<Usuario> findAll();
    public Usuario save(Usuario usuario);
}
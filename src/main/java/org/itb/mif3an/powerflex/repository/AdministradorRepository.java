package org.itb.mif3an.powerflex.repository;

import org.itb.mif3an.powerflex.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    UserDetails findByLogin(String login);
}

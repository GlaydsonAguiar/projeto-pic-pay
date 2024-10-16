package com.desafio.picpay.repositories;

import com.desafio.picpay.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findUsuarioByDocumento(String documento);

    Optional<Usuario> findUsuarioById(Long id);
}

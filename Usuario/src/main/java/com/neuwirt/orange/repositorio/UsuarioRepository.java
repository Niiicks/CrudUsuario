package com.neuwirt.orange.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neuwirt.orange.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}

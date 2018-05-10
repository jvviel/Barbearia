package br.com.barbearia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.barbearia.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

	public Optional<Usuario> findByEmail(String email);
}

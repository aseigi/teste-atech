package br.com.atech.teste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.atech.teste.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmailAndNomeUsuario(String email, String nomeusuario);
	
}

package br.com.jowdev.projetosiplataformaempregos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jowdev.projetosiplataformaempregos.models.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findById(Long id);
	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String email);
	Boolean existsByCpf(String cpf);

}


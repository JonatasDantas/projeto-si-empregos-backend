package br.com.jowdev.projetosiplataformaempregos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jowdev.projetosiplataformaempregos.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByName(String name);

}

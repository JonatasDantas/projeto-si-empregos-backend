package br.com.jowdev.projetosiplataformaempregos.repository;

import br.com.jowdev.projetosiplataformaempregos.models.user.UserKnowledge;
import br.com.jowdev.projetosiplataformaempregos.models.user.UserKnowledgeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserKnowledgeRepository extends JpaRepository<UserKnowledge, UserKnowledgeId> {
}

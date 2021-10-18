package br.com.jowdev.projetosiplataformaempregos.repository;

import br.com.jowdev.projetosiplataformaempregos.models.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeRepository extends JpaRepository<Knowledge, Long> {
}

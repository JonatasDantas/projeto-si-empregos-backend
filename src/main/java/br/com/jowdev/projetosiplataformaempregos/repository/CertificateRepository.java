package br.com.jowdev.projetosiplataformaempregos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jowdev.projetosiplataformaempregos.models.user.Certificate;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

}

package jv.avaliacao1.gabriel_felix_faustina_prova1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jv.avaliacao1.gabriel_felix_faustina_prova1.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
	
}
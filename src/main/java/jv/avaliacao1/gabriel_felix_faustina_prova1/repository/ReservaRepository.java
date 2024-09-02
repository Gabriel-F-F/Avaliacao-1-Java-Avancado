package jv.avaliacao1.gabriel_felix_faustina_prova1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jv.avaliacao1.gabriel_felix_faustina_prova1.entity.ReservaEntity;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

	ReservaEntity findReservaByNumeroMesa(Integer numeroMesa);

}